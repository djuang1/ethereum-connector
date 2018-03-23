package org.mule.modules.ethereum;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.param.MetaDataKeyParamAffectsType;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.Source;
import org.mule.api.annotations.SourceStrategy;
import org.mule.api.annotations.display.Password;
import org.mule.api.callback.SourceCallback;
import org.mule.api.annotations.param.Default;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.mule.modules.ethereum.config.ConnectorConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

import rx.Subscription;

@Connector(name="ethereum", friendlyName="Ethereum")
@MetaDataScope( DataSenseResolver.class )
public class EthereumConnector {

	private static final Logger logger = LoggerFactory.getLogger(EthereumConnector.class);
	
	private static final int COUNT = 10;
	
    @Config
    ConnectorConfig config;

    
    /**
     * Get Balance
     *
     * @param address Address to check balance for.
     * @return The current balance of the address in wei
     * @throws Exception 
     */
    @Processor
    public EthGetBalance getBalance(String address) throws Exception {
            	
    	Web3j web3j = Web3j.build(new HttpService(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken()));
    	logger.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());    	
    	EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).send();
    	
        return ethGetBalance;
    }
    
    /**
     * Send Funds
     *
     * @param walletfile location of wallet file
     * @param password wallet file password
     * @param addressTo address to send funds to
     * @param value amount to send to address
     * @param unit Unit of value
     * @return A JSONObject 
     * @throws Exception 
     */
    @Processor
    public JSONObject sendFunds(String walletFile, @Password String password, String addressTo, BigDecimal value, Unit unit) throws Exception {
            	
    	Web3j web3j = Web3j.build(new HttpService(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken()));
    	logger.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion());    	

    	Credentials credentials =
                WalletUtils.loadCredentials(
                        password,
                        walletFile);
        logger.info("Credentials loaded");
        
        logger.info("Sending 1 Wei ("
                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
        CompletableFuture<TransactionReceipt> transactionReceipt = Transfer.sendFunds(
                web3j, credentials,
                addressTo,
                value, unit)  // 1 wei = 10^-18 Ether
                .sendAsync();
        logger.info("Funds sent to:" + addressTo);
        
        JSONObject obj = new JSONObject();
        obj.put("from", credentials.getAddress());
        obj.put("to", addressTo);
        obj.put("completed", transactionReceipt.isDone());
        
		return obj;
    }

    /**
     * DataSense processor

     * @param key Key to be used to populate the entity
     * @param entity Map that represents the entity
     * @return Some string
     
    @Processor
    public Map<String,Object> addEntity( @MetaDataKeyParam String key, @Default("#[payload]") Map<String,Object> entity) {
        return entity;
    }
	*/
	
    /**
     *  Block Filter
     *
     *  @param callback The sourcecallback used to dispatch message to the flow
     *  @throws Exception error produced while processing the payload
     */
    @Source (sourceStrategy = SourceStrategy.POLLING,pollingPeriod=5000)
    public void blockFilter(SourceCallback callback) throws Exception {

    	Web3j web3j = Web3j.build(new HttpService(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken()));
    	logger.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion()); 
    	
    	Subscription subscription = web3j.blockObservable(false).subscribe(ethBlock -> {
    		//logger.info("Block Number " + block.getBlock().getNumber() + " has just been created");
    		
    		try {
    			EthBlock.Block block = ethBlock.getBlock();
    			
    			LocalDateTime timestamp = Instant.ofEpochSecond(
                        block.getTimestamp()
                                .longValueExact()).atZone(ZoneId.of("UTC")).toLocalDateTime();

    			
    			block.getTransactions();
    			
                JSONObject obj = new JSONObject();
                obj.put("blockNumber", block.getNumber());
                obj.put("txCount", block.getTransactions().size());
                obj.put("hash", block.getHash());
                obj.put("parentHash", block.getParentHash());
                obj.put("extraData", block.getExtraData());
                obj.put("timestamp", timestamp);
                
				callback.process(obj);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	});
    	TimeUnit.MINUTES.sleep(2);
    	subscription.unsubscribe();
    }
    
    /**
     *  Transaction Filter
     *
     *  @param callback The sourcecallback used to dispatch message to the flow
     *  @throws Exception error produced while processing the payload
     */
    @Source (sourceStrategy = SourceStrategy.POLLING,pollingPeriod=5000)
    public void transactionFilter(SourceCallback callback) throws Exception {

    	logger.info(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken());
    	
    	Web3j web3j = Web3j.build(new HttpService(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken()));
    	
    	logger.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion()); 
    	
    	Subscription subscription = web3j.transactionObservable().subscribe(tx -> {

    		try {
    					
                JSONObject obj = new JSONObject();
                obj.put("to", tx.getTo()); 
                obj.put("from", tx.getFrom()); 
                obj.put("input", tx.getInput());
                obj.put("value", tx.getValue());
                
				callback.process(obj);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	});
    	TimeUnit.MINUTES.sleep(2);
    	subscription.unsubscribe();
    }
    
    /**
     *  Transaction Filter
     *
     *  @param callback The sourcecallback used to dispatch message to the flow
     *  @throws Exception error produced while processing the payload
     */
    @Source (sourceStrategy = SourceStrategy.POLLING,pollingPeriod=5000)
    public void eventFilter(SourceCallback callback, String contractAddress) throws Exception {

    	logger.info(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken());
    	
    	Web3j web3j = Web3j.build(new HttpService(config.getProtocol() + "://" + config.getHost() + ":" + config.getPort() + "/" + config.getToken()));
    	
    	logger.info("Connected to Ethereum client version: " + web3j.web3ClientVersion().send().getWeb3ClientVersion()); 
    	
		EthFilter filter = new EthFilter(
                DefaultBlockParameterName.EARLIEST,
                DefaultBlockParameterName.LATEST,
                contractAddress
        );
        	
    	Subscription subscription = web3j.ethLogObservable(filter).subscribe(log -> {

    		try {
    					
                JSONObject obj = new JSONObject();
                obj.put("blockNumber", log.getBlockNumber()); 
                obj.put("address", log.getAddress());
                obj.put("type", log.getType());
                
				callback.process(obj);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	});
    	
    	TimeUnit.MINUTES.sleep(2);
    	subscription.unsubscribe();
    }

    public ConnectorConfig getConfig() {
        return config;
    }

    public void setConfig(ConnectorConfig config) {
        this.config = config;
    }

}

package org.mule.modules.ethereum.generated.processors;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Generated;
import org.json.simple.JSONObject;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.devkit.internal.metadata.fixes.STUDIO7157;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;
import org.mule.modules.ethereum.EthereumConnector;
import org.mule.security.oauth.callback.ProcessCallback;
import org.web3j.utils.Convert.Unit;


/**
 * SendFundsMessageProcessor invokes the {@link org.mule.modules.ethereum.EthereumConnector#sendFunds(java.lang.String, java.lang.String, java.lang.String, java.math.BigDecimal, org.web3j.utils.Convert.Unit)} method in {@link EthereumConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2018-03-22T09:33:44-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class SendFundsMessageProcessor
    extends DevkitBasedMessageProcessor
    implements MessageProcessor, OperationMetaDataEnabled
{

    protected Object walletFile;
    protected String _walletFileType;
    protected Object password;
    protected String _passwordType;
    protected Object addressTo;
    protected String _addressToType;
    protected Object value;
    protected BigDecimal _valueType;
    protected Object unit;
    protected Unit _unitType;

    public SendFundsMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets password
     * 
     * @param value Value to set
     */
    public void setPassword(Object value) {
        this.password = value;
    }

    /**
     * Sets unit
     * 
     * @param value Value to set
     */
    public void setUnit(Object value) {
        this.unit = value;
    }

    /**
     * Sets walletFile
     * 
     * @param value Value to set
     */
    public void setWalletFile(Object value) {
        this.walletFile = value;
    }

    /**
     * Sets value
     * 
     * @param value Value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Sets addressTo
     * 
     * @param value Value to set
     */
    public void setAddressTo(Object value) {
        this.addressTo = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(null, false, event);
            final String _transformedWalletFile = ((String) evaluateAndTransform(getMuleContext(), event, SendFundsMessageProcessor.class.getDeclaredField("_walletFileType").getGenericType(), null, walletFile));
            final String _transformedPassword = ((String) evaluateAndTransform(getMuleContext(), event, SendFundsMessageProcessor.class.getDeclaredField("_passwordType").getGenericType(), null, password));
            final String _transformedAddressTo = ((String) evaluateAndTransform(getMuleContext(), event, SendFundsMessageProcessor.class.getDeclaredField("_addressToType").getGenericType(), null, addressTo));
            final BigDecimal _transformedValue = ((BigDecimal) evaluateAndTransform(getMuleContext(), event, SendFundsMessageProcessor.class.getDeclaredField("_valueType").getGenericType(), null, value));
            final Unit _transformedUnit = ((Unit) evaluateAndTransform(getMuleContext(), event, SendFundsMessageProcessor.class.getDeclaredField("_unitType").getGenericType(), null, unit));
            Object resultPayload;
            final ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((EthereumConnector) object).sendFunds(_transformedWalletFile, _transformedPassword, _transformedAddressTo, _transformedValue, _transformedUnit);
                }

            }
            , this, event);
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        MetaDataModel metaDataPayload = getPojoOrSimpleModel(JSONObject.class);
        DefaultMetaDataKey keyForStudio = new DefaultMetaDataKey("OUTPUT_METADATA", null);
        keyForStudio.setCategory("DataSenseResolver");
        metaDataPayload.addProperty(STUDIO7157 .getStructureIdentifierMetaDataModelProperty(keyForStudio, false, false));
        return new DefaultResult<MetaData>(new DefaultMetaData(metaDataPayload));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(null, false, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at EthereumConnector at sendFunds retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}

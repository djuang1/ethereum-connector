
package org.mule.modules.ethereum.generated.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/ethereum</code>.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2018-03-22T09:33:44-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class EthereumNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(EthereumNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [ethereum] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [ethereum] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config", new EthereumConnectorConnectorConfigConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("get-balance", new GetBalanceDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("get-balance", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("send-funds", new SendFundsDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("send-funds", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("block-filter", new BlockFilterDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("block-filter", "@Source", ex);
        }
        try {
            this.registerBeanDefinitionParser("transaction-filter", new TransactionFilterDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("transaction-filter", "@Source", ex);
        }
        try {
            this.registerBeanDefinitionParser("event-filter", new EventFilterDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("event-filter", "@Source", ex);
        }
    }

}

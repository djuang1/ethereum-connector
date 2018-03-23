
package org.mule.modules.ethereum.generated.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.mule.modules.ethereum.config.ConnectorConfig;
import org.mule.modules.ethereum.generated.adapters.EthereumConnectorConnectorConfigBasicAdapter;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2018-03-22T09:46:12-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class EthereumConnectorConnectorConfigConfigDefinitionParser
    extends AbstractDevkitBasedDefinitionParser
{

    private static Logger logger = LoggerFactory.getLogger(EthereumConnectorConnectorConfigConfigDefinitionParser.class);

    public String moduleName() {
        return "Ethereum";
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        parseConfigName(element);
        BeanDefinitionBuilder builder = getBeanDefinitionBuilder(parserContext);
        builder.setScope(BeanDefinition.SCOPE_SINGLETON);
        setInitMethodIfNeeded(builder, EthereumConnectorConnectorConfigBasicAdapter.class);
        setDestroyMethodIfNeeded(builder, EthereumConnectorConnectorConfigBasicAdapter.class);
        BeanDefinitionBuilder basicConfigBuilder = BeanDefinitionBuilder.rootBeanDefinition(ConnectorConfig.class.getName());
        parseProperty(basicConfigBuilder, element, "protocol", "protocol");
        parseProperty(basicConfigBuilder, element, "host", "host");
        parseProperty(basicConfigBuilder, element, "port", "port");
        parseProperty(basicConfigBuilder, element, "token", "token");
        builder.addPropertyValue("config", basicConfigBuilder.getBeanDefinition());
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        return definition;
    }

    private BeanDefinitionBuilder getBeanDefinitionBuilder(ParserContext parserContext) {
        try {
            return BeanDefinitionBuilder.rootBeanDefinition(EthereumConnectorConnectorConfigBasicAdapter.class.getName());
        } catch (NoClassDefFoundError noClassDefFoundError) {
            String muleVersion = "";
            try {
                muleVersion = MuleManifest.getProductVersion();
            } catch (Exception _x) {
                logger.error("Problem while reading mule version");
            }
            logger.error(("Cannot launch the mule app, the configuration [config] within the connector [ethereum] is not supported in mule "+ muleVersion));
            throw new BeanDefinitionParsingException(new Problem(("Cannot launch the mule app, the configuration [config] within the connector [ethereum] is not supported in mule "+ muleVersion), new Location(parserContext.getReaderContext().getResource()), null, noClassDefFoundError));
        }
    }

}

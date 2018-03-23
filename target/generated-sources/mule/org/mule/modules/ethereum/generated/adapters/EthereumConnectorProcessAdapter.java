
package org.mule.modules.ethereum.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.ethereum.EthereumConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>EthereumConnectorProcessAdapter</code> is a wrapper around {@link EthereumConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2018-03-22T09:46:12-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class EthereumConnectorProcessAdapter
    extends EthereumConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<EthereumConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, EthereumConnectorCapabilitiesAdapter> getProcessTemplate() {
        final EthereumConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,EthereumConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, EthereumConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, EthereumConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}

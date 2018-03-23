
package org.mule.modules.ethereum.generated.transformers;

import java.math.BigDecimal;
import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.config.i18n.MessageFactory;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2018-03-22T09:46:12-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class StringToBigDecimalTransformer
    extends AbstractTransformer
    implements DiscoverableTransformer
{

    private int priorityWeighting = 1;

    public StringToBigDecimalTransformer() {
        registerSourceType(DataTypeFactory.create(String.class));
        setReturnClass(BigDecimal.class);
        setName("StringToBigDecimalTransformer");
    }

    protected Object doTransform(Object src, String encoding)
        throws TransformerException
    {
        try {
            return new BigDecimal(((String) src));
        } catch (NumberFormatException e) {
            throw new TransformerException(MessageFactory.createStaticMessage(String.format("Could not parse %s to a big integer", src)), this, e);
        }
    }

    /**
     * Retrieves priorityWeighting
     * 
     */
    public int getPriorityWeighting() {
        return this.priorityWeighting;
    }

    /**
     * Sets priorityWeighting
     * 
     * @param value Value to set
     */
    public void setPriorityWeighting(int value) {
        this.priorityWeighting = value;
    }

}

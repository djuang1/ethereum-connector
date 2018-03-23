
package org.web3j.utils.generated.transformers;

import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;
import org.web3j.utils.Convert.Unit;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2018-03-22T09:33:44-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class ConvertUnitEnumTransformer
    extends AbstractTransformer
    implements DiscoverableTransformer
{

    private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;

    public ConvertUnitEnumTransformer() {
        registerSourceType(DataTypeFactory.create(String.class));
        setReturnClass(Unit.class);
        setName("ConvertUnitEnumTransformer");
    }

    protected Object doTransform(Object src, String encoding)
        throws TransformerException
    {
        Unit result = null;
        result = Enum.valueOf(Unit.class, ((String) src));
        return result;
    }

    public int getPriorityWeighting() {
        return weighting;
    }

    public void setPriorityWeighting(int weighting) {
        this.weighting = weighting;
    }

}

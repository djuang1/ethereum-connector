package org.mule.modules.ethereum;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mule.api.annotations.MetaDataKeyRetriever;
import org.mule.api.annotations.MetaDataRetriever;
import org.mule.api.annotations.components.MetaDataCategory;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.builder.DefaultMetaDataBuilder;
import org.mule.common.metadata.builder.DynamicObjectBuilder;
import org.mule.common.metadata.builder.PojoMetaDataBuilder;
import org.mule.common.metadata.datatype.DataType;

import org.web3j.protocol.core.methods.response.EthGetBalance;

/**
 * Category which can differentiate between input or output MetaDataRetriever
 */
@MetaDataCategory
public class DataSenseResolver {

    /**
     * If you have a service that describes the entities, you may want to use
     * that through the connector. Devkit will inject the connector, after
     * initializing it.
     */
    @Inject
    private EthereumConnector connector;

    /**
     * Retrieves the list of keys
     */
    @MetaDataKeyRetriever
    public List<MetaDataKey> getMetaDataKeys() throws Exception {
        List<MetaDataKey> keys = new ArrayList<MetaDataKey>();

        //Generate the keys
        keys.add(new DefaultMetaDataKey("ENTITY_TYPE_1", "Balance"));
        keys.add(new DefaultMetaDataKey("ENTITY_TYPE_2", "Book"));

        return keys;
    }

    /**
     * Get MetaData given the Key the user selects
     * 
     * @param key The key selected from the list of valid keys
     * @return The MetaData model of that corresponds to the key
     * @throws Exception If anything fails
     */
    @MetaDataRetriever
    public MetaData getMetaData(MetaDataKey key) throws Exception {
        DefaultMetaDataBuilder builder = new DefaultMetaDataBuilder();
        
        PojoMetaDataBuilder<?> pojoObject = null;
        
        DynamicObjectBuilder<?> dynamicObject = builder.createDynamicObject(key
                .getId());

        if (key.getId().equals("ENTITY_TYPE_1")) {
        	pojoObject = builder.createPojo(EthGetBalance.class);
        } else {
            dynamicObject.addSimpleField("Author", DataType.STRING);
            dynamicObject.addSimpleField("Tittle", DataType.STRING);
        } 
        MetaDataModel model = builder.build();
        MetaData metaData = new DefaultMetaData(model);

        return metaData;
    }

    public EthereumConnector getConnector() {
        return connector;
    }

    public void setConnector(EthereumConnector connector) {
        this.connector = connector;
    }
}

package org.mule.modules.ethereum.config;

import org.mule.api.annotations.components.Configuration;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.display.Placement;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.param.Optional;

@Configuration(friendlyName = "Configuration")
public class ConnectorConfig {

	public enum HttpProtocol {
	    HTTP, HTTPS
	}
	
	/**
     * Node protocol
     */
    @Configurable
    @Placement(order = 1)
    private HttpProtocol protocol;
    
    /**
     * Node host
     */
    @Configurable
    @Placement(order = 2)
    @Default("localhost")
    private String host;

    /**
     * Node port
     */
    @Configurable
    @Placement(order = 3)
    @Default("8545")
    private String port;
    
    /**
     * Infura token
     */
    @Configurable
    @Optional
    @Placement(group = "Infura Settings")
    @FriendlyName(value = "Infura Token")
    private String token;

    /**
     * Set node protocol
     *
     * @param protocol The node protocol
     */
    public void setProtocol(HttpProtocol protocol) {
        this.protocol = protocol;
    }

    /**
     * Get node protocol
     */
    public HttpProtocol getProtocol() {
        return this.protocol;
    }
    
    /**
     * Set node host
     *
     * @param host The node host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get node host
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Set node port
     *
     * @param port The node port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Get node port
     */
    public String getPort() {
        return this.port;
    }
    
    /**
     * Set Infura token
     *
     * @param port The Infura token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get Infura token
     */
    public String getToken() {
        return this.token;
    }

}
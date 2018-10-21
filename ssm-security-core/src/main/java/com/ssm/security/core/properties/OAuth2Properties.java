/**
 *
 */
package com.ssm.security.core.properties;

/**
 * @author jialq
 */
public class OAuth2Properties {

    private OAuth2ClientProperties[] clients = {};

    private String jwtSigningKey = "zcjlq";

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}

package com.ssm.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author 贾令强
 * @since 2018/7/22 18:00
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}

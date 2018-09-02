package com.ssm.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author 贾令强
 * @since 2018/7/29 16:51
 */
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}

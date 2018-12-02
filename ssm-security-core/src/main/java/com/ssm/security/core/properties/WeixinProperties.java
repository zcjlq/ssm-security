package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/7/29 16:51
 */
public class WeixinProperties extends SocialPropertiesInOldVersion {

    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}

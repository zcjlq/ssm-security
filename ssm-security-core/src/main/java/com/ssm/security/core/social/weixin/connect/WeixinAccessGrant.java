package com.ssm.security.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author 贾令强
 * @since 2018/8/1 20:00
 */
public class WeixinAccessGrant extends AccessGrant {

    private static final long serialVersionUID = 2899175777761999883L;
    /**
     * 微信多返回openId oAuth协议中无此字段
     */
    private String openId;

    public WeixinAccessGrant() {
        super("");
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}

package com.ssm.security.core.social.weixin.api;

/**
 * @author 贾令强
 * @since 2018/7/29 17:06
 */
public interface Weixin {

    /**
     * 比qq方式多一个openId
     *
     * @param openId 传入openId
     * @return
     */
    WeixinUserInfo getUserInfo(String openId);
}

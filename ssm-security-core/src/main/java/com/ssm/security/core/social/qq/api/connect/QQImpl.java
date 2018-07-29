package com.ssm.security.core.social.qq.api.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author 贾令强
 * @since 2018/7/21 16:18
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final Logger log = LoggerFactory.getLogger(QQImpl.class);

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private ObjectMapper objectMapper = new ObjectMapper();

    private String appId;

    private String openId;

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        String url = String.format(URL_GET_OPENID, accessToken);
        String result = super.getRestTemplate().getForObject(url, String.class);
        log.info(result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    public QQImpl(String accessToken, TokenStrategy tokenStrategy, String appId) {
        super(accessToken, tokenStrategy);
        this.appId = appId;
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = super.getRestTemplate().getForObject(url, String.class);
        log.info("getUserInfo:{}", result);

        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败" + e);
        }
        return qqUserInfo;
    }
}

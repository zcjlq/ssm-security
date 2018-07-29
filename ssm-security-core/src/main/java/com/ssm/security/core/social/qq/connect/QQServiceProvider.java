package com.ssm.security.core.social.qq.connect;

import com.ssm.security.core.social.qq.api.connect.QQ;
import com.ssm.security.core.social.qq.api.connect.QQImpl;
import com.ssm.security.core.social.qq.api.connect.QQOoAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author 贾令强
 * @since 2018/7/22 16:23
 * <p>
 * http://wiki.connect.qq.com/get_user_info
 * http://wiki.connect.qq.com/使用authorization_code获取access_token
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    private String appid;

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOoAuth2Template(appId, appSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL));
        this.appid = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        // 为了避免线程安全问题，每次使用时new
        return new QQImpl(accessToken, appid);
    }
}

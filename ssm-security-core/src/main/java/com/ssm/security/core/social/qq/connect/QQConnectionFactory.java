package com.ssm.security.core.social.qq.connect;

import com.ssm.security.core.social.qq.api.connect.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author 贾令强
 * @since 2018/7/22 16:46
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * Create a {@link OAuth2ConnectionFactory}.
     *
     * @param providerId      the provider id e.g. "facebook"
     * @param serviceProvider the ServiceProvider model for conducting the authorization flow and obtaining a native service API instance.
     * @param apiAdapter      the ApiAdapter for mapping the provider-specific service API model to the uniform {@link Connection} interface.
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapt());
    }
}

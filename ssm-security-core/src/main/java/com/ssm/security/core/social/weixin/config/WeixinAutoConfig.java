package com.ssm.security.core.social.weixin.config;

import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.properties.WeixinProperties;
import com.ssm.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author 贾令强
 * @since 2018/7/22 18:03
 */
@Configuration
@ConditionalOnProperty(prefix = "ssm.security.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialAutoConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WeixinAutoConfig.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        WeixinProperties weixinProperties = securityProperties.getSocial().getWeixin();

        log.info(weixinProperties.toString());

        return new WeixinConnectionFactory(weixinProperties.getProviderId(), weixinProperties.getAppId(),
                weixinProperties.getAppSecret());
    }


}

package com.ssm.security.core.social.qq.config;

import com.ssm.security.core.properties.QQProperties;
import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.social.qq.connect.QQConnectionFactory;
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
@ConditionalOnProperty(prefix = "ssm.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(QQAutoConfig.class);

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        log.info(qqProperties.toString());
        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }


}

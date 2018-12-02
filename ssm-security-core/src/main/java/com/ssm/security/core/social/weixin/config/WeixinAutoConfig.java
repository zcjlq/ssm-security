package com.ssm.security.core.social.weixin.config;

import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.properties.WeixinProperties;
import com.ssm.security.core.social.weixin.connect.WeixinConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

/**
 * @author 贾令强
 * @since 2018/7/22 18:03
 */
// SocialAutoConfigurerAdapter

@Configuration
@ConditionalOnProperty(prefix = "ssm.security.social.weixin", name = "app-id")
public class WeixinAutoConfig extends SocialConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WeixinAutoConfig.class);

    @Autowired
    private SecurityProperties securityProperties;

    public ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinProperties = securityProperties.getSocial().getWeixin();

        log.info(weixinProperties.toString());

        return new WeixinConnectionFactory(weixinProperties.getProviderId(), weixinProperties.getAppId(),
                weixinProperties.getAppSecret());
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        connectionFactoryConfigurer.addConnectionFactory(this.createConnectionFactory());
    }

    // 后补：做到处理注册逻辑的时候发现的一个bug：登录完成后，数据库没有数据，但是再次登录却不用注册了
    // 就怀疑是否是在内存中存储了。结果果然发现这里父类的内存ConnectionRepository覆盖了SocialConfig中配置的jdbcConnectionRepository
    // 这里需要返回null，否则会返回内存的 ConnectionRepository
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return null;
    }

    // https://blog.csdn.net/mr_zhuqiang/article/details/81942534


    @Override
    public UserIdSource getUserIdSource() {
        return () -> "anonymous";
    }
}

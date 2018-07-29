package com.ssm.security.core.social;

import com.ssm.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author 贾令强
 * @since 2018/7/22 16:49
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // Encryptors.noOpText 为了方便看具体信息，展示不加密解密
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository =
                new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 默认建表脚本在JdbcUsersConnectionRepository同包中
        // 表名默认，可用加前缀
        jdbcUsersConnectionRepository.setTablePrefix("ssm_");
        if (connectionSignUp != null) {
            jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        }
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SpringSocialConfigurer ssmSocialSecurityConfigurer() {
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessUrl();
        MySpringSocialConfigurer configurer = new MySpringSocialConfigurer(filterProcessUrl);
        configurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return configurer;
    }

    @Bean
    public ProviderSignInUtils providerSignInUtils() {
        return new ProviderSignInUtils(connectionFactoryLocator, this.getUsersConnectionRepository(connectionFactoryLocator));
    }
}

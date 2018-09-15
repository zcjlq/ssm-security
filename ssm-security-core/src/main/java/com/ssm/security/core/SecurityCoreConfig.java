package com.ssm.security.core;

import com.ssm.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 使配置类生效
 *
 * @author 贾令强
 * @since 2018/6/23 23:06
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

    /**
     * 使用spring提供的密码加密模式，相同密码每次加密后也不相同
     * 加密后长度为60
     * example：$2a$10$t2gRXJodCtopupnPg64Ymu8qcnC2ICneLdb6dnQVSUsalcN6bphfO
     *
     * @return 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

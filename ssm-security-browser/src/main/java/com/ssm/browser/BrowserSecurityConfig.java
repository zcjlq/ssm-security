package com.ssm.browser;

import com.ssm.security.core.authentication.AbstractChannelSecurityConfig;
import com.ssm.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ssm.security.core.properties.SecurityConstants;
import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * spring security 浏览器相关配置
 *
 * @author 贾令强
 * @since 2018/6/19 21:08
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 实现记住我功能，需要将cookie信息写入数据库,这里的dataSource是用的demo项目中的application.yml定义的
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 自定义用户信息获取业务类
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer ssmSocialSecurityConfigurer;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;


    /**
     * 用于实现remember-me功能
     * 自动创建表persistent_logins
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);// 启动时执行创建语句，第一次true，之后为false
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 从父类继承的配置，表单认证公共配置
        super.applyPasswordAuthenticationConfig(http);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(ssmSocialSecurityConfigurer)
                // 以下是记住我功能实现
                .and()
                .rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSecond())
                // 验证码配置
                .and()
                .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                // session 达到上限，阻止后来的登陆
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().getMaxSessionPreventsLogin())
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut")
//                .logoutSuccessUrl("/logout.html")
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                        "/user/register")
                .permitAll()
                .anyRequest()
                .authenticated()// 需要身份认证
                .and().csrf().disable();
    }
}

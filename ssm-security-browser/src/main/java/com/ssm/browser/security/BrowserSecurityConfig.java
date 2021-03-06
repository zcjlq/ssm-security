package com.ssm.browser.security;

import com.ssm.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.validate.code.SmsCodeFilter;
import com.ssm.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * spring security 配置
 *
 * @author 贾令强
 * @since 2018/6/19 21:08
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 登录成功后处理逻辑
     */
    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;
    /**
     * 登录失败后处理逻辑
     */
    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

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

    /**
     * 实现记住我功能，需要将cookie信息写入数据库,这里的dataSource是用的demo项目中的application.yml定义的
     */
    @Autowired
    private DataSource dataSource;

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

    /**
     * 自定义用户信息获取业务类
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置需要输入验证码的请求
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setFailureHandler(authenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        // 配置需要输入验证码的请求
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setFailureHandler(authenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                // form表单提交地址
                .loginProcessingUrl("/authentication/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .failureUrl("/login.html?error=true")
                // 以下是记住我功能实现
                .and()
                .rememberMe()
                .tokenRepository(this.persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSecond())
                // 验证码配置
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*")
                .permitAll()
                .anyRequest()
                .authenticated()// 需要身份认证
                .and().csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}

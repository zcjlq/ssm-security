package com.ssm.security.app.authtication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 开启资源服务器
 * 一个server可以同时作为认证服务器和资源服务器
 * 根据认证服务器提供的token作为请求头可以实现认证
 * 见03获取用户信息请求
 *
 * @author 贾令强
 * @since 2018/9/15 上午7:46
 */
@Configuration
@EnableResourceServer
public class SsmResourceServerConfig {
}

package com.ssm.security.app.authtication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 开启应用服务器
 * 默认生成security.oauth2.client.clientId和密码
 * 支持配置文件自定义
 * 01授权码模式 02用户名密码模式提供服务
 *
 * @author 贾令强
 * @since 2018/9/2 下午2:13
 */
@Configuration
@EnableAuthorizationServer
public class SsmAuthorizationServerConfig {


}

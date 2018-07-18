package com.ssm.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置文件，以ssm.security开头，下一级为browser
 *
 * @author 贾令强
 * @since 2018/6/23 22:58
 */
@ConfigurationProperties(prefix = "ssm.security")
@Configuration
public class SecurityProperties {

    /**
     * 浏览器相关配置，比如loginType 登录页面
     */
    private BrowserProperties browser = new BrowserProperties();

    /**
     * 验证码相关配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}

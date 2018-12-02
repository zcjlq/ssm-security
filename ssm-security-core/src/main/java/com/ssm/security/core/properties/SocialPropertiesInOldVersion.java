package com.ssm.security.core.properties;

/**
 * platform-bom 由Brussels-SR4升级到
 * SocialProperties删除
 * Brussels-SR4包含的jar为 spring-boot-autoconfigure-1.5.6
 * <dependency>
 * <groupId>io.spring.platform</groupId>
 * <artifactId>platform-bom</artifactId>
 * <version>Brussels-SR4</version>
 * <type>pom</type>
 * <scope>import</scope>
 * import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
 * import org.springframework.boot.autoconfigure.social.SocialProperties;
 *
 * @author 贾令强
 * @since 2018-12-02 16:37
 */
public abstract class SocialPropertiesInOldVersion {

    /**
     * Application id.
     */
    private String appId;

    /**
     * Application secret.
     */
    private String appSecret;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

}

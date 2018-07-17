package com.ssm.security.core.validate.code;

import com.ssm.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 贾令强
 * @since 2018/7/10 21:44
 */
@Configuration
public class ValidateCodeConfigBean {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 此处声明bean类似与在类ImageCodeGenerator上标注@Componet
     * 但是此处可以通过注解ConditionalOnMissingBean定义实现类，容器初始化时找不到bean imageCodeGenerator
     * 就会用此处的配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator imageCodeGenerator() {
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
}

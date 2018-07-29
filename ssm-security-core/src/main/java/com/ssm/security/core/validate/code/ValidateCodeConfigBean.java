package com.ssm.security.core.validate.code;

import com.ssm.security.core.properties.SecurityProperties;
import com.ssm.security.core.validate.code.sms.DefaultSmsCodeSenderImpl;
import com.ssm.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Bean
//    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    // 重构前使用此注解
    public ValidateCodeGenerator imageCodeGenerator() {
//        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
//        imageCodeGenerator.setSecurityProperties(securityProperties);
//        return imageCodeGenerator;
        return null;
    }

    /**
     * 注解ConditionalOnMissingBean可以通过name指定，也可以根据class
     * 当spring找不到接口SmsCodeSender的实现时才用下面的实现
     *
     * @return
     */
//    @Bean
//    @ConditionalOnMissingBean(SmsCodeSender.class)
    // 注意，重构前使用此注解
    public SmsCodeSender smsCodeGenerator() {
        return new DefaultSmsCodeSenderImpl();
    }
}

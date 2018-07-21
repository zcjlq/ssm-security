package com.ssm.security.core.validate.code.sms;

import org.springframework.stereotype.Component;

/**
 * 发送短信，默认实现
 *
 * @author 贾令强
 * @since 2018/7/17 21:47
 */
@Component  // 重构后必须在这里加注解声明bean，不能在类ValidateCodeConfigBean中new
public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送验证码" + code);
    }
}

package com.ssm.security.core.validate.code.sms;

/**
 * 发送短信，默认实现
 *
 * @author 贾令强
 * @since 2018/7/17 21:47
 */
public class DefaultSmsCodeSenderImpl implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送验证码" + code);
    }
}

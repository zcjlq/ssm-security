package com.ssm.security.core.validate.code.sms;

/**
 * @author 贾令强
 * @since 2018/7/17 21:46
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}

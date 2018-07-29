package com.ssm.security.core.validate.code.sms;

import com.ssm.security.core.validate.code.ValidateCode;
import com.ssm.security.core.validate.code.impl.AbstractValidateCodeProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author 贾令强
 * @since 2018/7/19 21:19
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessorImpl<ValidateCode> {

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}

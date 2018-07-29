package com.ssm.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/7/21 12:46
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    private ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(name);
        if (validateCodeProcessor == null) {
            throw new ValidateCodeException("验证码处理器：" + name + "，不存在");
        }
        return validateCodeProcessor;
    }
}

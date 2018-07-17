package com.ssm.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码相关异常
 *
 * @author 贾令强
 * @since 2018/7/7 16:13
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -1013295394367514019L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

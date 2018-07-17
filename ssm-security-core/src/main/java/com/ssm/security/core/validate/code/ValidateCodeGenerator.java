package com.ssm.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 贾令强
 * @since 2018/7/10 21:40
 */
public interface ValidateCodeGenerator {

    ImageCode createImageCode(ServletWebRequest request);
}

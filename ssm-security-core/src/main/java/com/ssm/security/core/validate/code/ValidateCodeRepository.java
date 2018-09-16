package com.ssm.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author 贾令强
 * @since 2018/9/16 下午8:42
 */
public interface ValidateCodeRepository {

    void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType);

    ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType);

    void remove(ServletWebRequest request, ValidateCodeType validateCodeType);
}

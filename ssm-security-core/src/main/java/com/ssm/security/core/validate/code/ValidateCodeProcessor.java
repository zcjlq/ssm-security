package com.ssm.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码处理器，封装不同的验证码处理逻辑
 *
 * @author 贾令强
 * @since 2018/7/17 22:27
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码
     *
     * @param request
     * @throws Exception
     */
    void create(ServletWebRequest request) throws Exception;
}

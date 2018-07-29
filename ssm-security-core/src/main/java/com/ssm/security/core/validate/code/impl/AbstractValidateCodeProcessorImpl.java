package com.ssm.security.core.validate.code.impl;

import com.ssm.security.core.validate.code.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/7/17 22:32
 */
public abstract class AbstractValidateCodeProcessorImpl<C extends ValidateCode> implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * spring收集系统中所有{@link ValidateCodeGenerator}接口的实现,bean名称 做key value为bean
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        // 生成验证码
        C validateCode = this.generate(request);
        // 保存验证码
        this.save(request, validateCode);
        // 发送验证码
        this.send(request, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = this.getProcessorType(request).toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, this.getSessionKey(), validateCode);
    }

    /**
     * 发送验证码，由子类实现
     *
     * @param request
     * @param validateCode
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求的url判断验证码类型
     *
     * @param request
     * @return
     */
    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType validateCodeType = this.getValidateCodeType();
        String sessionKey = this.getSessionKey();

        C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);

        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), validateCodeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new SecurityException(validateCodeType + "获取验证码的值失败");
        }


        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(validateCodeType + "验证码的值为空");
        }
        if (codeInSession == null) {
            throw new ValidateCodeException(validateCodeType + "验证码不存在");
        }
        if (codeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException(validateCodeType + "验证码已过期");
        }
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(validateCodeType + "验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, sessionKey);
    }

    private String getSessionKey() {
        return SESSION_KEY_PREFIX + this.getValidateCodeType().toString().toUpperCase();
    }

    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(this.getClass().getSimpleName(), "CodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }
}

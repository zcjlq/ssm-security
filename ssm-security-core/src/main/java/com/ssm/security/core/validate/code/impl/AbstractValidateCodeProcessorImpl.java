package com.ssm.security.core.validate.code.impl;

import com.ssm.security.core.validate.code.ValidateCodeGenerator;
import com.ssm.security.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @author 贾令强
 * @since 2018/7/17 22:32
 */
public abstract class AbstractValidateCodeProcessorImpl<C> implements ValidateCodeProcessor {

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
        String processorType = this.getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(processorType + "CodeGenerator");
        return (C) validateCodeGenerator.generateCode(request);
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request,
                SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
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
}

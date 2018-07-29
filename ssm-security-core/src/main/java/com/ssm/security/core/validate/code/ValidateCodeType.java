package com.ssm.security.core.validate.code;

import com.ssm.security.core.properties.SecurityConstants;

/**
 * @author 贾令强
 * @since 2018/7/21 12:35
 */
public enum ValidateCodeType {

    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };


    /**
     * 校验时从请求中获取的参数名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();
}


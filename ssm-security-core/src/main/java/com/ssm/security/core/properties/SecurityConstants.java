package com.ssm.security.core.properties;

/**
 * 公共常量
 *
 * @author 贾令强
 * @since 2018/7/21 12:24
 */
public interface SecurityConstants {

    /**
     * 验证码url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * TODO
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 表单认证url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 短信验证码form认证url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 默认登陆页面
     */
    String DEFAULT_LOGIN_PAGE_URL = "/login.html";

    /**
     * 登陆页面图片验证码input name
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 登陆页面短信验证码input name
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 登陆页面手机号input name
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

}

package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/6/23 22:58
 */
public class BrowserProperties {

    /**
     * 默认登陆页面
     */
    private String loginPage = "/login.html";

    /**
     * 登陆类型
     */
    private LoginType loginType = LoginType.JSON;

    /**
     * 记住我一个小时
     */
    private int rememberMeSecond = 3600;

    private String signUpUrl = "/signup.html";

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSecond() {
        return rememberMeSecond;
    }

    public void setRememberMeSecond(int rememberMeSecond) {
        this.rememberMeSecond = rememberMeSecond;
    }
}

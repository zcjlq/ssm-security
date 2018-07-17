package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/6/23 22:58
 */
public class BrowserProperties {

    // 设置默认值
    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSecond = 3600;// 记住我一个小时

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

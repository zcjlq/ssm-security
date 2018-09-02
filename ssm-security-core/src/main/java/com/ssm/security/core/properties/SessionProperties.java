package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/8/27 下午9:45
 */
public class SessionProperties {

    private int maximumSessions = 1;

    private boolean isMaxSessionPreventsLogin;

    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean getMaxSessionPreventsLogin() {
        return isMaxSessionPreventsLogin;
    }

    public void setMaxSessionPreventsLogin(boolean maxSessionPrevent) {
        isMaxSessionPreventsLogin = maxSessionPrevent;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }
}

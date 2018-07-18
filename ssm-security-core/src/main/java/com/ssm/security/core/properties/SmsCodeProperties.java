package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/7/8 13:09
 */
public class SmsCodeProperties {

    // 验证码长度
    private int length = 6;
    // 验证码过期时间，单位秒
    private int expireIn = 60;

    // 需要生成验证码的请求
    private String url;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

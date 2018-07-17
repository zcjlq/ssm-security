package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/7/8 13:09
 */
public class ImageCodeProperties extends SmsCodeProperties {

    private int width = 67;
    private int height = 23;

    // 需要生成验证码的请求
    private String url;

    /**
     * 通过构造器，设置默认长度
     */
    public ImageCodeProperties() {
        this.setLength(4);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}

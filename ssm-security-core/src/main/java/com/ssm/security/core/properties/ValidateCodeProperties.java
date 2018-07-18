package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/7/8 13:12
 */
public class ValidateCodeProperties {

    /**
     * 图片验证码
     */
    private ImageCodeProperties image = new ImageCodeProperties();

    /**
     * 短信验证码
     */
    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}

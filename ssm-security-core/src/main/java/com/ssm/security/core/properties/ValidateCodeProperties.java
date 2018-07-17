package com.ssm.security.core.properties;

/**
 * @author 贾令强
 * @since 2018/7/8 13:12
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}

package com.ssm.security.core.validate.code.image;

import com.ssm.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 贾令强
 * @since 2018/7/7 13:09
 */
public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    /**
     * @param bufferedImage
     * @param code
     * @param expireIn      过期时间
     */
    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public ImageCode(BufferedImage bufferedImage, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }


}

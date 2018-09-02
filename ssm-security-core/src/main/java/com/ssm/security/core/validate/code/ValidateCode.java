package com.ssm.security.core.validate.code;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 贾令强
 * @since 2018/7/17 20:59
 */
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = -5924077540305513951L;
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    // 当前时间是否在过期时间后
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}

package com.ssm.web.exception;

/**
 * 自定义异常
 *
 * @author 贾令强
 * @since 2018/6/18 01:25
 */
public class UserNotFoundException extends RuntimeException {

    private Integer id;

    public UserNotFoundException(Integer id) {
        super("自定义异常");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

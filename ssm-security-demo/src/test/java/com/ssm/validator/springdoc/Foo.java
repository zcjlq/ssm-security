package com.ssm.validator.springdoc;

import javax.validation.constraints.Size;

/**
 * @author 贾令强
 * @since 2018/11/22 1:35 PM
 */
public class Foo {

    @Size
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

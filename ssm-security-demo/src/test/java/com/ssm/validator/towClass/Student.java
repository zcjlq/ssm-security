package com.ssm.validator.towClass;


import com.ssm.validator.GroupA;

import javax.validation.constraints.AssertTrue;

/**
 * @author 贾令强
 * @since 2018/11/22 5:05 PM
 */
public class Student {

    @AssertTrue(message = "属性assertTrue只能为true", groups = GroupA.class)
    private Boolean assertTrue;

    public Boolean getAssertTrue() {
        return assertTrue;
    }

    public void setAssertTrue(Boolean assertTrue) {
        this.assertTrue = assertTrue;
    }

    @Override
    public String toString() {
        return "Student{" +
                "assertTrue=" + assertTrue +
                '}';
    }
}

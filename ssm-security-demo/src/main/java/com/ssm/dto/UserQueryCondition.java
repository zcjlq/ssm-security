package com.ssm.dto;

/**
 * @author 贾令强
 * @since 2018/6/17 21:49
 */
public class UserQueryCondition {

    private int age;

    //    @ApiModelProperty("用户名称，非空")
    private String username;

    //    @ApiModelProperty("用户年龄，非空")
    private int ageTo;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }
}

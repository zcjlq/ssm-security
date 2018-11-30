package com.ssm.validator;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Person {

    @AssertTrue(message = "属性assertTrue只能为true", groups = GroupA.class)
    private Boolean assertTrue;

    //    @NotNull(message = "student不能为null")
    private Student student;

    @Pattern(regexp = "^\\w+$", message = "'${validatedValue}'登陆用户名只能是英文字母", groups = GroupB.class)
    private String name;

    @Min(value = 18, message = "年龄必须大于等于18")
    private Integer minAge;

    @Max(value = 60, message = "年龄只能是数字，且小于等于60")
    private String maxAge;

    private String sex;

    @DecimalMin(value = "1")
    private BigDecimal money;

    @Size(min = 1, max = 5, message = "长度必须在1到5之间")
    private String size;

    @Size(min = 1, max = 5, message = "长度必须在1到5之间")
    private List listSize;

    @Length(min = 1, max = 5, message = "String 长度必须在1-5之间")
    private String stringLength;

    // integer 可以接受的最大整数位数
    // fraction 可以接受的最大位数
    @Digits(integer = 1, fraction = 2, message = "被注释的元素必须是一个数字，最大1位整数，最多2位小数")
    private Double aDouble;

    @Digits(integer = 1, fraction = 2, message = "被注释的元素必须是一个数字，最大1位整数，最多2位小数")
    private String stringDigits;

    @Past(message = "生日必须小于当前时间")
    private Date birthday;

    @NotEmpty(message = "not empty")
    private String stringEmpty;

    @NotBlank(message = "not blank")
    private String stringBlank;

    public Person() {
    }

    public Person(@NotEmpty String name) {
        this.name = name;
    }

    public String getStringEmpty() {
        return stringEmpty;
    }

    public void setStringEmpty(String stringEmpty) {
        this.stringEmpty = stringEmpty;
    }

    public String getStringBlank() {
        return stringBlank;
    }

    public void setStringBlank(String stringBlank) {
        this.stringBlank = stringBlank;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getStringDigits() {
        return stringDigits;
    }

    public void setStringDigits(String stringDigits) {
        this.stringDigits = stringDigits;
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }

    public String getStringLength() {
        return stringLength;
    }

    public void setStringLength(String stringLength) {
        this.stringLength = stringLength;
    }

    public List getListSize() {
        return listSize;
    }

    public void setListSize(List listSize) {
        this.listSize = listSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }


    public Boolean getAssertTrue() {
        return assertTrue;
    }

    public void setAssertTrue(Boolean assertTrue) {
        this.assertTrue = assertTrue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    static class Student {

        private String studentName;

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }
    }
}


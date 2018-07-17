package com.ssm.validate;

import com.ssm.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author 贾令强
 * @since 2018/6/18 00:39
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("自定义校验器初始化");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.hello("自定义校验器可以注入任何bean，不需要标注");
        System.out.println("自定义校验器接收到value为：" + value);
        return false;
    }
}

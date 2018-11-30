package com.ssm.validator.springdoc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author 贾令强
 * @since 2018/11/22 1:37 PM
 */
public class FooValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}

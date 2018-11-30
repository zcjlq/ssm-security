package com.ssm.validator;

import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author 贾令强
 * @since 2018/11/22 4:47 PM
 */
public class PersonGroupTest {
    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void test1() {
        Person person = new Person();
        person.setAssertTrue(false);
        Set<ConstraintViolation<Person>> validate = validator.validate(person, GroupA.class);
        System.out.println(validate.size());
        validate.forEach(valid -> System.out.println(valid.getMessage()));
    }
}

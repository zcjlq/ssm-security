package com.ssm.validator.towClass;

import com.ssm.validator.GroupA;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author 贾令强
 * @since 2018/11/22 5:06 PM
 */
public class TwoClassTest {

    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void test1() {
        Student student = new Student();
        student.setAssertTrue(false);

        ClassRoom classRoom = new ClassRoom();
        classRoom.setStudent(student);
        classRoom.setStrings(Arrays.asList(" ", "1"));
        classRoom.setStudents(Arrays.asList(null, new Student()));

        Set<ConstraintViolation<ClassRoom>> validate = validator.validate(classRoom);
        System.out.println(validate.size());
        validate.forEach(valid -> System.out.println(valid.getMessage()));
        System.out.println("-----------");

        Set<ConstraintViolation<ClassRoom>> validate1 = validator.validate(classRoom, GroupA.class);
        validate1.forEach(valid -> System.out.println(valid.getMessage()));
        System.out.println("-----------");


        List<String> strings = Arrays.asList(" ", "1");
        strings.forEach(System.out::println);

    }

}

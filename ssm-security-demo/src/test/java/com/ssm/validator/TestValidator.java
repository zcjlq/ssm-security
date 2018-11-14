package com.ssm.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * @author 贾令强
 * @since 2018/11/12 8:35 PM
 */
public class TestValidator {

    private static final Logger log = LoggerFactory.getLogger(TestValidator.class);
    private Validator validator;
    private Person person;

    @Before
    public void before() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        person = new Person();
    }

    @After
    public void after() {
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        log.info("校验没有通过的数量为：[{}]", validate.size());
        for (ConstraintViolation<Person> violation : validate) {
            String message = violation.getMessage();
            log.info("校验结果为：[{}]", message);
        }
    }

    @Test
    public void testEl() {
        person.setName("false哈哈");
    }

    @Test
    public void testAssertTrue() {
//        person.setAssertTrue(true);
        person.setAssertTrue(false);
    }

    @Test
    public void testNotNull() {
        // 放开属性Student注释
        Person.Student student = new Person.Student();
        person.setStudent(student);
//        person.setStudent(null);
    }

    @Test
    public void testMinMax() {
//        person.setMinAge(18);
        person.setMinAge(11);
//        person.setMaxAge("51.5");
//        person.setMaxAge("61");
        person.setMaxAge("汉字");
    }

    @Test
    public void testDecimal() {
//        BigDecimal money = new BigDecimal(0.23);
        BigDecimal money = new BigDecimal(22.23);
        BigDecimal bigDecimal = money.setScale(3, BigDecimal.ROUND_HALF_UP);
        log.info("money[{}]", bigDecimal);
        person.setMoney(money);
    }

    // @Size可以用在集合上
    @Test
    public void testSize() {
//        person.setSize("a2345");
//        person.setSize("a23456");
//        person.setSize("");
//        List<String> list = new ArrayList<>(Collections.singleton("aa"));
//        person.setListSize(list);
//        person.setListSize(null);
        person.setListSize(new ArrayList());
    }

    // @Length 只能用在String上
    @Test
    public void testLength() {
//        person.setStringLength("");
        person.setStringLength("12");
    }

    @Test
    public void testDouble() {
//        person.setaDouble(11.2);
//        person.setaDouble(1.2);
//        person.setaDouble(1.2333);

//        person.setStringDigits("1.12");
        person.setStringDigits("11.12");
    }

    @Test
    public void testDate() {
//        Date date = new Date(System.currentTimeMillis() - 9999999);
        Date date = new Date(System.currentTimeMillis() + 9999999);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        log.info("date:{}", format);
        person.setBirthday(date);
    }


    @Test
    public void testNull11() {
        Set<ConstraintViolation<Person>> validate = validator.validate(person);
        log.info("没有通过的数量为：[{}]", validate.size());
        for (ConstraintViolation<Person> violation : validate) {
            String message = violation.getMessage();
            log.info("校验信息[{}]", message);
        }
    }
}


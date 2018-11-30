package com.ssm.validator.springdoc;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * @author 贾令强
 * @since 2018/11/22 1:35 PM
 */
public class Main {

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.setName("");
        DataBinder dataBinder = new DataBinder(foo);
        dataBinder.setValidator(new FooValidator());

//        dataBinder.bind();
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();

    }
}

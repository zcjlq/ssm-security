package com.ssm.validate.demo;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 贾令强
 * @since 2018/11/22 2:02 PM
 */
@RequestMapping("/valid")
@RestController
public class Validation2Controller {

    @RequestMapping("/demo2")
    public void demo2(@RequestBody @Valid DemoModel demo, BindingResult result) {
        System.out.println("-------------");
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
        System.out.println("-------------");
    }
}

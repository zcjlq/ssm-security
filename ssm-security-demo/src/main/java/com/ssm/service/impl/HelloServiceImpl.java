package com.ssm.service.impl;

import com.ssm.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author 贾令强
 * @since 2018/6/18 00:41
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello(String s) {
        System.out.println("service hello 方法");
    }
}

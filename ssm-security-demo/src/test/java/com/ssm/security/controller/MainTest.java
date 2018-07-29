package com.ssm.security.controller;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 普通测试类，区别于需要spring配置文件的
 */
public class MainTest {

    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("1234");
        String encode1 = passwordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println(encode.length());
        System.out.println(encode1);
        System.out.println(encode1.length());
    }

    @Test
    public void testPost() {
        System.out.println(HttpMethod.POST);
    }

    @Test
    public void testBcry() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}

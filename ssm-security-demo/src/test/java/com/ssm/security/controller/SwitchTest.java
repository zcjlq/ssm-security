package com.ssm.security.controller;

import org.junit.Test;

public class SwitchTest {

    /**
     * 测试不加break，将会打印 2 和 3
     * case 两个不能都是a
     */
    @Test
    public void testCase() {
        String s = "a";
        switch (s) {
            case "a":
                System.out.println("----1");
            case "b":
                System.out.println("----2");
            default:
                System.out.println("----3");
        }

        int[] a = {};
        int b[] = {};
    }

}

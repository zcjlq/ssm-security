package com.ssm.web.controller;

import com.ssm.web.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理机制
 *
 * @author 贾令强
 * @since 2018/6/18 01:26
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * @param userNotFoundException
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handlerUserNotFoundException(UserNotFoundException userNotFoundException) {

        System.out.println("Controller 所有异常为UserNotFoundException，将进入此处处理");

        HashMap<String, Object> map = new HashMap<>(2);
        map.put("id", userNotFoundException.getId());
        map.put("msg", userNotFoundException.getMessage());
        return map;
    }
}

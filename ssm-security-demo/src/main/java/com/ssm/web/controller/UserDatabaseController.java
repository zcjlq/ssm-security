package com.ssm.web.controller;

import com.ssm.generator.model.User;
import com.ssm.web.service.UserDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 贾令强
 * @since 2018/8/23 21:37
 */
@RestController
@RequestMapping("/user_db")
public class UserDatabaseController {
    private static final Logger log = LoggerFactory.getLogger(UserDatabaseController.class);


    @Autowired
    private UserDatabaseService userDatabaseService;

    @GetMapping("/{id:\\d+}")
    public User getUser(@PathVariable String id) {
        User user = userDatabaseService.getUser(id);
        return user;
    }

    @GetMapping("/add/{id:\\d+}")
    public User addUser(@PathVariable String id) {
        User user = userDatabaseService.addUser(id);
        return user;
    }
}

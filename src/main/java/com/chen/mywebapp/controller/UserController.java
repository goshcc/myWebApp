package com.chen.mywebapp.controller;

import com.chen.mywebapp.domain.Result;
import com.chen.mywebapp.domain.User;
import com.chen.mywebapp.service.UserService;
import com.chen.mywebapp.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/save")
    public Result<User> save(@RequestParam String name) {
        return ResultUtils.success(userService.saveUser(name));
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> girlList() {
        logger.info("get all users");

        return userService.getAllUsers();
    }
}

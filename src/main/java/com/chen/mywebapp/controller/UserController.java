package com.chen.mywebapp.controller;

import com.chen.mywebapp.domain.Result;
import com.chen.mywebapp.domain.User;
import com.chen.mywebapp.service.UserService;
import com.chen.mywebapp.utils.JsonUtils;
import com.chen.mywebapp.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/save")
    public Result<User> save(@RequestParam String name, @RequestParam String password) {
        userService.saveUser(name, password);
        return ResultUtils.success();
    }

    @GetMapping("/user/{name}")
    public Result<User> getUserByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        if(user == null){
            return ResultUtils.error(500, "no user");
        }else{
            return ResultUtils.success(user);
        }
    }
}

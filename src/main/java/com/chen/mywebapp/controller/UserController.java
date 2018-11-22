package com.chen.mywebapp.controller;

import com.chen.mywebapp.domain.Result;
import com.chen.mywebapp.domain.User;
import com.chen.mywebapp.repository.UserMongoRepository;
import com.chen.mywebapp.repository.UserRepository;
import com.chen.mywebapp.service.UserService;
import com.chen.mywebapp.utils.JsonUtils;
import com.chen.mywebapp.utils.RedisOperator;
import com.chen.mywebapp.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redis;

    @Autowired
    private UserMongoRepository userMongoRepository;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/save")
    public Result<User> save(@RequestParam String name) {
        User user = userService.saveUser(name);
        redis.set(String.valueOf(user.getId()), JsonUtils.objectToJson(user), 2000);
        userMongoRepository.saveUser(user);

        return ResultUtils.success(user);
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public List<User> userList() {
        logger.info("get all users");

        return userService.getAllUsers();
    }

    @GetMapping("/user/redis/{id}")
    public Result<User> getUserFromRedisByID(@PathVariable String id) {
        String userFromRedis = redis.get(id);
        if(StringUtils.isEmpty(userFromRedis)){
            return ResultUtils.error(500, "no user");
        }else{
            User userBorn = JsonUtils.jsonToPojo(userFromRedis, User.class);
            return ResultUtils.success(userBorn);
        }
    }

    @GetMapping("/user/mongo/{name}")
    public Result<User> getUserFromMongoByName(@PathVariable String name) {
        User userFromMongo = userMongoRepository.findUserByName(name);
        if(userFromMongo == null){
            return ResultUtils.error(500, "no user");
        }else{
            return ResultUtils.success(userFromMongo);
        }
    }
}

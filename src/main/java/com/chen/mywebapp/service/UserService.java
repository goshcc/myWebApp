package com.chen.mywebapp.service;

import com.chen.mywebapp.domain.User;
import com.chen.mywebapp.enums.ResultEnum;
import com.chen.mywebapp.exception.CommonException;
import com.chen.mywebapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    public User saveUser(String name) {
        User user = new User();
        user.setName(name);
        user.setPassword("xxx");
        if(userRepository.save(user)) {
            logger.info("save user {}", user.toString());
        }else{
            throw new CommonException(ResultEnum.SAVE_USER_ERROR);
        }

        return user;
    }

    public List<User> getAllUsers(){
        Collection<User> userCollection = userRepository.findAll();

        return new ArrayList(userCollection);
    }
}

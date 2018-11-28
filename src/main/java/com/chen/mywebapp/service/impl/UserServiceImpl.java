package com.chen.mywebapp.service.impl;

import com.chen.mywebapp.domain.User;
import com.chen.mywebapp.repository.BaseMongoRepository;
import com.chen.mywebapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BaseMongoRepository<User> userRepository;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public void saveUser(String name, String password) {
        logger.info("save user ", name);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }
}

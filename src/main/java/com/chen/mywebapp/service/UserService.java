package com.chen.mywebapp.service;

import com.chen.mywebapp.domain.User;

public interface UserService {
    public void saveUser(String name, String password);

    public User getUserByName(String name);
}

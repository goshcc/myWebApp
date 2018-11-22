package com.chen.mywebapp.repository;

import com.chen.mywebapp.domain.User;

public interface UserMongoRepository {
    public User findUserByName(String name);

    public void saveUser(User user);

    public void updateUser(User user);

    public void removeUser(User user);
}

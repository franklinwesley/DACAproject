package com.ufcg.services;

import com.ufcg.models.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> findAllUser();
    boolean isUserExist(User user);
}

package com.ufcg.services;

import com.ufcg.models.User;

import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
public interface UserService {

    User findById(Long id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<User> findAllUser();
    boolean isUserExist(User user);
}

package com.ufcg.services;

import com.ufcg.Utils.UserType;
import com.ufcg.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Long id) {
        return new User("Email " + id, "Password", id, UserType.NORMAL);
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("Email " + i, "Password", (long) i, UserType.NORMAL));
        }
        return users;
    }

    @Override
    public boolean isUserExist(User user) {
        return false;
    }
}

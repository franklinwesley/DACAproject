package com.ufcg.services;

import com.ufcg.models.User;
import com.ufcg.models.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(Long id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long user);
    List<UserDTO> findAllUser();
    boolean isUserExist(User user);
    boolean findByEmail(User currentUser);
}

package com.ufcg.repositories;

import com.ufcg.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findAll();
    String findByEmail(String email);
    User findUserByEmail(String username);
}

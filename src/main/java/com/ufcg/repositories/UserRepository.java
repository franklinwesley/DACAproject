package com.ufcg.repositories;

import com.ufcg.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by PábloVíctor on 28/08/2016.
 */
public interface UserRepository extends CrudRepository<User, Long>{
    List<User> findAll();
}
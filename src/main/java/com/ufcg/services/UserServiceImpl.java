package com.ufcg.services;

import com.ufcg.models.User;
import com.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProblemService problemService;

    @Autowired
    SolutionService solutionService;

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        if (isUserExist(user)){
            userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(User user) {
        problemService.deleteUserProblems(user.getId());
        solutionService.deleteUserSolutions(user.getId());
        userRepository.delete(user);
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserExist(User user) {
        return userRepository.exists(user.getId());
    }

    @Override
    public boolean findByEmail(User currentUser) {
        return userRepository.findByEmail(currentUser.getEmail()) != null;
    }
}

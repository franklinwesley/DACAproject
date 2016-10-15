package com.ufcg.services;

import com.ufcg.models.User;
import com.ufcg.models.UserDTO;
import com.ufcg.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.findOne(id));
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
    public void deleteUser(Long user) {
        problemService.deleteUserProblems(user);
        solutionService.deleteUserSolutions(user);
        userRepository.delete(user);
    }

    @Override
    public List<UserDTO> findAllUser() {
        List<UserDTO> result = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            result.add(new UserDTO(user));
        }
        return result;
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

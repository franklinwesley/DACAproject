package com.ufcg.loaders;

import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository productRepository;
    private ProblemRepository problemRepository;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setProductRepository(UserRepository productRepository, ProblemRepository problemRepository) {
        this.productRepository = productRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User myUser = new User("pablo212123@gmail.com","210494", UserType.ADMINISTRATOR);
        productRepository.save(myUser);
        log.info("Saved Mug1212 - id:" + myUser.getId());

        for (int i = 0; i < 30; i++) {
            User shirt = new User("userTest"+i+"@gmail.com","2312331", UserType.NORMAL);
            productRepository.save(shirt);
            log.info("Saved Mug - id:" + shirt.getId());
        }

//        String username = "userTest@gmail.com";
//        String password = "2312331";
//
//        User userTest = new User(username,password, UserType.NORMAL);
//        productRepository.save(userTest);
//
//        List<Test> testList = new ArrayList<>();
//
//        User userCreator = new User("usercreator@gmail.com", "1oi2io1n", UserType.ADMINISTRATOR);
//        productRepository.save(userCreator);
//        for (int i = 0; i < 5; i++) {
//            testList.add(new com.ufcg.models.Test("Test " + i, "Bad tip", "Inputs", "Output", Visibility.PUBLIC));
//        }
//        Problem problem = new Problem(userCreator, "Problem 1", "Problem about the problems", "Good tip", testList, Visibility.PUBLIC);
//        problemRepository.save(problem);

    }
}


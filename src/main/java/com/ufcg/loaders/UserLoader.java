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

    private UserRepository userRepository;
    private ProblemRepository problemRepository;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setProductRepository(UserRepository userRepository, ProblemRepository problemRepository) {
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User myUser = new User("admin@gmail.com","123456", UserType.ADMINISTRATOR);
        userRepository.save(myUser);
        log.info("Saved user - id:" + myUser.getId());

        for (int i = 0; i < 30; i++) {
            User user = new User("userTest"+i+"@gmail.com","123456", UserType.NORMAL);
            userRepository.save(user);
            log.info("Saved - id:" + user.getId());
            Test problemTest2 = new com.ufcg.models.Test("problemTest 2", "Use Scanner object",
                    "oi", "oi", Visibility.PUBLIC);

            List<Test> testList = new ArrayList<>();
            testList.add(problemTest2);

            Problem problem2 = new Problem(user, "Print input 2", "Print the program input",
                    "Use Scanner object", testList, Visibility.PUBLIC);
            problemRepository.save(problem2);
        }
    }
}


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

        User myUser = new User("admin@gmail.com","123456", UserType.ADMINISTRATOR);
        productRepository.save(myUser);
        log.info("Saved user - id:" + myUser.getId());

        for (int i = 0; i < 30; i++) {
            User shirt = new User("userTest"+i+"@gmail.com","123456", UserType.NORMAL);
            productRepository.save(shirt);
            log.info("Saved - id:" + shirt.getId());
        }

    }
}


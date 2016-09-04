package com.ufcg.loaders;

import com.ufcg.Utils.UserType;
import com.ufcg.models.User;
import com.ufcg.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository productRepository;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setProductRepository(UserRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        for (int i = 0; i < 30; i++) {
            User shirt = new User("userTest"+i+"@gmail.com","2312331", UserType.NORMAL);
            productRepository.save(shirt);
            log.info("Saved Mug - id:" + shirt.getId());
        }

    }
}


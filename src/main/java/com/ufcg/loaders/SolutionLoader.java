package com.ufcg.loaders;

import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import com.ufcg.models.Test;
import com.ufcg.models.User;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.SolutionRepository;
import com.ufcg.repositories.TestRepository;
import com.ufcg.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SolutionLoader implements ApplicationListener<ContextRefreshedEvent> {

    private SolutionRepository solutionRepository;
    private UserRepository userRepository;
    private ProblemRepository problemRepository;
    private TestRepository testRepository;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setProductRepository(SolutionRepository solutionRepository,
                                     UserRepository userRepository,
                                     ProblemRepository problemRepository,
                                     TestRepository testRepository) {
        this.solutionRepository = solutionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.testRepository = testRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        User pablo = new User("pablo@gmail.com","2312331", UserType.NORMAL);
        userRepository.save(pablo);
        log.info("Saved user - id:" + pablo.getId());
        User franklin = new User("franklin@gmail.com","2312331", UserType.NORMAL);
        userRepository.save(franklin);
        log.info("Saved user - id:" + franklin.getId());

        Test problemTest = new Test("problemTest", "Use Scanner object", "oi", "oi", Visibility.PUBLIC);
        testRepository.save(problemTest);
        log.info("Saved test - id:" + problemTest.getId());
        List<Test> problemtests = new ArrayList<>();
        problemtests.add(problemTest);
        Test solutionTest = new Test("solutionTest", "Use Scanner object", "oi", "ei", Visibility.PUBLIC);
        testRepository.save(solutionTest);
        log.info("Saved test - id:" + solutionTest.getId());
        List<Test> solutiontests = new ArrayList<>();
        solutiontests.add(solutionTest);

        Problem problem = new Problem(pablo, "Print input", "Print the program input", "Use Scanner object", problemtests, Visibility.PUBLIC);
        problemRepository.save(problem);
        log.info("Saved problem - id:" + problem.getId());

        String code = "print \"ei\"";
        Solution solution = new Solution(franklin, code, problem, solutiontests);
        solutionRepository.save(solution);
        log.info("Saved Mug - id:" + solution.getId());
    }
}

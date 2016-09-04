package com.ufcg.loaders;

import com.ufcg.Utils.UserType;
import com.ufcg.Utils.Visibility;
import com.ufcg.models.*;
import com.ufcg.repositories.*;
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
    private OutputSolutionRepository outputSolutionRepository;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setProductRepository(SolutionRepository solutionRepository,
                                     UserRepository userRepository,
                                     ProblemRepository problemRepository,
                                     TestRepository testRepository,
                                     OutputSolutionRepository outputSolutionRepository) {
        this.solutionRepository = solutionRepository;
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.testRepository = testRepository;
        this.outputSolutionRepository = outputSolutionRepository;
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
        List<Test> problemtests = new ArrayList<>();
        problemtests.add(problemTest);

        Problem problem = new Problem(pablo, "Print input", "Print the program input", "Use Scanner object", problemtests, Visibility.PUBLIC);
        problemRepository.save(problem);
        log.info("Saved problem - id:" + problem.getId());

        OutputSolution solutionTest = new OutputSolution("oi", "ei");
        List<OutputSolution> solutiontests = new ArrayList<>();
        solutiontests.add(solutionTest);

        String code = "print \"ei\"";
        Solution solution = new Solution(franklin, code, problem, solutiontests);
        solutionRepository.save(solution);
        log.info("Saved Mug - id:" + solution.getId());
    }
}

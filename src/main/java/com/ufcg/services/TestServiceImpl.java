package com.ufcg.services;

import com.ufcg.models.Test;
import com.ufcg.repositories.ProblemRepository;
import com.ufcg.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    ProblemService problemService;

    @Override
    public Test findById(Long id) {
        return testRepository.findOne(id);
    }

    @Override
    public void createTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void createTest(Long problemId, Test test) {
        createTest(test);
        problemService.addTestInProblem(problemId, test);
    }

    @Override
    public void updateTest(Long problemId, Test test) {
        if (isTestExist(problemId, test)) {
            testRepository.save(test);
        }
    }

    @Override
    public void deleteTest(Test test) {
        testRepository.delete(test);
    }

    @Override
    public boolean isTestExist(Long problemId, Test test) {
        boolean result = false;
        if (problemService.isProblemExist(problemId)) {
            result = testRepository.exists(test.getId());
        }
        return result;
    }
}

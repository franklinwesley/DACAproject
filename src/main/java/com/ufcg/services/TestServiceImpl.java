package com.ufcg.services;

import com.ufcg.models.Test;
import com.ufcg.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Override
    public Test findById(Long id) {
        return testRepository.findOne(id);
    }

    @Override
    public void createTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void updateTest(Test test) {
        if (isTestExist(test)) {
            testRepository.save(test);
        }
    }

    @Override
    public void deleteTest(Test test) {
        testRepository.delete(test);
    }

    @Override
    public List<Test> findAllTestsOfProblem(Long problemId) {
        return testRepository.findByProblem(problemId);
    }

    @Override
    public boolean isTestExist(Test test) {
        return testRepository.exists(test.getId());
    }
}

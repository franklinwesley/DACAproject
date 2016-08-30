package com.ufcg.services;

import com.ufcg.models.Test;
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

    @Override
    public Test findById(Long problemId, Long id) {
        return testRepository.findOne(id);
    }

    @Override
    public void createTest(Long problemId, Test test) {
        testRepository.save(test);
    }

    @Override
    public void updateTest(Long problemId, Test test) {
        if (isTestExist(problemId, test)) {
            testRepository.save(test);
        }
    }

    @Override
    public void deleteTest(Long problemId, Test test) {
        testRepository.delete(test);
    }

    @Override
    public List<Test> findAllTestsOfProblem(Long problemId) {
        return testRepository.findAll();
    }

    @Override
    public boolean isTestExist(Long problemId, Test test) {
        return testRepository.exists(test.getId());
    }
}

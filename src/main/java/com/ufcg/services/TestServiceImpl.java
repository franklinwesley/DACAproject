package com.ufcg.services;

import com.ufcg.models.Test;
import com.ufcg.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public Test findById(Long problemId, Long id) {
        return problemRepository.findOneTest(problemId, id);
    }

    @Override
    public void createTest(Long problemId, Test test) {
//        problemRepository.save(problemId, test);
    }

    @Override
    public void updateTest(Long problemId, Test test) {
        if (isTestExist(problemId, test)) {
//            problemRepository.save(problemId, test);
        }
    }

    @Override
    public void deleteTest(Long problemId, Test test) {
//        problemRepository.deleteTest(problemId, test);
    }

    @Override
    public List<Test> findAllTestsOfProblem(Long problemId) {
        return problemRepository.findAllTests(problemId);
    }

    @Override
    public boolean isTestExist(Long problemId, Test test) {
        boolean result = false;
        if (problemRepository.exists(problemId)) {
            result = problemRepository.existsTest(problemId,test.getId());
        }
        return result;
    }
}

package com.ufcg.services;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public Test findById(Long problemId, Long id) {
        return new Test(id, "name", "tip", new HashMap<>(), Visibility.PUBLIC);
    }

    @Override
    public void createTest(Long problemId, Test test) {

    }

    @Override
    public void updateTest(Long problemId, Test test) {

    }

    @Override
    public void deleteTest(Long problemId, Test test) {

    }

    @Override
    public List<Test> findAllTestsOfProblem(Long problemId) {
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tests.add(new Test((long) i, "name", "tip", new HashMap<>(), Visibility.PUBLIC));
        }
        return tests;
    }

    @Override
    public boolean isTestExist(Long problemId, Test test) {
        return false;
    }
}

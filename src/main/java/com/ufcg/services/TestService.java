package com.ufcg.services;

import com.ufcg.models.Test;

import java.util.List;

public interface TestService {

    Test findById(Long id);
    void createTest(Test test);
    void updateTest(Test test);
    void deleteTest(Test test);
    List<Test> findAllTestsOfProblem(Long problemId);
    boolean isTestExist(Test test);
}

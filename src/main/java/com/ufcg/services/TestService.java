package com.ufcg.services;

import com.ufcg.models.Test;

import java.util.List;

public interface TestService {

    Test findById(Long id);
    void createTest(Test test);
    void createTest(Long problemId, Test test);
    void updateTest(Long problemId, Test test);
    void deleteTest(Test test);
    boolean isTestExist(Long problemId, Test test);
}

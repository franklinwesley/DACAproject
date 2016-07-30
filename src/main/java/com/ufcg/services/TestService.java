package com.ufcg.services;

import com.ufcg.models.Test;

import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
public interface TestService {

    Test findById(Long problemId,Long id);
    void createTest(Long problemId, Test test);
    void updateTest(Long problemId, Test test);
    void deleteTest(Long problemId, Test test);
    List<Test> findAllTestsOfProblem(Long problemId);
    boolean isTestExist(Long problemId, Test test);
}

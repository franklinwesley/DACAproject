package com.ufcg.services;

import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProblemService {

    Problem findById(Long id);
    void createProblem(Problem problem);
    void updateProblem(Long problemId, Problem problem);
    void deleteProblem(Problem problem);
    Page<Problem> findAllProblems(int page, int size, String sort);
    boolean isProblemExist(Problem problem);
    boolean isProblemExist(Long problemId);
    boolean publishProblem(Problem problem);
    void addTestInProblem(Long problemId, Test test);
    void deleteUserProblems(Long userId);
}

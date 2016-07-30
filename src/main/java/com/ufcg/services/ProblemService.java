package com.ufcg.services;

import com.ufcg.models.Problem;

import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
public interface ProblemService {

    Problem findById(Long id);
    void createProblem(Problem problem);
    void updateProblem(Problem problem);
    void deleteProblem(Problem problem);
    List<Problem> findAllProblems(int page, String sort, Long user);
    boolean isProblemExist(Problem problem);
    boolean publishProblem(Problem problem);
}

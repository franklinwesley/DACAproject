package com.ufcg.services;

import com.ufcg.models.Problem;
import org.springframework.data.domain.Page;

public interface ProblemService {

    Problem findById(Long id);
    void createProblem(Problem problem);
    void updateProblem(Problem problem);
    void deleteProblem(Problem problem);
    Page<Problem> findAllProblems(int page, String sort, Long user);
    boolean isProblemExist(Problem problem);
    boolean publishProblem(Problem problem);
}

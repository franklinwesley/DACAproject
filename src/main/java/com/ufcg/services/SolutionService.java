package com.ufcg.services;

import com.ufcg.models.Solution;

import java.util.List;

public interface SolutionService {

    Solution findById(Long id);
    void createSolution(Solution solution);
    void updateSolution(Solution solution);
    void deleteSolution(Solution solution);
    List<Solution> findAllSolutionsOfProblem(Long problemId);
    boolean isSolutionExist(Solution solution);
}

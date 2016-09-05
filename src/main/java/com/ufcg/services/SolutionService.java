package com.ufcg.services;

import com.ufcg.models.Problem;
import com.ufcg.models.Solution;

import java.util.List;

public interface SolutionService {

    Solution findById(Long id);
    void createSolution(Solution solution);
    void updateSolution(Solution solution);
    void deleteSolution(Solution solution);
    List<Solution> findAllSolutionsOfProblem(Long problemId);
    boolean isSolutionExist(Solution solution);
    List<Problem> userProblemsResolved(Long userId);
    int userProblemsResolvedNumber(Long userId);
    int problemsResolved();
    int userSubmitting();
    void deleteUserSolutions(Long userId);
}

package com.ufcg.services;

import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
@Service("solutionService")
public class SolutionServiceImpl implements SolutionService {
    @Override
    public Solution findById(Long id) {
        return new Solution(id, "code", new Problem(), new HashMap<>());
    }

    @Override
    public void createSolution(Solution solution) {

    }

    @Override
    public void updateSolution(Solution solution) {

    }

    @Override
    public void deleteSolution(Solution solution) {

    }

    @Override
    public List<Solution> findAllSolutionsOfProblem(Long problemId) {
        List<Solution> solutions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            solutions.add(new Solution((long) i, "code", new Problem(), new HashMap<>()));
        }
        return solutions;
    }

    @Override
    public boolean isSolutionExist(Solution solution) {
        return false;
    }
}

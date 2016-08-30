package com.ufcg.services;

import com.ufcg.models.Solution;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("solutionService")
@Transactional
public class SolutionServiceImpl implements SolutionService {
    @Override
    public Solution findById(Long id) {
        return new Solution(id, "code", id, new HashMap<>());
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
            solutions.add(new Solution((long) i, "code", (long) i, new HashMap<>()));
        }
        return solutions;
    }

    @Override
    public boolean isSolutionExist(Solution solution) {
        return false;
    }
}

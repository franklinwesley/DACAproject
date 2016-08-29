package com.ufcg.services;

import com.ufcg.models.Solution;
import com.ufcg.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("solutionService")
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    SolutionRepository solutionRepository;

    @Override
    public Solution findById(Long id) {
        return solutionRepository.findOne(id);
    }

    @Override
    public void createSolution(Solution solution) {
        solutionRepository.save(solution);
    }

    @Override
    public void updateSolution(Solution solution) {
        if (isSolutionExist(solution)){
            solutionRepository.save(solution);
        }
    }

    @Override
    public void deleteSolution(Solution solution) {
        solutionRepository.delete(solution);
    }

    @Override
    public List<Solution> findAllSolutionsOfProblem(Long problemId) {
        return solutionRepository.findAll();
    }

    @Override
    public boolean isSolutionExist(Solution solution) {
        return solutionRepository.exists(solution.getId());
    }
}

package com.ufcg.services;

import com.ufcg.models.OutputSolution;
import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import com.ufcg.repositories.OutputSolutionRepository;
import com.ufcg.repositories.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("solutionService")
@Transactional
public class SolutionServiceImpl implements SolutionService {

    @Autowired
    SolutionRepository solutionRepository;

    @Autowired
    OutputSolutionRepository outputSolutionRepository;

    @Override
    public Solution findById(Long id) {
        return solutionRepository.findOne(id);
    }

    @Override
    public void createSolution(Solution solution) {
        for (OutputSolution outputSolution : solution.getInputsOutputs()) {
            outputSolutionRepository.save(outputSolution);
        }
        solutionRepository.save(solution);
    }

    @Override
    public void updateSolution(Solution solution) {
        if (isSolutionExist(solution)) {
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
        return solutionRepository.existsByCode(solution.getCode());
    }

    @Override
    public List<Problem> userProblemsResolved(Long userId) {
        return solutionRepository.userProblemsResolved(true, userId);
    }

    @Override
    public int problemsResolved() {
        return solutionRepository.countDistinctProblemsByResolved(true);
    }

    @Override
    public int userProblemsResolvedNumber(Long userId) {
        return solutionRepository.countDistinctProblemsByResolvedAndCreator(true, userId);
    }

    @Override
    public int userSubmitting() {
        return solutionRepository.countDistinctCreators();
    }
}

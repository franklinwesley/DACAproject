package com.ufcg.services;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("problemService")
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Override
    public Problem findById(Long id) {
        return problemRepository.findOne(id);
    }

    @Override
    public void createProblem(Problem problem) {
        problemRepository.save(problem);
    }

    @Override
    public void updateProblem(Problem problem) {
        if (isProblemExist(problem)) {
            problemRepository.save(problem);
        }
    }

    @Override
    public void deleteProblem(Problem problem) {
        problemRepository.delete(problem);
    }

    @Override
    public List<Problem> findAllProblems(int page, String sort, Long user) {
        return problemRepository.findAll();
    }

    @Override
    public boolean isProblemExist(Problem problem) {
        return problemRepository.exists(problem.getId());
    }

    @Override
    public boolean publishProblem(Long problemId) {
        return problemRepository.updateType(problemId, Visibility.PUBLIC);
    }
}

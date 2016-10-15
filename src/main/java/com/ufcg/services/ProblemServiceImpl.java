package com.ufcg.services;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import com.ufcg.repositories.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    SolutionService solutionService;

    @Autowired
    TestService testService;

    @Override
    public Problem findById(Long id) {
        return problemRepository.findOne(id);
    }

    @Override
    public void createProblem(Problem problem) {
        problemRepository.save(problem);
    }

    @Override
    public void updateProblem(Long idProblem, Problem problem) {
        if (problemRepository.exists(problem.getId())) {
            problemRepository.save(problem);
        }
    }

    @Override
    public void deleteProblem(Problem problem) {
        solutionService.deleteProblemSolutions(problem.getId());
        problemRepository.delete(problem);
    }

    @Override
    public Page<Problem> findAllProblems(int page, int size, String sort) {
        return problemRepository.findAll(new PageRequest(page-1, size, new Sort(sort)));
    }

    @Override
    public boolean isProblemExist(Problem problem) {
        return problemRepository.exists(problem.getId());
    }

    @Override
    public boolean isProblemExist(Long problemId) {
        return problemRepository.exists(problemId);
    }

    @Override
    public boolean publishProblem(Problem problem) {
        boolean result = false;
        int updateType = problemRepository.updateType(problem.getId(), Visibility.PUBLIC);
        if (updateType > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public void addTestInProblem(Long problemId, Test test) {
        if (isProblemExist(problemId)) {
            Problem problem = findById(problemId);
            problem.getTests().add(test);
            updateProblem(problemId,problem);
        }
    }

    @Override
    public void deleteUserProblems(Long userId) {
        problemRepository.deleteAll(userId);
    }
}

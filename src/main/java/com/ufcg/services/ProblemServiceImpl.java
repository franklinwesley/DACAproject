package com.ufcg.services;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by franklin on 29/07/16.
 */
@Service("problemService")
public class ProblemServiceImpl implements ProblemService {

    @Override
    public Problem findById(Long id) {
        return new Problem(id, "Problem " + id, "description", "tip", new ArrayList<>(), Visibility.PRIVATE);
    }

    @Override
    public void createProblem(Problem problem) {

    }

    @Override
    public void updateProblem(Problem problem) {

    }

    @Override
    public void deleteProblem(Problem problem) {

    }

    @Override
    public List<Problem> findAllProblems(int page, String sort) {
        List<Problem> problems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            problems.add(new Problem((long) i, "Problem " + i, "description", "tip", new ArrayList<>(), Visibility.PRIVATE));
        }
        return problems;
    }

    @Override
    public boolean isProblemExist(Problem problem) {
        return false;
    }

    @Override
    public boolean publishProblem(Problem problem) {
        return true;
    }
}

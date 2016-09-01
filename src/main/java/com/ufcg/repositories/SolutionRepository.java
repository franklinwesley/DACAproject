package com.ufcg.repositories;

import com.ufcg.models.Solution;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolutionRepository extends CrudRepository<Solution,Long> {
    List<Solution> findAll();
//    int countDistinctProblemsByResolved(boolean resolved);
//    int countDistinctProblemsByResolvedAndCreator(boolean resolved, Long userId);
    int countDistinctCreators();
}

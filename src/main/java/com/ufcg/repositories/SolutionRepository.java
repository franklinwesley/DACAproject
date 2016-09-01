package com.ufcg.repositories;

import com.ufcg.models.Solution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolutionRepository extends CrudRepository<Solution,Long> {
    List<Solution> findAll();

    @Query("select count(distinct s.creator) from Solution s where s.resolved=?1")
    int countDistinctProblemsByResolved(boolean resolved);

    @Query("select count(distinct s.problem) from Solution s where s.resolved=?1 and s.creator=?2")
    int countDistinctProblemsByResolvedAndCreator(boolean resolved, Long userId);

    @Query("select count(distinct s.creator) from Solution s")
    int countDistinctCreators();
}

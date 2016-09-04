package com.ufcg.repositories;

import com.ufcg.models.Problem;
import com.ufcg.models.Solution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolutionRepository extends CrudRepository<Solution,Long> {
    List<Solution> findAll();

    @Query("select count(distinct s.creator) from Solution s where s.resolvedProblem=?1")
    int countDistinctProblemsByResolved(boolean resolved);

    @Query("select count(distinct s.problem) from Solution s where s.resolvedProblem=?1 and s.creator.id=?2")
    int countDistinctProblemsByResolvedAndCreator(boolean resolved, Long userId);

    @Query("select count(distinct s.creator) from Solution s")
    int countDistinctCreators();

    @Query("select s.problem from Solution s where s.creator.id=?2 and s.resolvedProblem=?1")
    List<Problem> userProblemsResolved(boolean resolved, Long userId);

    @Query("select case when count(s) > 0 then 'true' else 'false' end from Solution s where s.code = ?1")
    boolean existsByCode(String code);
}

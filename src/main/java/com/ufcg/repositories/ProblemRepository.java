package com.ufcg.repositories;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem,Long>{
    List<Problem> findAll();

    @Query("update Problem p set p.type=?2 where p.id=?1")
    int updateType(Long problemId, Visibility type);
}

package com.ufcg.repositories;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    List<Problem> findAll();
    boolean updateType(Long id, Visibility type);
}

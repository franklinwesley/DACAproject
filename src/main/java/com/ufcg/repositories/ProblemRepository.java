package com.ufcg.repositories;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProblemRepository extends PagingAndSortingRepository<Problem,Long> {
    Page<Problem> findAll(Pageable pageable);

    @Modifying
    @Query("update Problem p set p.type=?2 where p.id=?1")
    int updateType(Long problemId, Visibility type);

    @Query("select p.tests from Problem p where p.id=?1")
    List<Test> getTests(Long problemId);

    @Query("delete from Problem p where p.creator.id=?1")
    void deleteAll(Long userId);
}

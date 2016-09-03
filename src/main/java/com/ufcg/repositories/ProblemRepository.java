package com.ufcg.repositories;

import com.ufcg.Utils.Visibility;
import com.ufcg.models.Problem;
import com.ufcg.models.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProblemRepository extends PagingAndSortingRepository<Problem,Long> {
    Page<Problem> findAll(Pageable pageable);

    @Query("update Problem p set p.type=?2 where p.id=?1")
    int updateType(Long problemId, Visibility type);

    @Query("select t from Problem p,Test t where p.id=?1 and t.id=?2")
    Test findOneTest(Long problemId, Long testId);

    @Query("select t from Problem p,Test t where p.id=?1")
    List<Test> findAllTests(Long problemId);

    @Query("select case when count(t) > 0 then 'true' else 'false' end from Problem p,Test t where p.id = ?1 and t.id=?2")
    boolean existsTest(Long problemId, Long testId);

//    @Query("delete from Problem p join Test t on t.id in p.tests where p.id=?1 and t.id=?2.id")
//    void deleteTest(Long problemId, Test test);

//    @Query("update Test set t=?2 from Problem p,Test t where p.id=?1 and t.id=?2.id and ?2.id in p.tests")
//    void updateTest(Long problemId, Test test);
}

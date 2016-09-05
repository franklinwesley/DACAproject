package com.ufcg.repositories;

import com.ufcg.models.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Long> {
}

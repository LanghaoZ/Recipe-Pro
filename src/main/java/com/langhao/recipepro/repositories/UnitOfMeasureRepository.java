package com.langhao.recipepro.repositories;

import com.langhao.recipepro.domain.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}

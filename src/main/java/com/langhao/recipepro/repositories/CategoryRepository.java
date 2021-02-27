package com.langhao.recipepro.repositories;

import org.springframework.data.repository.CrudRepository;
import com.langhao.recipepro.domain.Category;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}

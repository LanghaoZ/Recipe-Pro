package com.langhao.recipepro.repositories;

import org.springframework.data.repository.CrudRepository;
import com.langhao.recipepro.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

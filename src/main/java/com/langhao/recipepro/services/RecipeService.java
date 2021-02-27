package com.langhao.recipepro.services;

import com.langhao.recipepro.domain.Recipe;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
}

package com.langhao.recipepro.services;

import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.RecipeDto;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeDto saveRecipeDto(RecipeDto dto);
    RecipeDto findDtoById(Long id);
}

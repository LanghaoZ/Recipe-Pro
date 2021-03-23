package com.langhao.recipepro.services;

import com.langhao.recipepro.domain.Ingredient;
import com.langhao.recipepro.dto.IngredientDto;

public interface IngredientService {

    IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long id);

    IngredientDto saveIngredientDto(IngredientDto dto);

    void deleteById(Long recipeId, Long ingredientId);
}

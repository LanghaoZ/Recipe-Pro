package com.langhao.recipepro.services;

import com.langhao.recipepro.dto.IngredientDto;

public interface IngredientService {

    IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long id);
}

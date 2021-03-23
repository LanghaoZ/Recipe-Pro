package com.langhao.recipepro.services;

import com.langhao.recipepro.converters.IngredientToIngredientDto;
import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.IngredientDto;
import com.langhao.recipepro.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientDto ingredientToIngredientDto;
    RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientDto ingredientToIngredientDto,
                                 RecipeRepository recipeRepository) {
        this.ingredientToIngredientDto = ingredientToIngredientDto;
        this.recipeRepository = recipeRepository;

    }

    @Override
    public IngredientDto findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (!recipeOptional.isPresent()) {
            // TODO: Implement error handling
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientDto> ingredientDtoOptional = recipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientId))
                        .map(ingredient -> ingredientToIngredientDto.convert(ingredient))
                        .findFirst();

        if (!ingredientDtoOptional.isPresent()) {
            // TODO: Implement error handling
        }

        return ingredientDtoOptional.get();
    }
}

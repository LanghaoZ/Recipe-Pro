package com.langhao.recipepro.services;

import com.langhao.recipepro.converters.IngredientDtoToIngredient;
import com.langhao.recipepro.converters.IngredientToIngredientDto;
import com.langhao.recipepro.domain.Ingredient;
import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.IngredientDto;
import com.langhao.recipepro.repositories.RecipeRepository;
import com.langhao.recipepro.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientDto ingredientToIngredientDto;
    private final IngredientDtoToIngredient ingredientDtoToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(IngredientToIngredientDto ingredientToIngredientDto,
                                 IngredientDtoToIngredient ingredientDtoToIngredient,
                                 RecipeRepository recipeRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientToIngredientDto = ingredientToIngredientDto;
        this.ingredientDtoToIngredient = ingredientDtoToIngredient;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
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

    @Override
    @Transactional
    public IngredientDto saveIngredientDto(IngredientDto dto) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(dto.getRecipeId());
        if (!recipeOptional.isPresent()) {
            // TODO: Add error handler
            return new IngredientDto();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(dto.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(dto.getDescription());
                ingredientFound.setAmount(dto.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository.findById(dto.getUom().getId()).orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
            } else {
                recipe.addIngredient(ingredientDtoToIngredient.convert(dto));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            return ingredientToIngredientDto.convert(savedRecipe.getIngredients().stream()
                            .filter(recipeIngredients -> recipeIngredients.getId().equals(dto.getId()))
                            .findFirst().get());
        }

    }
}

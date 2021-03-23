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

import javax.swing.text.html.Option;
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
                Ingredient ingredient = ingredientDtoToIngredient.convert(dto);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(recipeIngredient -> recipeIngredient.getId().equals(dto.getId()))
                    .findFirst();

            // Check by description. Above filters by ingredient id.
            // But in case of CREATE, the ingredient does not yet have
            // an id. Hence, we fall back to filter by description,
            // amount nad UOM ID. This is not safe. Need to fix in future.
            if (!savedIngredientOptional.isPresent()) {
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredient -> recipeIngredient.getDescription().equals(dto.getDescription()))
                        .filter(recipeIngredient -> recipeIngredient.getAmount().equals(dto.getAmount()))
                        .filter(recipeIngredient -> recipeIngredient.getUom().getId().equals(dto.getUom().getId()))
                        .findFirst();
            }

            return ingredientToIngredientDto.convert(savedIngredientOptional.get());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientToDelete = ingredientOptional.get();
                ingredientToDelete.setRecipe(null);
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            // TODO: Add logger
        }
    }
}

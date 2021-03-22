package com.langhao.recipepro.services;

import com.langhao.recipepro.converters.RecipeDtoToRecipe;
import com.langhao.recipepro.converters.RecipeToRecipeDto;
import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.RecipeDto;
import com.langhao.recipepro.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RecipeServiceIT {

    public static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeDtoToRecipe recipeDtoToRecipe;

    @Autowired
    RecipeToRecipeDto recipeToRecipeDto;

    @Transactional
    @Test
    public void testSaveOfDescription() throws Exception {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeDto testRecipeDto = recipeToRecipeDto.convert(testRecipe);

        testRecipeDto.setDescription(NEW_DESCRIPTION);
        RecipeDto savedRecipeCommand = recipeService.saveRecipeDto(testRecipeDto);

        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }
}

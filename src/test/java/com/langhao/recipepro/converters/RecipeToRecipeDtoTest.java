package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.*;
import com.langhao.recipepro.dto.RecipeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeToRecipeDtoTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;
    RecipeToRecipeDto converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new RecipeToRecipeDto(
                new CategoryToCategoryDto(),
                new IngredientToIngredientDto(new UnitOfMeasureToUnitOfMeasureDto()),
                new NotesToNotesDto());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setNotes(new Notes());
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(RECIPE_ID);
        recipe.setCookTime(COOK_TIME);
        recipe.setPrepTime(PREP_TIME);
        recipe.setDescription(DESCRIPTION);
        recipe.setDifficulty(DIFFICULTY);
        recipe.setDirections(DIRECTIONS);
        recipe.setServings(SERVINGS);
        recipe.setSource(SOURCE);
        recipe.setUrl(URL);

        Notes notes = new Notes();
        notes.setId(NOTES_ID);

        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(CAT_ID_1);

        Category category2 = new Category();
        category2.setId(CAT_ID2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(INGRED_ID_1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(INGRED_ID_2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);
        
        RecipeDto dto = converter.convert(recipe);

        assertNotNull(dto);
        assertEquals(RECIPE_ID, dto.getId());
        assertEquals(COOK_TIME, dto.getCookTime());
        assertEquals(PREP_TIME, dto.getPrepTime());
        assertEquals(DESCRIPTION, dto.getDescription());
        assertEquals(DIFFICULTY, dto.getDifficulty());
        assertEquals(DIRECTIONS, dto.getDirections());
        assertEquals(SERVINGS, dto.getServings());
        assertEquals(SOURCE, dto.getSource());
        assertEquals(URL, dto.getUrl());
        assertEquals(NOTES_ID, dto.getNotes().getId());
        assertEquals(2, dto.getCategories().size());
        assertEquals(2, dto.getIngredients().size());
    }
}

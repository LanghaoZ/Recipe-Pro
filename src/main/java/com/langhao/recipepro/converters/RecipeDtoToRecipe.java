package com.langhao.recipepro.converters;

import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.RecipeDto;
import com.sun.istack.Nullable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeDtoToRecipe implements Converter<RecipeDto, Recipe> {

    private final CategoryDtoToCategory categoryConverter;
    private final IngredientDtoToIngredient ingredientConverter;
    private final NotesDtoToNotes notesConverter;

    public RecipeDtoToRecipe(CategoryDtoToCategory categoryConverter,
                             IngredientDtoToIngredient ingredientConverter,
                             NotesDtoToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    @Nullable
    public synchronized Recipe convert(RecipeDto source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories().forEach(category ->
                    recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients().forEach(ingredient ->
                    recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}

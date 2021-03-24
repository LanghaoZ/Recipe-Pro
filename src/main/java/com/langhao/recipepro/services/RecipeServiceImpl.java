package com.langhao.recipepro.services;

import com.langhao.recipepro.converters.RecipeDtoToRecipe;
import com.langhao.recipepro.converters.RecipeToRecipeDto;
import com.langhao.recipepro.domain.Recipe;
import com.langhao.recipepro.dto.RecipeDto;
import com.langhao.recipepro.exceptions.NotFoundException;
import com.langhao.recipepro.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeDto recipeToRecipeDto;
    private final RecipeDtoToRecipe recipeDtoToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeToRecipeDto recipeToRecipeDto,
                             RecipeDtoToRecipe recipeDtoToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeDto = recipeToRecipeDto;
        this.recipeDtoToRecipe = recipeDtoToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found: recipe with id " +
                    id.toString() + " does not exist.");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeDto saveRecipeDto(RecipeDto dto) {
        Recipe detachedRecipe = recipeDtoToRecipe.convert(dto);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        return recipeToRecipeDto.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeDto findDtoById(Long id) {
        return recipeToRecipeDto.convert(findById(id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}

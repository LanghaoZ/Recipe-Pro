package com.langhao.recipepro.controllers;

import com.langhao.recipepro.dto.RecipeDto;
import com.langhao.recipepro.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/recipeViewMain";
    }

    @GetMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDto());

        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("/recipe/update/{id}")
    public String newRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findDtoById(Long.valueOf(id)));

        return "recipe/recipeForm";
    }
    
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeDto dto) {
        RecipeDto savedDto = recipeService.saveRecipeDto(dto);

        return "redirect:/recipe/show/" + savedDto.getId();
    }

    @GetMapping("/recipe/delete/{id}")
    public String deleteRecipe(@PathVariable String id) {
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}

package emg.springframework.sfg.recipes.services;

import emg.springframework.sfg.recipes.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}

package emg.springframework.sfg.recipes.services;

import emg.springframework.sfg.recipes.domain.Recipe;
import emg.springframework.sfg.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {
        var mockrecipe = new Recipe();
        var mockRecipeData = new HashSet<Recipe>();
        mockRecipeData.add(mockrecipe);
        when(recipeRepository.findAll()).thenReturn(mockRecipeData);

        var recipes = recipeService.getRecipes();
        assertThat(recipes.size()).isEqualTo(1);

        verify(recipeRepository, times(1)).findAll();
    }
}
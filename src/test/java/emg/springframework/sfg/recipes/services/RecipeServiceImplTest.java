package emg.springframework.sfg.recipes.services;

import emg.springframework.sfg.recipes.converters.RecipeCommandToRecipe;
import emg.springframework.sfg.recipes.converters.RecipeToRecipeCommand;
import emg.springframework.sfg.recipes.domain.Recipe;
import emg.springframework.sfg.recipes.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipes() {
        var mockrecipe = new Recipe();
        var mockRecipeData = new HashSet<Recipe>();
        mockRecipeData.add(mockrecipe);
        //when(recipeRepository.findAll()).thenReturn(mockRecipeData);
        when(recipeRepository.findAllByOrderByIdDesc()).thenReturn(mockRecipeData);

        var recipes = recipeService.getRecipes();
        assertThat(recipes.size()).isEqualTo(1);

        //verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, times(1)).findAllByOrderByIdDesc();
    }

    @Test
    public void testDeleteById() throws Exception {
        Long idToDelete = 1L;
        recipeService.deleteById(idToDelete);
        verify(recipeRepository, times(1)).deleteById(anyLong());

    }
}
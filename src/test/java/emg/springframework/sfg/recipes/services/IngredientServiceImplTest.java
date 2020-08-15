package emg.springframework.sfg.recipes.services;

import emg.springframework.sfg.recipes.commands.IngredientCommand;
import emg.springframework.sfg.recipes.converters.IngredientCommandToIngredient;
import emg.springframework.sfg.recipes.converters.IngredientToIngredientCommand;
import emg.springframework.sfg.recipes.converters.UnitOfMeasureCommandToUnitOfMeasure;
import emg.springframework.sfg.recipes.converters.UnitOfMeasureToUnitOfMeasureCommand;
import emg.springframework.sfg.recipes.domain.Ingredient;
import emg.springframework.sfg.recipes.domain.Recipe;
import emg.springframework.sfg.recipes.repositories.RecipeRepository;
import emg.springframework.sfg.recipes.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }

    @Test
    public void findByRecipeIdAndReceipeIdHappyPath() throws Exception {
        //given
        var recipe = new Recipe();
        recipe.setId(1L);

        var ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        var ingredient2 = new Ingredient();
        ingredient2.setId(1L);

        var ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        var recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        //then
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

        //when
        assertThat(ingredientCommand.getId()).isEqualTo(3L);
        assertThat(ingredientCommand.getRecipeId()).isEqualTo(1L);
        verify(recipeRepository, times(1)).findById(anyLong());
    }
}

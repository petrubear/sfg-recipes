package emg.springframework.sfg.recipes.controllers;

import emg.springframework.sfg.recipes.domain.Recipe;
import emg.springframework.sfg.recipes.repositories.CategoryRepository;
import emg.springframework.sfg.recipes.repositories.UnitOfMeasureRepository;
import emg.springframework.sfg.recipes.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    IndexController indexController;

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(categoryRepository,
            unitOfMeasureRepository, recipeService);

        when(categoryRepository.findByDescription(anyString())).thenReturn(Optional.empty());
        when(unitOfMeasureRepository.findByDescription(anyString())).thenReturn(Optional.empty());
        var mockRecipe = new Recipe();
        mockRecipe.setId(1L);
        var anotherMockRecipe = new Recipe();
        anotherMockRecipe.setId(2L);
        var mockRecipes = new HashSet<Recipe>();
        mockRecipes.add(mockRecipe);
        mockRecipes.add(anotherMockRecipe);
        when(recipeService.getRecipes()).thenReturn(mockRecipes);
    }

    @Test
    void getIndexPage() {
        var expectedResult = "index";
        var result = indexController.getIndexPage(model);

        assertThat(result).isEqualTo(expectedResult);
        verify(categoryRepository, times(1)).findByDescription(anyString());
        verify(unitOfMeasureRepository, times(1)).findByDescription(anyString());
        verify(recipeService, times(1)).getRecipes();

        var setArgumentCaptor = ArgumentCaptor.forClass(Set.class);
        verify(model, times(1)).addAttribute(anyString(), setArgumentCaptor.capture());
        var recipes = setArgumentCaptor.getValue();
        assertThat(recipes.size()).isEqualTo(2);

    }


    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }
}
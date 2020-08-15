package emg.springframework.sfg.recipes.controllers;

import emg.springframework.sfg.recipes.repositories.CategoryRepository;
import emg.springframework.sfg.recipes.repositories.UnitOfMeasureRepository;
import emg.springframework.sfg.recipes.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeService recipeService;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                           RecipeService recipeService) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeService = recipeService;
    }

    @GetMapping( {"/", "", "/index"})
    public String getIndexPage(Model model) {
        var category = categoryRepository.findByDescription("American");
        var unit = unitOfMeasureRepository.findByDescription("Teaspoon");
        category.ifPresent(c -> log.info("category id = " + c.getId()));
        unit.ifPresent(u -> log.info("unit id = " + u.getId()));

        var recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}

package emg.springframework.sfg.recipes.controllers;

import emg.springframework.sfg.recipes.repositories.CategoryRepository;
import emg.springframework.sfg.recipes.repositories.RecipeRepository;
import emg.springframework.sfg.recipes.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping( {"/", "", "/index"})
    public String getIndexPage(Model model) {
        var category = categoryRepository.findByDescription("American");
        var unit = unitOfMeasureRepository.findByDescription("Teaspoon");
        //category.ifPresent(c -> System.out.println("category id = " + c.getId()));
        //unit.ifPresent(u -> System.out.println("unit id = " + u.getId()));

        var recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        return "index";
    }
}

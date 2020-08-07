package emg.springframework.sfg.recipes.repositories;

import emg.springframework.sfg.recipes.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

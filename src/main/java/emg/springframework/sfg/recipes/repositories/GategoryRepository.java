package emg.springframework.sfg.recipes.repositories;

import emg.springframework.sfg.recipes.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface GategoryRepository extends CrudRepository<Category, Long> {
}

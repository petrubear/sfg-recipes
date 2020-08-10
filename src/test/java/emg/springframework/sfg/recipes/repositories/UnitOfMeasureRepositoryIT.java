package emg.springframework.sfg.recipes.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DirtiesContext
        // limpia el contexto (no debe usarse a menos que se muy necesario
    void findByDescription() {
        var uomTeaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertThat(uomTeaspoon.get().getDescription()).isEqualTo("Teaspoon");
    }

    @Test
    void findByDescriptionCup() {
        var uomCup = unitOfMeasureRepository.findByDescription("Cup");
        assertThat(uomCup.get().getDescription()).isEqualTo("Cup");
    }
}
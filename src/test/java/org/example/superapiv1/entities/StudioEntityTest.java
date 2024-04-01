package org.example.superapiv1.entities;

import org.example.superapiv1.repositories.StudioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudioEntityTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private StudioRepository studioRepository;

    @Test
    public void shouldSaveAndRetrieveStudio() {
        Studio studio = new Studio();
        studio.setName("Warner Bros");

        Studio savedStudio = testEntityManager.persistFlushFind(studio);

        Studio foundStudio = studioRepository.findById(savedStudio.getId()).orElse(null);

        assertThat(foundStudio).isNotNull();
        assertThat(foundStudio.getName()).isEqualTo(savedStudio.getName());
    }
}

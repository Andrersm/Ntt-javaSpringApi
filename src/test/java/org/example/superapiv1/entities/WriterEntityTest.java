package org.example.superapiv1.entities;

import org.example.superapiv1.repositories.WriterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class WriterEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private WriterRepository writerRepository;

    @Test
    public void shouldSaveAndRetrieveWriter() {
        Writer writer = new Writer();
        writer.setName("Jane Doe");

        Writer savedWriter = testEntityManager.persistFlushFind(writer);


        Writer foundWriter = writerRepository.findById(savedWriter.getId()).orElse(null);


        assertThat(foundWriter).isNotNull();
        assertThat(foundWriter.getName()).isEqualTo(savedWriter.getName());
    }
}
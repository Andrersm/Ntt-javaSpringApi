package org.example.superapiv1.entities;

import org.example.superapiv1.repositories.StreamingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class StreamingEntityTest {

    @Autowired
    private StreamingRepository streamingRepository;

    @Test
    public void shouldSaveAndRetrieveStreaming() {
        Streaming streaming = new Streaming();
        streaming.setName("Netflix");

        Streaming savedStreaming = streamingRepository.save(streaming);

        Streaming foundStreaming = streamingRepository.findById(savedStreaming.getId()).orElse(null);

        assertThat(foundStreaming).isNotNull();
        assertThat(foundStreaming.getName()).isEqualTo(savedStreaming.getName());
    }


}


package org.example.superapiv1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DirectorEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldSaveAndRetrieveDirector() {
        Director newDirector = new Director();
        newDirector.setName("Christopher Nolan");
        newDirector.setFavoriteSport("Football");

        newDirector = testEntityManager.persistFlushFind(newDirector);

        Director foundDirector = testEntityManager.find(Director.class, newDirector.getId());

        assertThat(foundDirector.getName()).isEqualTo(newDirector.getName());
        assertThat(foundDirector.getFavoriteSport()).isEqualTo(newDirector.getFavoriteSport());
    }

    @Test
    @Transactional
    public void directorShouldBeLinkedToMovies() {
        Director director = new Director();
        director.setName("James Cameron");
        director.setFavoriteSport("Swimming");

        Director savedDirector = testEntityManager.persistFlushFind(director);

        Movie movie = new Movie();
        movie.setTitle("Test Movie 2");
        movie.setDuration(180);
        movie.setDirector(savedDirector);
        savedDirector.getDirectedMovies().add(movie);
        Movie savedMovie = testEntityManager.persistFlushFind(movie);

        Director foundDirector = testEntityManager.find(Director.class, savedDirector.getId());
        assertThat(foundDirector.getDirectedMovies()).isNotEmpty();
    }
}

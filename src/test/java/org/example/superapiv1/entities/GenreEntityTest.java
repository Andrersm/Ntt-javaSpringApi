package org.example.superapiv1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class GenreEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldSaveAndRetrieveGenre() {
        Genre newGenre = new Genre();
        newGenre.setName("Action");

        newGenre = testEntityManager.persistFlushFind(newGenre);

        Genre foundGenre = testEntityManager.find(Genre.class, newGenre.getId());

        assertThat(foundGenre.getName()).isEqualTo(newGenre.getName());
    }

    @Test
    @Transactional
    public void genreShouldBeLinkedToMovies() {
        Genre genre = new Genre();
        genre.setName("Sci-Fi");

        Genre savedGenre = testEntityManager.persistFlushFind(genre);

        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setDuration(169);
        movie.setGenre(savedGenre);
        savedGenre.getMovies().add(movie);
        Movie savedMovie = testEntityManager.persistFlushFind(movie);

        Genre foundGenre = testEntityManager.find(Genre.class, savedGenre.getId());
        assertThat(foundGenre.getMovies()).isNotEmpty();
    }
}

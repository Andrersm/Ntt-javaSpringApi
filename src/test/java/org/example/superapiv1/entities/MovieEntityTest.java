package org.example.superapiv1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void movieShouldBeLinkedToFranchiseGenreStudioAndStreaming() {
        Franchise franchise = new Franchise();
        franchise.setName("Marvel Cinematic Universe");
        testEntityManager.persistAndFlush(franchise);

        Genre genre = new Genre();
        genre.setName("Action");
        testEntityManager.persistAndFlush(genre);

        Studio studio = new Studio();
        studio.setName("Marvel Studios");
        testEntityManager.persistAndFlush(studio);

        Streaming streaming = new Streaming();
        streaming.setName("Netflix");
        testEntityManager.persistAndFlush(streaming);

        Director director = new Director();
        director.setName("Frank Darabont");
        director.setFavoriteSport("Basketball");
        testEntityManager.persistAndFlush(director);

        Actor actor = new Actor();
        actor.setName("Tim Robbins");
        actor.setAge(62);
        actor.setFavoriteFood("Pasta");
        testEntityManager.persistAndFlush(actor);


        Movie newMovie = new Movie();
        newMovie.setTitle("The Shawshank Redemption");
        newMovie.setDuration(142);
        newMovie.setImgUrl("shawshank_redemption.jpg");
        newMovie.setShortDescription("Teste");
        newMovie.setDirector(director);
        newMovie.getActors().add(actor);
        newMovie.setFranchise(franchise);
        newMovie.setGenre(genre);
        newMovie.setStudio(studio);
        newMovie.setStreaming(streaming);


        Movie savedMovie = testEntityManager.persistFlushFind(newMovie);


        assertThat(savedMovie.getFranchise()).isNotNull();
        assertThat(savedMovie.getGenre()).isNotNull();
        assertThat(savedMovie.getStudio()).isNotNull();
        assertThat(savedMovie.getStreaming()).isNotNull();
    }


}
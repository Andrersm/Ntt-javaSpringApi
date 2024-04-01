package org.example.superapiv1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ActorEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldSaveAndRetrieveActor() {

        Actor newActor = new Actor();
        newActor.setName("Tom Hanks");
        newActor.setAge(64);
        newActor.setFavoriteFood("Sushi");

        newActor = testEntityManager.persistFlushFind(newActor);

        Actor foundActor = testEntityManager.find(Actor.class, newActor.getId());

        assertThat(foundActor.getName()).isEqualTo(newActor.getName());
        assertThat(foundActor.getAge()).isEqualTo(newActor.getAge());
        assertThat(foundActor.getFavoriteFood()).isEqualTo(newActor.getFavoriteFood());
    }

    @Test
    @Transactional
    public void actorShouldBeLinkedToMovies() {

        Actor actor = new Actor();
        actor.setName("John Doe");
        actor.setFavoriteFood("Pizza");
        actor.setAge(30);

        Actor savedActor = testEntityManager.persistFlushFind(actor);

        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setDuration(2022);
        movie.getActors().add(savedActor);
        savedActor.getActedMovies().add(movie);
        Movie savedMovie = testEntityManager.persistFlushFind(movie);

        Actor foundActor = testEntityManager.find(Actor.class, savedActor.getId());
        assertThat(foundActor.getActedMovies()).isNotEmpty();
    }
}

package org.example.superapiv1.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FranchiseEntityTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldSaveAndRetrieveFranchise() {
        Franchise newFranchise = new Franchise();
        newFranchise.setName("Star Wars");

        newFranchise = testEntityManager.persistFlushFind(newFranchise);

        Franchise foundFranchise = testEntityManager.find(Franchise.class, newFranchise.getId());

        assertThat(foundFranchise.getName()).isEqualTo(newFranchise.getName());
    }

    @Test
    @Transactional
    public void franchiseShouldBeLinkedToMovies() {
        Franchise franchise = new Franchise();
        franchise.setName("Marvel Cinematic Universe");

        Franchise savedFranchise = testEntityManager.persistFlushFind(franchise);

        Movie movie = new Movie();
        movie.setTitle("Iron Man");
        movie.setDuration(126);
        movie.setFranchise(savedFranchise);
        savedFranchise.getMovies().add(movie);
        Movie savedMovie = testEntityManager.persistFlushFind(movie);

        Franchise foundFranchise = testEntityManager.find(Franchise.class, savedFranchise.getId());
        assertThat(foundFranchise.getMovies()).isNotEmpty();
    }
}

package org.example.superapiv1.entities;

import org.example.superapiv1.repositories.DirectorRepository;
import org.example.superapiv1.repositories.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DirectorTest {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testCreateDirector() {
        Director director = new Director();
        director.setId(1L);
        director.setName("John Doe");
        director.setFavoriteSport("Football");

        assertEquals(1L, director.getId());
        assertEquals("John Doe", director.getName());
        assertEquals("Football", director.getFavoriteSport());
    }

}


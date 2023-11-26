package com.harun.director;


import com.harun.common.base.BaseTest;
import com.harun.director.model.Director;
import com.harun.director.repository.DirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.HSQLDB)
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) status!=WHERE olduğu için çalışmıyor
public class DirectorRepositoryTests extends BaseTest {

    @Autowired
    private DirectorRepository directorRepository;
    private Director expectedDirector;

    @BeforeEach
    public void setupTestData() {
        expectedDirector = Director.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();
    }

    @Test
    @DisplayName("JUnit test for save director operation")
    public void givenDirectorObject_whenCreateDirector_thenReturnSavedDirector() {

        //When
        Director actualDirector = directorRepository.save(expectedDirector);

        //Then
        assertNotNull(actualDirector);
        assertTrue(actualDirector.getId() > 0);
    }

    @Test
    @DisplayName("JUnit test for get Director List")
    public void givenListOfDirectors_whenGetAllDirectors_thenReturnDirectorsList() {
        //Given
        Director expectedDirectorTwo = Director.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();


        Director expectedDirectorThree = Director.builder()
                .withName("Harun")
                .withLastName("Aydemir")
                .withAge(25)
                .withPhoneNumber("05111111111")
                .withEmail("harunaydemir001@gmail.com")
                .withPrize("Oscar")
                .withLastUpdate(new Date())
                .withBirthDay(new Date())
                .build();

        //When
        directorRepository.save(expectedDirectorTwo);
        directorRepository.save(expectedDirectorThree);

        List<Director> directors = directorRepository.findAll();

        // Then
        assertNotNull(directors);
        assertEquals(directors.size(), 2);
    }

    @Test
    @DisplayName("JUnit test for get Director by id")
    public void givenDirectorId_whenGetDirectorById_thenReturnDirectorObject() {
        // Given
        directorRepository.save(expectedDirector);

        // When
        Director actualDirector = directorRepository.findById(expectedDirector.getId()).get();

        // Then
        assertNotNull(actualDirector);
    }

    @Test
    @DisplayName("JUnit test for get director update operation")
    public void givenDirectorObject_whenUpdateDirector_thenReturnDirectorObject() {

        // Given
        directorRepository.save(expectedDirector);

        // When
        Director updatedDirector = directorRepository.findById(expectedDirector.getId()).get();

        updatedDirector.setName("Ahmet");
        updatedDirector.setAge(28);
        updatedDirector.setPrize("Prize");
        Director actualDirector = directorRepository.save(updatedDirector);

        // Then
        assertNotNull(actualDirector);
        assertEquals(expectedDirector.getName(), actualDirector.getName());
    }

    @Test
    @DisplayName("JUnit test for delete director operation")
    public void givenDirectorObject_whenDeleteDirector_thenRemoveDirector() {

        // Given
        directorRepository.save(expectedDirector);

        // When
        directorRepository.deleteById(expectedDirector.getId());
        Optional<Director> deletedDirector = directorRepository.findById(expectedDirector.getId());

        // Then
        assertEquals(deletedDirector, Optional.empty());
    }
}




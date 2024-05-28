package br.com.daniel.restwithspringbootandjava.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.daniel.restwithspringbootandjava.model.Person;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    private Person person0;

    String firstName, lastName;

    @BeforeEach
    public void setup() {
        firstName = "Daniel";
        lastName = "Lima";
        person0 = new Person(firstName, lastName,
        "Guarulhos - SP", "Male", "dsl15021996@gmail.com");
    }

    @DisplayName("Given Person Object When Save Then Return Saved Person")
    @Test
    void testGivenPersonObject_WhenSave_ThenReturnSavedPerson() {
        // Given / Arrange

        // When / Act
        Person savedPerson = repository.save(person0);
        // Then / Assert
        assertNotNull(savedPerson);
        assertTrue(savedPerson.getId() > 0);
    }

    @DisplayName("Given Person List When Find All Then Return Person List")
    @Test
    void testGivenPersonList_WhenFindAll_ThenReturnPersonList() {
        // Given / Arrange
        Person person1 = new Person("Emelly", "Pereira",
                "Guarulhos - SP", "Female", "emellypereira3@gmail.com");
        repository.saveAll(Arrays.asList(person0, person1));
        // When / Act
        List<Person> personList = repository.findAll();
        ;
        // Then / Assert
        assertNotNull(personList);
        assertEquals(2, personList.size());
        ;
    }

    @DisplayName("Given Person Object When Find By Id Then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindById_ThenReturnPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findById(person0.getId()).get();
        // Then / Assert
        assertNotNull(savedPerson);
    }

    @DisplayName("Given Person Object When Find By Email Then Return Person Object")
    @Test
    void testGivenPersonObject_WhenFindByEmail_ThenReturnPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findByEmail(person0.getEmail()).get();
        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(savedPerson.getEmail(), savedPerson.getEmail());
    }

    @DisplayName("Given Person Object When Update Person Then Return Updated Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_ThenReturnUpdatedPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findById(person0.getId()).get();
        savedPerson.setFirstName("Daniels");
        savedPerson.setFirstName("daniel.lima@gmail.com");

        Person updatedPerson = repository.save(savedPerson);
        // Then / Assert
        assertNotNull(updatedPerson);
        assertEquals(savedPerson.getId(), updatedPerson.getId());
        assertEquals(savedPerson.getEmail(), updatedPerson.getEmail());
        assertEquals(savedPerson.getFirstName(), updatedPerson.getFirstName());
    }

    @DisplayName("Given Person Object When Delete Person Then Remove Person")
    @Test
    void testGivenPersonObject_WhenDeletePerson_ThenRemovePerson() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        repository.deleteById(person0.getId());

        Optional<Person> personOptional = repository.findById(person0.getId());
        // Then / Assert
        assertTrue(personOptional.isEmpty());
    }

    @DisplayName("Given FirstName and LastName When Find By JPQL Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQL_ThenReturnPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findByJPQL(firstName, lastName);
        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getFirstName(), savedPerson.getFirstName());
        assertEquals(person0.getLastName(), savedPerson.getLastName());
    }

    @DisplayName("Given FirstName and LastName When Find By JPQLNamedParameters Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByJPQLNamedParameters_ThenReturnPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findByJPQLNamedParameters(firstName, lastName);
        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getFirstName(), savedPerson.getFirstName());
        assertEquals(person0.getLastName(), savedPerson.getLastName());
    }

    @DisplayName("Given FirstName and LastName When Find By Native SQL Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByNativeSQL_ThenReturnPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findByNativeSQL(firstName, lastName);
        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getFirstName(), savedPerson.getFirstName());
        assertEquals(person0.getLastName(), savedPerson.getLastName());
    }

    @DisplayName("Given FirstName and LastName When Find By Native SQL Named Parameters Then Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_WhenFindByNativeSQLWithNamedParameters_ThenReturnPersonObject() {
        // Given / Arrange

        repository.save(person0);

        // When / Act
        Person savedPerson = repository.findByNativeSQLWithNamedParameters(firstName, lastName);
        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals(person0.getFirstName(), savedPerson.getFirstName());
        assertEquals(person0.getLastName(), savedPerson.getLastName());
    }

}

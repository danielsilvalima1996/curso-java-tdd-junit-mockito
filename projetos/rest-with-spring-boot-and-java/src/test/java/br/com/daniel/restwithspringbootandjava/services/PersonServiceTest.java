package br.com.daniel.restwithspringbootandjava.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.daniel.restwithspringbootandjava.exceptions.ResourceConflictException;
import br.com.daniel.restwithspringbootandjava.model.Person;
import br.com.daniel.restwithspringbootandjava.repositories.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private PersonService service;

    private Person person0;

    @BeforeEach
    void setup() {
        person0 = new Person("Daniel", "Lima",
                "Guarulhos - SP", "Male", "dsl15021996@gmail.com");
    }

    @DisplayName("Given Person Object When Saved Person Then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavedPerson_ThenReturnPersonObject() {
        // Given / Arrange
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(repository.save(person0)).thenReturn(person0);

        // When / Act
        Person savedPerson = service.create(person0);

        // Then / Assert
        assertNotNull(savedPerson);
        // assertTrue(savedPerson.getId() > 0);
        assertEquals("Daniel", savedPerson.getFirstName());
    }

    @DisplayName("Given Existing Email When Saved Person Then Throws Exception")
    @Test
    void testGivenExistingEmail_WhenSavedPerson_ThenThrowsException() {
        // Given / Arrange
        when(repository.findByEmail(anyString())).thenReturn(Optional.of(person0));

        // When / Act
        assertThrows(ResourceConflictException.class, () -> {
            service.create(person0);
        });

        // Then / Assert
        verify(repository, never()).save(any(Person.class));
    }

    @DisplayName("Given People List When Find All People Then Return People List")
    @Test
    void testGivenPeopleList_WhenFindAllPeople_ThenReturnPeopleList() {
        // Given / Arrange
        Person person1 = new Person("Emelly", "Pereira",
                "Guarulhos - SP", "Female", "emellypereira3@gmail.com");

        when(repository.findAll()).thenReturn(List.of(person0, person1));

        // When / Act
        List<Person> peopleList = service.findAll();

        // Then / Assert
        assertNotNull(peopleList);
        assertEquals(2, peopleList.size());
    }

    @DisplayName("Given People List When Find All People Then Return Empty List")
    @Test
    void testGivenPeopleList_WhenFindAllPeople_ThenReturnEmptyList() {
        // Given / Arrange
        Person person1 = new Person("Emelly", "Pereira",
                "Guarulhos - SP", "Female", "emellypereira3@gmail.com");

        when(repository.findAll()).thenReturn(Collections.emptyList());

        // When / Act
        List<Person> peopleList = service.findAll();

        // Then / Assert
        assertTrue(peopleList.isEmpty());
        assertEquals(0, peopleList.size());
    }

    @DisplayName("Given Person Id When Find By Id Then Return Person Object")
    @Test
    void testGivenPersonId_WhenFindById_ThenReturnPersonObject() {
        // Given / Arrange
        when(repository.findById(anyLong())).thenReturn(Optional.of(person0));

        // When / Act
        Person savedPerson = service.findById(1L);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals("Daniel", savedPerson.getFirstName());
    }

    @DisplayName("Given Person Object Person When Update Person Then Return Updated Person Object")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_ThenReturnUpdatedPersonObject() {
        // Given / Arrange
        person0.setId(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(person0));

        person0.setEmail("emellypereira3@gmail.com");
        person0.setFirstName("Emelly");
        person0.setLastName("Jesus");
        person0.setGender("Female");

        when(repository.save(person0)).thenReturn(person0);

        // When / Act
        Person savedPerson = service.update(person0);

        // Then / Assert
        assertNotNull(savedPerson);
        assertEquals("Emelly", savedPerson.getFirstName());
        assertEquals("Jesus", savedPerson.getLastName());
        assertEquals("Female", savedPerson.getGender());
    }

    @DisplayName("Given Person Id When Delete Person Then Do Nothing")
    @Test
    void testGivenPersonId_WhenDeletePerson_ThenDoNothing() {
        // Given / Arrange
        person0.setId(1L);
        given(repository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(repository).delete(person0);

        // When / Act
        service.delete(person0.getId());

        // Then / Assert
        verify(repository, times(1)).delete(person0);
    }

}
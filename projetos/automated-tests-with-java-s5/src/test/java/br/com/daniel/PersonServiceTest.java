package br.com.daniel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.daniel.model.Person;
import br.com.daniel.service.IPersonService;
import br.com.daniel.service.PersonService;

class PersonServiceTest {

    Person person;

    IPersonService service;

    @BeforeEach
    void setup() {
        person = new Person(
                "Keith",
                "Moon",
                "Wembley - UK",
                "Male",
                "keith.moon@gmail.com");

                service = new PersonService();
    }

    @DisplayName("When create a Person with success should return Person Object")
    @Test
    void testCreatePerson_whenSuccess_ShouldReturnPersonObject() {

        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual, () -> "the createPerson() should not have return null!");
    }

    @DisplayName("When create a Person with success should contains firstName in returned Person Object")
    @Test
    void testCreatePerson_whenSuccess_ShouldContainsFirstNameInReturnedPersonObject() {

        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertEquals(person.getFirstName(), actual.getFirstName(),
                () -> "The firstName is diferent!");
    }

    @DisplayName("When create a Person with success should contains Valid Fields in returned Person Object")
    @Test
    void testCreatePerson_whenSuccess_ShouldContainsValidFieldsInReturnedPersonObject() {

        // Given / Arrange

        // When / Act
        Person actual = service.createPerson(person);

        // Then / Assert
        assertNotNull(actual.getId(), () -> "Person ID is missing!");
        assertEquals(person.getFirstName(), actual.getFirstName(),
                () -> "The Person FirstName is incorrect!");

        assertEquals(person.getLastName(), actual.getLastName(),
                () -> "The Person LastName is incorrect!");

        assertEquals(person.getAddress(), actual.getAddress(),
                () -> "The Person Address is incorrect!");

        assertEquals(person.getGender(), actual.getGender(),
                () -> "The Person Gender is incorrect!");

        assertEquals(person.getEmail(), actual.getEmail(),
                () -> "The Person Email is incorrect!");
    }

    // test[System Under Test]_[Condition or State Change]_[Expected Result]
    @DisplayName("When create a Person with Null e-mail should Throw exception")
    @Test
    void testCreatePerson_WithNullEmail_ShouldThrowIllegalArgumentException() {
        // Given / Arrange

        String expectedMessage = "The Person e-mail is null or empty!";
        Person actual = service.createPerson(person);
        actual.setEmail(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.createPerson(actual);
        },
        () -> "Empty E-mail should have cause an IllegalArgumentException!");

        assertEquals(expectedMessage, exception.getMessage(),
            () -> "The message of IllegalArgumentException is not equals expected!");
    }
}

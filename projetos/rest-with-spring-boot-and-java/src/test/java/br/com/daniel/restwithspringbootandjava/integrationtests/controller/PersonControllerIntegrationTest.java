package br.com.daniel.restwithspringbootandjava.integrationtests.controller;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.daniel.restwithspringbootandjava.config.TestConfigs;
import br.com.daniel.restwithspringbootandjava.integrationtests.testcontainers.AbstractIntegrationTest;
import br.com.daniel.restwithspringbootandjava.model.Person;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonControllerIntegrationTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static Person person;

    @BeforeAll
    public static void setup() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        specification = new RequestSpecBuilder()
                .setBasePath("/person")
                .setPort(TestConfigs.SERVER_PORT)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        person = new Person(
                "Daniel",
                "Lima",
                "Guarulhos - SP",
                "Male",
                "dsl15021996@gmail.com");
    }

    @Test
    @Order(1)
    @DisplayName("Integration Test Given Person Object When Create One Person Should Return A Person Object")
    void integrationTestGivenPersonObject_When_CreateOnePerson_ShouldReturnAPersonObject()
            throws JsonMappingException, JsonProcessingException {

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person createdPerson = mapper.readValue(content, Person.class);

        person = createdPerson;

        assertNotNull(createdPerson);
        assertNotNull(createdPerson.getId());
        assertNotNull(createdPerson.getFirstName());
        assertNotNull(createdPerson.getLastName());
        assertNotNull(createdPerson.getAddress());
        assertNotNull(createdPerson.getEmail());
        assertNotNull(createdPerson.getGender());

        assertTrue(createdPerson.getId() > 0);

        assertEquals("Daniel", createdPerson.getFirstName());
        assertEquals("Lima", createdPerson.getLastName());
        assertEquals("Guarulhos - SP", createdPerson.getAddress());
        assertEquals("Male", createdPerson.getGender());
        assertEquals("dsl15021996@gmail.com", createdPerson.getEmail());
    }

    @Test
    @Order(2)
    @DisplayName("Integration Test Given Person Object When Update One Person Should Return A Updated Person Object")
    void integrationTestGivenPersonObject_When_UpdateOnePerson_ShouldReturnAUpdatedPersonObject()
            throws JsonMappingException, JsonProcessingException {

        person.setFirstName("Emelly");
        person.setLastName("Jesus");
        person.setGender("Female");
        person.setEmail("emellypereira3@gmail.com");

        var content = given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(person)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person updatedPerson = mapper.readValue(content, Person.class);

        person = updatedPerson;

        assertNotNull(updatedPerson);
        assertNotNull(updatedPerson.getId());
        assertNotNull(updatedPerson.getFirstName());
        assertNotNull(updatedPerson.getLastName());
        assertNotNull(updatedPerson.getAddress());
        assertNotNull(updatedPerson.getEmail());
        assertNotNull(updatedPerson.getGender());

        assertTrue(updatedPerson.getId() > 0);

        assertEquals("Emelly", updatedPerson.getFirstName());
        assertEquals("Jesus", updatedPerson.getLastName());
        assertEquals("Guarulhos - SP", updatedPerson.getAddress());
        assertEquals("Female", updatedPerson.getGender());
        assertEquals("emellypereira3@gmail.com", updatedPerson.getEmail());
    }

    @Test
    @Order(3)
    @DisplayName("Integration Test Given Person Object When Find By Id Should Return A Updated Person Object")
    void integrationTestGivenPersonObject_When_FindById_ShouldReturnAUpdatedPersonObject()
            throws JsonMappingException, JsonProcessingException {

        var content = given()
                .spec(specification)
                .pathParam("id", person.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person foundPerson = mapper.readValue(content, Person.class);

        assertNotNull(foundPerson);
        assertNotNull(foundPerson.getId());
        assertNotNull(foundPerson.getFirstName());
        assertNotNull(foundPerson.getLastName());
        assertNotNull(foundPerson.getAddress());
        assertNotNull(foundPerson.getEmail());
        assertNotNull(foundPerson.getGender());

        assertTrue(foundPerson.getId() > 0);

        assertEquals("Emelly", foundPerson.getFirstName());
        assertEquals("Jesus", foundPerson.getLastName());
        assertEquals("Guarulhos - SP", foundPerson.getAddress());
        assertEquals("Female", foundPerson.getGender());
        assertEquals("emellypereira3@gmail.com", foundPerson.getEmail());
    }

    @Test
    @Order(4)
    @DisplayName("Integration Test When Find All Should Return People List")
    void integrationTest_When_FindAllId_ShouldReturnPeopleList()
            throws JsonMappingException, JsonProcessingException {

        Person anotherPerson = new Person("Daniel", "Lima",
                "Guarulhos - SP", "Male",
                "dsl15021996@gmail.com");

        given()
                .spec(specification)
                .contentType(TestConfigs.CONTENT_TYPE_JSON)
                .body(anotherPerson)
                .when()
                .post()
                .then()
                .statusCode(200);

        var content = given()
                .spec(specification)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        Person[] myArray = mapper.readValue(content, Person[].class);
        List<Person> people = Arrays.asList(myArray);

        Person foundPersonOne = people.get(0);

        assertNotNull(foundPersonOne);
        assertNotNull(foundPersonOne.getId());
        assertNotNull(foundPersonOne.getFirstName());
        assertNotNull(foundPersonOne.getLastName());
        assertNotNull(foundPersonOne.getAddress());
        assertNotNull(foundPersonOne.getEmail());
        assertNotNull(foundPersonOne.getGender());

        assertTrue(foundPersonOne.getId() > 0);

        assertEquals("Emelly", foundPersonOne.getFirstName());
        assertEquals("Jesus", foundPersonOne.getLastName());
        assertEquals("Guarulhos - SP", foundPersonOne.getAddress());
        assertEquals("Female", foundPersonOne.getGender());
        assertEquals("emellypereira3@gmail.com", foundPersonOne.getEmail());

        Person foundPersonTwo = people.get(1);

        assertNotNull(foundPersonTwo);
        assertNotNull(foundPersonTwo.getId());
        assertNotNull(foundPersonTwo.getFirstName());
        assertNotNull(foundPersonTwo.getLastName());
        assertNotNull(foundPersonTwo.getAddress());
        assertNotNull(foundPersonTwo.getEmail());
        assertNotNull(foundPersonTwo.getGender());

        assertTrue(foundPersonTwo.getId() > 0);

        assertEquals("Daniel", foundPersonTwo.getFirstName());
        assertEquals("Lima", foundPersonTwo.getLastName());
        assertEquals("Guarulhos - SP", foundPersonTwo.getAddress());
        assertEquals("Male", foundPersonTwo.getGender());
        assertEquals("dsl15021996@gmail.com", foundPersonTwo.getEmail());
    }

    @Test
    @Order(5)
    @DisplayName("Integration Test Given Person Object When Delete Should Return No Content")
    void integrationTestGivenPersonObject_When_Delete_ShouldReturnNoContent()
            throws JsonMappingException, JsonProcessingException {

        given()
                .spec(specification)
                .pathParam("id", person.getId())
                .when()
                .delete("{id}")
                .then()
                .statusCode(204);

    }

}

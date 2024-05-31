package br.com.daniel.restwithspringbootandjava.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.daniel.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import br.com.daniel.restwithspringbootandjava.model.Person;
import br.com.daniel.restwithspringbootandjava.services.PersonService;

@WebMvcTest()
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PersonService service;

	private Person person;

	@BeforeEach
	void setup() {
		person = new Person(
				"Daniel",
				"Lima",
				"Guarulhos - SP",
				"Male",
				"dsl15021996@gmail.com");
	}

	@Test
	@DisplayName("test Given Person Object When Create Person Then Return Saved Person")
	void testGivenPersonObject_WhenCreatePerson_ThenReturnSavedPerson() throws Exception {
		// Given / Arrange
		given(service.create(any(Person.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));
		// When / Act
		ResultActions response = mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(person)));
		// Then / Assert
		response.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(person.getLastName())))
				.andExpect(jsonPath("$.email", is(person.getEmail())));
	}

	@Test
	@DisplayName("test Given List Of People When Find All People Then Return People List")
	void testGivenListOfPeople_WhenFindAllPeople_ThenReturnPeopleList() throws Exception {
		// Given / Arrange
		List<Person> people = new ArrayList<>();
		people.add(person);
		people.add(new Person(
				"Emelly",
				"Jesus",
				"Guarulhos - SP",
				"Female",
				"emellypereira3@gmail.com"));
		given(service.findAll())
				.willReturn(people);
		// When / Act
		ResultActions response = mockMvc.perform(get("/person"));
		// Then / Assert
		response
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.size()", is(people.size())));
	}

	@Test
	@DisplayName("test Given Person Id When Find By Id Then Person Object")
	void testGivenPersonId_WhenFindById_ThenReturnPersonObject() throws Exception {
		// Given / Arrange
		long personId = 1L;
		given(service.findById(personId))
				.willReturn(person);
		// When / Act
		ResultActions response = mockMvc.perform(get("/person/{id}", personId));
		// Then / Assert
		response
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(person.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(person.getLastName())))
				.andExpect(jsonPath("$.email", is(person.getEmail())));
	}

	@Test
	@DisplayName("test Given Invalid Person Id When Find By Id Then Not Found")
	void testGivenInvalidPersonId_WhenFindById_ThenReturnNotFound() throws Exception {
		// Given / Arrange
		long personId = 1L;
		given(service.findById(personId))
				.willThrow(ResourceNotFoundException.class);
		// When / Act
		ResultActions response = mockMvc.perform(get("/person/{id}", personId));
		// Then / Assert
		response.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("test Given Updated Person When Update Then Return Updated Person Object")
	void testGivenUpdatedPerson_WhenUpdate_ThenReturnUpdatedPersonObject() throws Exception {
		// Given / Arrange
		long personId = 1L;
		Person updatedPerson = new Person(
				"Emelly",
				"Jesus",
				"Guarulhos - SP",
				"Female",
				"emellypereira3@gmail.com");
		given(service.findById(personId)).willReturn(person);
		given(service.update(any(Person.class)))
				.willAnswer((invocation) -> invocation.getArgument(0));
		// When / Act
		ResultActions response = mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(updatedPerson)));
		// Then / Assert
		response
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
				.andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
				.andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
	}

	@Test
	@DisplayName("JUnit test for Given Unexistent Person when Update then Return Not Found")
	void testGivenUnexistentPerson_WhenUpdate_thenReturnNotFound() throws JsonProcessingException, Exception {

		// Given / Arrange
		long personId = 1L;
		given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
		given(service.update(any(Person.class))).willThrow(ResourceNotFoundException.class)
				.willAnswer((invocation) -> invocation.getArgument(1));

		// When / Act
		Person updatedPerson = new Person(
				"Leonardo",
				"Costa",
				"leonardo@erudio.com.br",
				"Uberlândia - Minas Gerais - Brasil",
				"Male");

		ResultActions response = mockMvc.perform(put("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(updatedPerson)));

		// Then / Assert
		response.andExpect(status().isNotFound())
				.andDo(print());
	}

	@Test
	@DisplayName("JUnit test for Given Person Id When Delete then Return No Content")
	void testGivenPersonId_WhenDelete_thenReturnNoContent() throws JsonProcessingException, Exception {

		// Given / Arrange
		long personId = 1L;

		willDoNothing().given(service).delete(personId);

		given(service.findById(personId)).willThrow(ResourceNotFoundException.class);
		given(service.update(any(Person.class))).willThrow(ResourceNotFoundException.class)
				.willAnswer((invocation) -> invocation.getArgument(1));

		ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

		// Then / Assert
		response.andExpect(status().isNoContent())
				.andDo(print());
	}

}

package br.com.daniel.mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.daniel.business.CourseBusiness;
import br.com.daniel.service.CourseService;

@ExtendWith(MockitoExtension.class)
class CourseBusinessMockitoInjectMocksTest {

	@Mock
	CourseService mockService;

	@InjectMocks
	CourseBusiness business;
	// business = new CourseBusiness(mockService);

	@Captor
	ArgumentCaptor<String> argumentCaptor ;

	List<String> courses;

	@BeforeEach
	void setup() {
		// Given / Arrange
		courses = Arrays.asList(
				"REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
				"Agile Desmistificado com Scrum, XP, Kanban e Trello",
				"Spotify Engineering Culture Desmistificado",
				"REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
				"Docker do Zero à Maestria - Contêinerização Desmistificada",
				"Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
				"Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
				"Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
				"REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
				"Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
				"Microsserviços do 0 com Spring Cloud, Kotlin e Docker");
	}

	// test[System Under Test]_[Condition or State Change]_[Expected Result]
	@DisplayName("Test with mockito")
	@Test
	void testCoursesRelatedToSpring_When_UsingAsMock() {

		// Given / Arrange
		given(mockService.retrieveCourses("Daniel"))
				.willReturn(courses);
		// When / Act
		var filteredCourse = business.retrieveCoursesRelatedToSpring("Daniel");
		// Then / Assert
		assertThat(filteredCourse.size(), is(4));
	}

	// test[System Under Test]_[Condition or State Change]_[Expected Result]
	@DisplayName("Delete courses not related to Spring using mockito should call method")
	@Test
	void testDeletedCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCourses() {
		// Given / Arrange
		given(mockService.retrieveCourses("Daniel"))
				.willReturn(courses);

		// When / Act
		business.deleteCoursesNotRelatedToSpring("Daniel");
		// Then / Assert

		verify(mockService, atLeastOnce())
				.deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");

		verify(mockService)
				.deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");

		verify(mockService, never())
				.deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
	}

	// test[System Under Test]_[Condition or State Change]_[Expected Result]
	@DisplayName("Delete courses not related to Spring using mockito should call method V2")
	@Test
	void testDeletedCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethod_deleteCoursesV2() {
		// Given / Arrange
		given(mockService.retrieveCourses("Daniel"))
				.willReturn(courses);

		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
		String architectureCourse = "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#";
		String restSpringCourse = "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker";

		// When / Act
		business.deleteCoursesNotRelatedToSpring("Daniel");
		// Then / Assert

		then(mockService)
				.should()
				.deleteCourse(agileCourse);

		then(mockService)
				.should()
				.deleteCourse(architectureCourse);

		then(mockService)
				.should(never())
				.deleteCourse(restSpringCourse);
	}

	// test[System Under Test]_[Condition or State Change]_[Expected Result]
	@DisplayName("Delete courses not related to Spring Capture Arguments should call method")
	@Test
	void testDeletedCoursesNotRelatedToSpring_CaptureArguments_Should_CallMethod_deleteCourses() {
		// Given / Arrange
		given(mockService.retrieveCourses("Daniel"))
				.willReturn(courses);

		// ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

		// When / Act
		business.deleteCoursesNotRelatedToSpring("Daniel");
		// Then / Assert

		then(mockService)
				.should(times(7))
				.deleteCourse(argumentCaptor.capture());

		assertThat(argumentCaptor.getAllValues().size(), is(7));
	}

}

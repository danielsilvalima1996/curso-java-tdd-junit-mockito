package br.com.daniel.restwithspringbootandjava.integrationtests.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.daniel.restwithspringbootandjava.config.TestConfigs;
import br.com.daniel.restwithspringbootandjava.integrationtests.testcontainers.AbstractIntegrationTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

	// test[System Under Test]_[Condition or State Change]_[Expected Result]
	@Test
	@DisplayName("test Should Display Swagger Ui Page")
	void testShouldDisplaySwaggerUiPage() { 

		var content = given()
			.basePath("/swagger-ui/index.html")
			.port(TestConfigs.SERVER_PORT)
			.when()
				.get()
			.then()
				.statusCode(200)
			.extract()
				.body()
					.asString();

		assertTrue(content.contains("Swagger UI"));
	}

}

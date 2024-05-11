package br.com.daniel.math;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.daniel.SimpleMath;

@DisplayName("Test Math Operation in SimpleMath Class")
public class SimpleMathTestS4 {

	SimpleMath math;

	@BeforeEach
	void beforeEachMethod() {
		math = new SimpleMath();
	}

	@DisplayName("Test String not null")
	@ParameterizedTest
	@ValueSource(strings = {"Pelé", "Senna", "Keith Moon"})
	void testValueSource(String firstName) {
		System.out.println(firstName);
		assertNotNull(firstName);
	}

	@DisplayName("Test Double Division [firstNumber, secondNumber, expected]")
	@ParameterizedTest
	/*
	@CsvSource({
		"6.2, 2, 3.1",
		"71, 14, 5.07",
		"18.3, 3.1, 5.90"
	}) 
	*/
	/* 
	@CsvSource({
		"Pelé, Football",
		"Senna, F1"0
		"Keith Moon, ''"
	})
	*/
	// @MethodSource("testDivisionInputParameters")
	// @MethodSource()
	@CsvFileSource(resources = "/testDivision.csv")
	void testDivision(double firstNumber, double secondNumber, double expected) {

		System.out.println(firstNumber + " / " + secondNumber + " = " + expected);
		
		Double actual = math.division(firstNumber, secondNumber);

		Assertions.assertEquals(expected, actual, 2D,
				() -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");

	}

	// public static Stream<Arguments> testDivisionInputParameters() {
	/* public static Stream<Arguments> testDivision() {
		return Stream.of(
			Arguments.of(6.2D, 2D, 3.1D),
			Arguments.of(71D, 14D, 5.07D),
			Arguments.of(18.3D, 3.1D, 5.90D)
		);
	} */

}

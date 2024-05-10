package br.com.daniel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Math Operation in SimpleMath Class")
class SimpleMathTest {

	SimpleMath math;

	@BeforeAll
	static void setup() {
		System.out.println("Running @BeforeAll method!");
	}

	@AfterAll
	static void cleanup() {
		System.out.println("Running @AfterAll method!");
	}

	@BeforeEach
	void beforeEachMethod() {
		System.out.println("Running @BeforeEach method!");
		math = new SimpleMath();
	}

	@AfterEach
	void afterEachMethod() {
		System.out.println("Running @AfterEach method!");
	}

	@Test
	@DisplayName("Test 6.2 + 2 = 8.2")
	void testSum_When_SixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo() {

		System.out.println("Running testSum_When_SixDotTwoIsAddedByTwo_ShouldReturnEightDotTwo method!");
		// AAA Arrange, Act and Assert
		// Given / Arrang

		double firstNumber = 6.2D;
		double secondNumber = 2D;
		Double expected = 8.2D;

		// When / Act
		Double actual = math.sum(firstNumber, secondNumber);

		// Then / Assert
		Assertions.assertEquals(expected, actual,
				() -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
	}

	@Test
	@DisplayName("Test 6.2 - 2 = 4.2")
	void testSubtraction() {

		System.out.println("Running testSubtraction method!");

		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.subtraction(firstNumber, secondNumber);

		Double expected = 4.2D;

		Assertions.assertEquals(expected, actual,
				() -> firstNumber + " - " + secondNumber + " did not produce " + expected + "!");
	}

	@Test
	@DisplayName("Test 6.2 * 2 = 12.4")
	void testMultiplication() {

		System.out.println("Running testMultiplication method!");

		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.multiplication(firstNumber, secondNumber);

		Double expected = 12.4D;

		Assertions.assertEquals(expected, actual,
				() -> firstNumber + " * " + secondNumber + " did not produce " + expected + "!");
	}

	@Test
	@DisplayName("Test 6.2 / 2 = 3.1")
	void testDivision() {

		System.out.println("Running testDivision method!");

		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.division(firstNumber, secondNumber);

		Double expected = 3.1D;

		Assertions.assertEquals(expected, actual,
				() -> firstNumber + " / " + secondNumber + " did not produce " + expected + "!");

	}

	@Test
	@DisplayName("Test Division By Zero")
	void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException() {
	
		// Given
		double firstNumber = 6.2D;
		double secondNumber = 0D;

		var expectedMessage = "Impossible to divide by zero!";

		// When & then
		ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
			math.division(firstNumber, secondNumber);
		}, () -> "Division by zero should thrown an ArithmeticException");

		assertEquals(expectedMessage, actual.getMessage(), 
			() -> "Unexpected message exception!");

	}

	@Test
	@DisplayName("Test (6.2 + 2) = 4.1")
	void testMean() {

		System.out.println("Running testMean method!");

		double firstNumber = 6.2D;
		double secondNumber = 2D;

		Double actual = math.mean(firstNumber, secondNumber);

		Double expected = 4.1D;

		Assertions.assertEquals(expected, actual,
				() -> "( " + firstNumber + " + " + secondNumber + " ) / 2 did not produce " + expected + "!");
	}

	@Test
	@DisplayName("Test Square Root of the 81 = 9")
	void testSquareRoot() {

		System.out.println("Running testSquareRoot method!");

		double number = 81D;

		Double actual = math.squareRoot(number);

		Double expected = 9D;

		Assertions.assertEquals(expected, actual,
				() -> "Square root of the " + number + " did not produce " + expected + "!");

	}

	// test[System Under Test]_[Condition or State Change]_[Expected Result]
	@DisplayName("Display Name")
	@Test
	void testABCD_when_XYZ_Should() { 

		System.out.println("Running testABCD_when_XYZ_Should method!");

		// Given / Arrange  
		// When / Act  
		// Then / Assert 
	}

}

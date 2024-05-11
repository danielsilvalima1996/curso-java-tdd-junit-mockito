package br.com.daniel.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import br.com.daniel.SimpleMath;

public class DemoRepeatedTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod() {
        System.out.println("Running @BeforeEach method!");
        math = new SimpleMath();
    }

    @RepeatedTest(value = 3, name = "{displayName}. Repetition " +
        "{currentRepetition} of {totalRepetitions}")
	@DisplayName("Test Division By Zero")
	void testDivision_When_FirstNumberIsDividedByZero_ShouldThrowArithmeticException(
        RepetitionInfo repetitionInfo,
        TestInfo testInfo
    ) {

        System.out.println("Repetition Nº. " + repetitionInfo.getCurrentRepetition() + " of " + 
            repetitionInfo.getTotalRepetitions()); 

        System.out.println("Running " + testInfo.getTestMethod().get().getName());
	
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

}

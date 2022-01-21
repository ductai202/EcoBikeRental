package caculator_bike;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import calculator_bike.CalculatorBikeDeposit;
import calculator_bike.CurrentCalculatorBikeDeposit;
import entity.Bike;

class ValidateCalculatorBikeDeposit {

	private CalculatorBikeDeposit calculatorBikeDeposit;
	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		calculatorBikeDeposit = new CurrentCalculatorBikeDeposit();
	}

	@ParameterizedTest
	@CsvSource({
		"b, 400000",
		"eb, 700000", 
		"tb, 550000",
		"a, -1",
	})
	void validateDeposit(String typeBike, int expected) {
		int isValid = calculatorBikeDeposit.calculateBikeDeposit(typeBike);
		assertEquals(isValid, expected);
	}
}

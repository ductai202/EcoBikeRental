package caculator_bike;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import calculator_bike.CalculatorBikeDeposit;
import calculator_bike.CalculatorBikeRenting;
import calculator_bike.CurrentCalculatorBikeDeposit;
import calculator_bike.CurrentCalculatorBikeRenting;
import entity.Bike;

class ValidateCalculatorBikeRenting {

	private CalculatorBikeRenting calculatorBikeRenting;
	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		calculatorBikeRenting = new CurrentCalculatorBikeRenting();
	}

	@ParameterizedTest
	@CsvSource({
		"b,60, 16000",
		"b,90, 22000", 
		"b,120, 28000",
		"eb,60, 24000",
		"eb,90, 33000",
		"eb,120, 42000",
		"tb,60, 24000",
		"tb,90, 33000",
		"tb,120, 42000",
		"a,60, -1",
		"1,60, -1"
	})
	void testBike(String typeBike,int minutes, int expected) {
		int isValid = calculatorBikeRenting.calculatorBikeRenting(typeBike, minutes);
		assertEquals(isValid, expected);
	}
}

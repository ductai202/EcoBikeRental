package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateWeightTest {
	private BikeController bikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		bikeController = new BikeController();
	}

	@ParameterizedTest
	@CsvSource({
		",false",
		"\"\", false",
		"0,false",
		"1abc, false",
		"3,true"
	})
			
	void test(String weight, boolean expected) {
		boolean isValid = bikeController.validateWeight(weight);
		assertEquals(expected, isValid);
	}
}

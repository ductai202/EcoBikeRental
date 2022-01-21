package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateValueTest {
	private BikeController bikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		bikeController = new BikeController();
	}

	@ParameterizedTest
	@CsvSource({
		",false",
		"\"\",false",
		"3333333333333333333333333333333, false",
		"0123,false",
		"12@3,false",
		"3,true"
	})
			
	void test(String value, boolean expected) {
		boolean isValid = bikeController.validateValue(value);
		assertEquals(expected, isValid);
	}
}

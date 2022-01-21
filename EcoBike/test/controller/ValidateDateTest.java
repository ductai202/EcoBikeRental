package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateDateTest {
	private BikeController bikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		bikeController = new BikeController();
	}

	@ParameterizedTest
	@CsvSource({
		",false",
		"\"\", false",
		"19/19/1888,false",
		"31/04/2020, false",
		"30/02/2020,false",
		"29/02/2020,true",
		"29/2/2021,false",
		"28/02/2021,true",
		"14/06/2000, true"
	})
			
	void test(String date, boolean expected) {
		boolean isValid = bikeController.validateDate(date);
		assertEquals(expected, isValid);
	}
}

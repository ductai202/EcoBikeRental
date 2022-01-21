package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateBikeCodeTest {
	private BikeController bikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		bikeController = new BikeController();
	}

	@ParameterizedTest
	@CsvSource({
		",false",
		"\"\",false",
		"BK123456789,false",
		"12@$3,false",
		"Bk 123,true"
	})
			
	void test(String bikeCode, boolean expected) {
		boolean isValid = bikeController.validateBikeCode(bikeCode);
		assertEquals(expected, isValid);
	}
}

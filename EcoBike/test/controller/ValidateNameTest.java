package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateNameTest {
	private BikeController bikeController;
	
	@BeforeEach
	void setUp() throws Exception {
		bikeController = new BikeController();
	}

	@ParameterizedTest
	@CsvSource({
		",false",
		"\"\",false",
		"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa,false",
		"Ya@#maha, false",
		"Xe dap dien, true",
	})
			
	void test(String name, boolean expected) {
		boolean isValid = bikeController.validateName(name);
		assertEquals(expected, isValid);
	}
}

package caculator_bike;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PaymentController;


class ValidateTimeRented {

	private PaymentController paymentController;
	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		paymentController = new PaymentController();
	}

	@ParameterizedTest
	@CsvSource({
		"2021-12-20 10:10:10, true ",
		"2021-12-20 10-10-10, false", 
		"2021:12:20 10-10-10, false",
		"2021:12:20 10:10:10, false",
		"2021/12/20 10-10-10, false",
		"2021-12-20 10/10/10, false",
		"2021:12:20 10/10/10, false",
		"2021/12/20 10:10:10, false",
		"2021/12/20 10/10/10, false",
		"2022/12/20 10/10/10, false"
	})
	void testBike(String minutes, boolean expected) {
		boolean isValid = paymentController.validateCalculatedTime(minutes);
		assertEquals(isValid, expected);
	}

}

package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardExpirationDate {

	private PaymentController paymentController;

	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		paymentController = new PaymentController();
	}

	/**
	 * @param date : gồm 4->5 ký tự, theo định dạng: 1-2 ký tự đầu là tháng + “/”
	 *                + 2 ký tự năm từ 20->99
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({ "123, -1", "123456, -1", "12/09, -1", "9/091, -1", "9/25, 925", "11/25, 1125" })
	void validateGettExpirationDate(String date, String expected) {
		String isValid = paymentController.getExpirationDate(date);
		assertEquals(isValid, expected);
	}

}

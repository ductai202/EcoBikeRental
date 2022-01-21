package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardCVV {

	private PaymentController paymentController;

	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		paymentController = new PaymentController();
	}

	/**
	 * @param cardCVV tên mã giao dịch chỉ chứa 2->3 chữ số
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({ "1, false", "1234, false", "2a, false", "23a, false", "12, true", "698, true" })
	void validateCardCvv(String cardCVV, boolean expected) {
		boolean isValid = paymentController.validateCardCVV(cardCVV);
		assertEquals(isValid, expected);
	}

}

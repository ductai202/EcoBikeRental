package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardCode {

	private PaymentController paymentController;

	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		paymentController = new PaymentController();
	}

	/**
	 * @param cardCode: từ 17 đến 18 bao gồm của chữ cái và chữ số, trong đó 4 chữ
	 *                  cái ở đầu + 1 chữ số tiếp theo + dấu “_” + 5 chữ cái tiếp
	 *                  theo sau+1 hoặc 2 chữ số + “_” + 4 chữ số
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({ "1234567890123456, false", "1234567891234567890, false", "Ab121212121212121, false",
			"Ab1212121212121211, false", "kscq2_group1_2021, true", "kscq2_group20_2021, true" })
	void validateCardCode(String cardCode, boolean expected) {
		boolean isValid = paymentController.validateCardCode(cardCode);
		assertEquals(isValid, expected);
	}

}

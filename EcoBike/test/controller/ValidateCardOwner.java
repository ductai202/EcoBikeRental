package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateCardOwner {

	private PaymentController paymentController;

	@BeforeEach
	void setUp() throws Exception {
		// type normal bike
		paymentController = new PaymentController();
	}

	/**
	 * @param owner    tên chủ sở hữu có 7->8 ký tự, có định dạng “Group” + “<<dấu
	 *                 cách>>“ + 1 hoặc chữ số
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({ "Group1, false", "123456789, false", "Aroup 1, false", "Abcde 20, false", "Group 1, true",
			"Group 20, true" })
	void validateCardOwner(String owner, boolean expected) {
		boolean isValid = paymentController.validateCardOwner(owner);
		assertEquals(isValid, expected);
	}

}

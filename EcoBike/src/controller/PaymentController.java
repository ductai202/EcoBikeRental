package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.exception.InvalidCardException;
import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;
import subsystem.InterbankInterface;
import subsystem.InterbankSubsystem;
import utils.Utils;

public class PaymentController extends BaseController {

	/**
	 * Represent the card used for payment
	 */
	private CreditCard card;

	/**
	 * Represent the Interbank subsystem
	 */
	private InterbankInterface interbank;

	/**
	 * Pay order, and then return the result with a message.
	 * 
	 * @param amount         - the amount to pay
	 * @param contents       - the transaction contents
	 * @param cardNumber     - the card number
	 * @param cardHolderName - the card holder name
	 * @param expirationDate - the expiration date in the format "mm/yy"
	 * @param securityCode   - the cvv/cvc code of the credit card
	 * @return {@link java.util.Map Map} represent the payment result with a
	 *         message.
	 */
	public PaymentTransaction payOrder(int amount, String contents, String cardNumber, String cardHolderName,
			String expirationDate, String securityCode) {
		this.processValidateCreditCard(cardHolderName, securityCode, cardNumber, expirationDate);

		try {
			this.card = new CreditCard(cardNumber, cardHolderName, Integer.parseInt(securityCode),
					getExpirationDate(expirationDate));

			this.interbank = new InterbankSubsystem();
			PaymentTransaction transaction = interbank.payOrder(card, amount, contents);
			return transaction;
		} catch (PaymentException | UnrecognizedException ex) {
			return null;
		}
	}

	public void processValidateCreditCard(String owner, String cvv, String cardCode, String date)
			throws InvalidCardException {
		if (!validateCardCode(cardCode))
			throw new InvalidCardException("Card number sai");
		else if (!validateCardOwner(owner))
			throw new InvalidCardException("Card owner sai");
		else if (getExpirationDate(date).equals("-1"))
			throw new InvalidCardException("Card date sai");
		else if (!validateCardCVV(cvv))
			throw new InvalidCardException("Card CVV sai");
	}

	public boolean validateCardOwner(String owner) {
		if (owner.matches("^(Group){1}[\\s][0-9]{1,2}$")) {
			return true;
		}
		return false;

	}

	public boolean validateCardCVV(String cvv) {
		if (cvv.matches("^[0-9]{2,3}$"))
			return true;
		return false;
	}

	public boolean validateCardCode(String cardCode) {
		if (cardCode.matches("^[a-z]{4}[0-9]{1}_[a-z]{5}[0-9]{1,2}_[0-9]{4}$"))
			return true;
		else
			return false;
	}

	/**
	 * Validate the input date which should be in the format "mm/yy", and then
	 * return a {@link java.lang.String String} representing the date in the
	 * required format "mmyy" .
	 * 
	 * @param date - the {@link java.lang.String String} represents the input date
	 * @return {@link java.lang.String String} - date representation of the required
	 *         format
	 * @throws InvalidCardException - if the string does not represent a valid date
	 *                              in the expected format
	 */
	public String getExpirationDate(String date) {
		if(date.matches("^0?([1-9]|10|11|12)\\/[2-9][0-9]$")) {
			return date.split("/")[0] + date.split("/")[1];
		}
		else
			return "-1";
	}
	
	 
	/**
	 * A function to get time rented to minute
	 */
	public boolean validateCalculatedTime(String timeStart) {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(timeStart);
			d2 = format.parse(Utils.getToday());
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		long diff = d2.getTime() - d1.getTime();
		long diffMinutes = diff / (60 * 1000);
		if ( diffMinutes >0) return true;
		return false;
	}
}
package entity;

import entity.payment.PaymentTransaction;

public class BikeRentingObject {

	private String bikeName;

	private String bikeCode;

	private String stationName;

	private String transactionId;
	private PaymentTransaction paymentTransaction;

	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

	public String getBikeCode() {
		return bikeCode;
	}

	public void setBikeCode(String bikeCode) {
		this.bikeCode = bikeCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String gettransactionId() {
		return transactionId;
	}

	public void settransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BikeRentingObject(String bikeName, String bikeCode, String stationName, String transactionId,
			PaymentTransaction paymentTransaction) {
		super();
		this.bikeName = bikeName;
		this.bikeCode = bikeCode;
		this.stationName = stationName;
		this.transactionId = transactionId;
		this.paymentTransaction = paymentTransaction;
	}

	public BikeRentingObject() {
		super();
	}

	public PaymentTransaction getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}

	@Override
	public String toString() {
		return "BikeRentingObject [bikeName=" + bikeName + ", bikeCode=" + bikeCode + ", stationName=" + stationName
				+ ", transactionId=" + transactionId + "]";
	}

}

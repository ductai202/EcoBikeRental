package entity.payment;

public class PaymentTransaction {
	private String errorCode;
	private PaymentCard card;
	private String transactionId;
	private String transactionContent;
	private int amount;
	private String createdAt;

	public PaymentTransaction() {
		super();
	}

	public PaymentTransaction(String errorCode, PaymentCard card, String transactionId, String transactionContent,
			int amount, String createdAt) {
		super();
		this.errorCode = errorCode;
		this.card = card;
		this.transactionId = transactionId;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "PaymentTransaction [errorCode=" + errorCode + ", card=" + card.toString() + ", transactionId="
				+ transactionId + ", transactionContent=" + transactionContent + ", amount=" + amount + ", createdAt="
				+ createdAt + "]";
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public PaymentCard getCard() {
		return card;
	}

	public void setCard(PaymentCard card) {
		this.card = card;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}

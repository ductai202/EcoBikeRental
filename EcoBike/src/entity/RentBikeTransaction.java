package entity;



/**
 * @author ADMIN
 *
 */
public class RentBikeTransaction {

	private int rentBikeTransactionId;

	/**
	 * xe được thuê
	 */
	private int bikeId;

	/**
	 * id người thuê xe tham chiếu tới entity User
	 */
	private String transactionId;

	private int ownerId;

	public RentBikeTransaction() {
		super();
	}

	public RentBikeTransaction(int rentBikeTransactionId, int bikeId, String transactionId,  int ownerId) {
		super();
		this.rentBikeTransactionId = rentBikeTransactionId;
		this.bikeId = bikeId;
		this.transactionId = transactionId;
		this.ownerId = ownerId;
	}

	public int getRentBikeTransactionId() {
		return rentBikeTransactionId;
	}

	public void setRentBikeTransactionId(int rentBikeTransactionId) {
		this.rentBikeTransactionId = rentBikeTransactionId;
	}

	public int getBikeId() {
		return bikeId;
	}

	public void setBikeId(int bikeId) {
		this.bikeId = bikeId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}

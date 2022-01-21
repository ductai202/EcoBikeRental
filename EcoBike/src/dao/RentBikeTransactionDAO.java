package dao;

import entity.RentBikeTransaction;
import entity.payment.PaymentTransaction;

public interface RentBikeTransactionDAO extends BaseDAO<RentBikeTransaction> {
	 public RentBikeTransaction findByTransactionId(String transactionId);
	 
	 
}

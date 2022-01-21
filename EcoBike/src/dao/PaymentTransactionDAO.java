package dao;

import entity.payment.PaymentTransaction;

public interface PaymentTransactionDAO extends BaseDAO<PaymentTransaction> {
		public PaymentTransaction findByTransactionId(String id);
		public PaymentTransaction insertCreditCard(PaymentTransaction paymentTransaction);
}

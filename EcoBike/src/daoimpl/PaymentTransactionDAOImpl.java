package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connect.DBConnection;
import dao.PaymentTransactionDAO;
import entity.payment.CreditCard;
import entity.payment.PaymentTransaction;

public class PaymentTransactionDAOImpl implements PaymentTransactionDAO {

	@Override
	public List<PaymentTransaction> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentTransaction findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaymentTransaction insert(PaymentTransaction t) throws SQLException {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "insert into payment_transaction (errorCode, transactionId, transactionContent, "
					+ "amount, createdAt, cardCode) values (?, ?, ?, ?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, t.getErrorCode());
			preparedStatement.setString(2, t.getTransactionId());
			preparedStatement.setString(3, t.getTransactionContent());
			preparedStatement.setInt(4, t.getAmount());
			preparedStatement.setString(5, t.getCreatedAt());
			preparedStatement.setString(6, ((CreditCard) t.getCard()).getCardCode());
			preparedStatement.executeUpdate();
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(PaymentTransaction t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PaymentTransaction findByTransactionId(String id) {
		// TODO Auto-generated method stub
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "select * from payment_transaction where transactionId = ? ";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				paymentTransaction.setErrorCode(resultSet.getString("errorCode"));
				paymentTransaction.setTransactionContent(resultSet.getString("transactionContent"));
				paymentTransaction.setAmount(resultSet.getInt("amount"));
				paymentTransaction.setCreatedAt(resultSet.getString("createdAt"));
				paymentTransaction.setTransactionId(resultSet.getString("transactionId"));
				paymentTransaction.setCard(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentTransaction;
	}

	@Override
	public PaymentTransaction insertCreditCard(PaymentTransaction t) {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "insert into payment_transaction (errorCode, transactionId, transactionContent, "
					+ "amount, createdAt, cardCode) values (?, ?, ?, ?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, t.getErrorCode());
			preparedStatement.setString(2, t.getTransactionId());
			preparedStatement.setString(3, t.getTransactionContent());
			preparedStatement.setInt(4, t.getAmount());
			preparedStatement.setString(5, t.getCreatedAt());
			preparedStatement.setString(6, ((CreditCard) t.getCard()).getCardCode());
			preparedStatement.executeUpdate();
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

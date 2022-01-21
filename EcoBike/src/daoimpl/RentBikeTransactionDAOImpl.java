package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connect.DBConnection;
import dao.BikeDAO;
import dao.PaymentTransactionDAO;
import dao.RentBikeTransactionDAO;
import dao.UserDAO;
import entity.RentBikeTransaction;
import entity.payment.PaymentTransaction;
import entity.session.Session;

public class RentBikeTransactionDAOImpl implements RentBikeTransactionDAO {

	@Override
	public List<RentBikeTransaction> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentBikeTransaction findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RentBikeTransaction insert(RentBikeTransaction t) throws SQLException {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "insert into rent_bike_transaction (bikeId, transactionId, " + "ownerId) values (?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, t.getBikeId());
			preparedStatement.setString(2, t.getTransactionId());
			preparedStatement.setInt(3, t.getOwnerId());
			preparedStatement.executeUpdate();
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(RentBikeTransaction t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public RentBikeTransaction findByTransactionId(String transactionId) {
		// TODO Auto-generated method stub
		RentBikeTransaction rentBikeTransaction = new RentBikeTransaction();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "select * from rent_bike_transaction where transactionId = ? ";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, Session.getSession().getPaymentTransaction().getTransactionId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				rentBikeTransaction.setRentBikeTransactionId(resultSet.getInt("rentBikeTransactionId"));
				rentBikeTransaction.setBikeId(resultSet.getInt("bikeId"));
				rentBikeTransaction.setOwnerId(resultSet.getInt("ownerId"));
				rentBikeTransaction.setTransactionId(resultSet.getString("transactionId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rentBikeTransaction;
	}

}

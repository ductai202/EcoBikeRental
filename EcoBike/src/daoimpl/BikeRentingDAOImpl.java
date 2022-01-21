package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connect.DBConnection;
import dao.BikeRentingDAO;
import entity.BikeRentingObject;

public class BikeRentingDAOImpl implements BikeRentingDAO {

	@Override
	public List<BikeRentingObject> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BikeRentingObject findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BikeRentingObject insert(BikeRentingObject t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(BikeRentingObject t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BikeRentingObject getTransactionByUserId(int id) {
		// TODO Auto-generated method stub
		BikeRentingObject bikeRentingObject = new BikeRentingObject();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT bike.name as bikeName , station.name as stationName , bikeCode, transactionId  FROM rent_bike_transaction inner join bike on rent_bike_transaction.bikeId = bike.bikeId inner join station on bike.stationId = station.stationId WHERE ownerId = ? ORDER BY rentBikeTransactionId DESC LIMIT 1;";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bikeRentingObject.setBikeName(resultSet.getString("bikeName"));
				bikeRentingObject.setBikeCode(resultSet.getString("bikeCode"));
				bikeRentingObject.setStationName(resultSet.getString("stationName"));
				bikeRentingObject.settransactionId(resultSet.getString("transactionId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		return bikeRentingObject;
	}

}

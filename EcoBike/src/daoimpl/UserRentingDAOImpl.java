package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnection;
import dao.UserRentingDAO;
import entity.Bike;
import entity.Station;
import entity.UserRentingBikeObject;

public class UserRentingDAOImpl implements UserRentingDAO {

	@Override
	public List<UserRentingBikeObject> findAll() throws SQLException {
		List<UserRentingBikeObject> userRentingBikes = new ArrayList<UserRentingBikeObject>();
		// TODO Auto-generated method stub
		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT userId, username, bike.name as bikeName , bikeCode, type, station.name as stationName  FROM ecobike.user left join rent_bike_transaction on rent_bike_transaction.ownerId = user.userId left join bike on rent_bike_transaction.bikeId = bike.bikeId left join station on bike.stationId = station.stationId  where active = 1 GROUP BY user.username ORDER BY rentBikeTransactionId DESC ;";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserRentingBikeObject userRentingBike = new UserRentingBikeObject();
				userRentingBike.setUserId(resultSet.getInt("userId"));
				userRentingBike.setUserName(resultSet.getString("username"));
				userRentingBike.setBikeName(resultSet.getString("bikeName"));
				userRentingBike.setBikeCode(resultSet.getString("bikeCode"));
				userRentingBike.setType(resultSet.getString("type"));
				userRentingBike.setStationName(resultSet.getString("stationName"));
				userRentingBikes.add(userRentingBike);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRentingBikes;
	}

	@Override
	public UserRentingBikeObject findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRentingBikeObject insert(UserRentingBikeObject t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(UserRentingBikeObject t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}

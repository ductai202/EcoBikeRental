package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnection;
import dao.StationDAO;
import entity.Station;

public class StationDAOImpl implements StationDAO {

	@Override
	public List findAll() throws SQLException {
		List stations = new ArrayList();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT station.*, COUNT(bike.bikeId) as \"totalBike\" FROM bike RIGHT join station on bike.stationId = station.stationId GROUP BY station.stationId, station.name, station.address, station.totalParking;";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Station station = new Station();
				station.setStationId(resultSet.getInt("stationId"));
				station.setName(resultSet.getString("name"));
				station.setAddress(resultSet.getString("address"));
				station.setTotalBike(resultSet.getInt("totalBike"));
				station.setTotalParking(resultSet.getInt("totalParking"));
				stations.add(station);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stations;
	}

	@Override
	public Station findById(int id) throws SQLException {

		return null;
	}

	@Override
	public Station insert(Station t) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection conn = DBConnection.getConnection()) {
			String query = "INSERT INTO station (name, address, totalParking) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getAddress());
			preparedStatement.setInt(3, t.getTotalParking());
			preparedStatement.executeUpdate();
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Station t) throws SQLException {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "UPDATE station SET name = ?, address = ?, totalParking = ? WHERE stationId = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setString(2, t.getAddress());
			preparedStatement.setInt(3, t.getTotalParking());
			preparedStatement.setInt(4, t.getStationId());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Station findByName(String stationName) {
		// TODO Auto-generated method stub
		Station station = new Station();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "SELECT * from station where name=? ";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, stationName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				station.setStationId(resultSet.getInt("stationId"));
				station.setName(resultSet.getString("name"));
				station.setAddress(resultSet.getString("address"));
				station.setTotalBike(resultSet.getInt("totalBike"));
				station.setTotalParking(resultSet.getInt("totalParking"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return station;
	}
}

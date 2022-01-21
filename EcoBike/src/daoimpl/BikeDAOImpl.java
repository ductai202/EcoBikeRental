package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.DBConnection;
import dao.BikeDAO;
import entity.Bike;
import entity.session.Session;

public class BikeDAOImpl implements BikeDAO {

	@Override
	public List<Bike> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bike findById(int bikeId) throws SQLException {
		Bike bike = new Bike();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "select * from bike where bikeId = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, bikeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bike.setBikeId(resultSet.getInt("bikeId"));
				bike.setName(resultSet.getString("name"));
				bike.setValue(resultSet.getInt("value"));
				bike.setWeight(resultSet.getFloat("weight"));
				bike.setBikeCode(resultSet.getString("bikeCode"));
				bike.setStationId(resultSet.getInt("stationId"));
				bike.setInUse(resultSet.getBoolean("isInUse"));
				bike.setType(resultSet.getString("type"));
				bike.setProducer(resultSet.getString("producer"));
				bike.setDateSX(resultSet.getString("dateSX"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bike;
	}

	@Override
	public Bike insert(Bike t) throws SQLException {
		// TODO Auto-generated method stub
		try (Connection conn = DBConnection.getConnection()) {
			String query = "INSERT INTO bike (name, value, weight, bikeCode, stationId, isInUse, type, producer, dateSX) VALUES ("
					+ "?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getValue());
			preparedStatement.setFloat(3, t.getWeight());
			preparedStatement.setString(4, t.getBikeCode());
			preparedStatement.setInt(5, t.getStationId());
			preparedStatement.setBoolean(6, t.isInUse());
			preparedStatement.setString(7, t.getType());
			preparedStatement.setString(8, t.getProducer());
			preparedStatement.setString(9, t.getDateSX());
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
	public boolean update(Bike t) throws SQLException {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "UPDATE bike SET name = ?,  value = ?, weight = ?, bikeCode = ?, isInUse = ?, type = ?, "
					+ "producer = ?, dateSX = ? WHERE bikeId = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, t.getName());
			preparedStatement.setInt(2, t.getValue());
			preparedStatement.setFloat(3, t.getWeight());
			preparedStatement.setString(4,  t.getBikeCode());
			preparedStatement.setBoolean(5, t.isInUse());
			preparedStatement.setString(6, t.getType());
			preparedStatement.setString(7, t.getProducer());
			preparedStatement.setString(8, t.getDateSX());
			preparedStatement.setInt(9, t.getBikeId());
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
	public List getBikesByStationId(int stationId) {
		List bikes = new ArrayList<Bike>();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "select * from bike where stationId = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, stationId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Bike bike = new Bike();
				bike.setBikeId(resultSet.getInt("bikeId"));
				bike.setName(resultSet.getString("name"));
				bike.setValue(resultSet.getInt("value"));
				bike.setWeight(resultSet.getFloat("weight"));
				bike.setBikeCode(resultSet.getString("bikeCode"));
				bike.setStationId(resultSet.getInt("stationId"));
				bike.setInUse(resultSet.getBoolean("isInUse"));
				bike.setType(resultSet.getString("type"));
				bike.setProducer(resultSet.getString("producer"));
				bike.setDateSX(resultSet.getString("dateSX"));
				bikes.add(bike);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bikes;
	}

	@Override
	public boolean updateRentBikeById(int bikeId) {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "update bike set isInUse=? where bikeId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, bikeId);
			preparedStatement.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateReturnBikeById(int bikeId, int stationId) {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "update bike set isInUse=?, stationId = ? where bikeId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setBoolean(1, false);
			preparedStatement.setInt(2, stationId);
			preparedStatement.setInt(3, bikeId);
			preparedStatement.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Bike getByBikeCode(String bikeCode) {
		// TODO Auto-generated method stub
		Bike bike = new Bike();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "select * from bike where bikeCode = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, bikeCode);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bike.setBikeId(resultSet.getInt("bikeId"));
				bike.setName(resultSet.getString("name"));
				bike.setValue(resultSet.getInt("value"));
				bike.setWeight(resultSet.getFloat("weight"));
				bike.setBikeCode(resultSet.getString("bikeCode"));
				bike.setStationId(resultSet.getInt("stationId"));
				bike.setInUse(resultSet.getBoolean("isInUse"));
				bike.setType(resultSet.getString("type"));
				bike.setProducer(resultSet.getString("producer"));
				bike.setDateSX(resultSet.getString("dateSX"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bike;
	}
}

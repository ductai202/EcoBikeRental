package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connect.DBConnection;
import dao.UserDAO;
import entity.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) throws SQLException {
		// TODO Auto-generated method stub
		User user = new User();
		try (Connection conn = DBConnection.getConnection()) {
			String query = "select * from user where userId = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setUserId(resultSet.getInt("userId"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setActive(resultSet.getBoolean("active"));
				user.setRole(resultSet.getString("role"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User insert(User t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User t) throws SQLException {
		return false;
	}

	public boolean updateActiveUser(int id, boolean isActive) {
		try (Connection conn = DBConnection.getConnection()) {
			String query = "update user set active=? where userId = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setBoolean(1, isActive);
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User login(String username, String password) {
		String sql = "SELECT * FROM user WHERE user.username = ? AND user.password = ?;";
		User user = new User();
		try (Connection conn = DBConnection.getConnection()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setUserId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(2));
				user.setActive(resultSet.getBoolean(4));
				user.setRole(resultSet.getString(5));
			}
			if (user.getUserId() > 0)
				return user;
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}

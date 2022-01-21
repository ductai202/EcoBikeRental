package dao;

import entity.User;

public interface UserDAO extends BaseDAO<User> {
	public boolean updateActiveUser(int id, boolean isActive);
	public User login(String username, String password);
}

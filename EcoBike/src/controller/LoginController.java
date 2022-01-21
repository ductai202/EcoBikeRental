package controller;

import java.sql.SQLException;
import dao.UserDAO;
import daoimpl.UserDAOImpl;
import entity.User;




public class LoginController extends BaseController{

	private UserDAO userDAO;
    
	public LoginController() {
		userDAO = new UserDAOImpl();
	}
    public User login(String username, String password) throws SQLException{
    	return userDAO.login(username, password);
    }

    
}

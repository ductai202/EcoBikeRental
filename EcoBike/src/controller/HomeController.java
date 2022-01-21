package controller;

import java.sql.SQLException;
import java.util.List;

import dao.StationDAO;
import daoimpl.StationDAOImpl;

public class HomeController extends BaseController{

	private StationDAO stationDAO;
    
	public HomeController() {
		stationDAO = new StationDAOImpl();
	}
    public List getAllStation() throws SQLException{
    	return stationDAO.findAll();
    }

}

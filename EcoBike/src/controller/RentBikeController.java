package controller;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.BikeDAO;
import dao.BikeRentingDAO;
import dao.PaymentTransactionDAO;
import dao.RentBikeTransactionDAO;
import dao.UserDAO;
import dao.UserRentingDAO;
import daoimpl.BikeDAOImpl;
import daoimpl.BikeRentingDAOImpl;
import daoimpl.PaymentTransactionDAOImpl;
import daoimpl.RentBikeTransactionDAOImpl;
import daoimpl.UserDAOImpl;
import daoimpl.UserRentingDAOImpl;
import entity.BikeRentingObject;
import entity.RentBikeTransaction;
import entity.UserRentingBikeObject;
import entity.payment.PaymentTransaction;
import entity.session.Session;

/**
 * @author ADMIN
 *
 */
public class RentBikeController {

	private RentBikeTransactionDAO rentBikeTransactionDAO;
	private UserRentingDAO userRentingDAO;
	private BikeRentingDAO bikeRentingDAO;
	private UserDAO userDAO;
	private BikeDAO bikeDAO;
	private PaymentTransactionDAO paymentTransactionDAO;

	public RentBikeController() {
		rentBikeTransactionDAO = new RentBikeTransactionDAOImpl();
	}

	public boolean processRentBikeTransaction(PaymentTransaction trans) {
		if (Session.getSession().getUser() == null)
			return false;
			try {
				userDAO = new UserDAOImpl();
				bikeDAO = new BikeDAOImpl();
				boolean updateUserActive = userDAO.updateActiveUser(Session.getSession().getUser().getUserId(), true);
				boolean updateRentBikeById = bikeDAO.updateRentBikeById(Session.getSession().getBike().getBikeId());
				if (updateRentBikeById && updateUserActive) {
					Session session = Session.getSession();
					// luu paymentTransaction
					paymentTransactionDAO = new PaymentTransactionDAOImpl();
					paymentTransactionDAO.insert(trans);
					// luu rentBikeTransaciton, dang mac dinh luu o san 1
					RentBikeTransaction rbt = new RentBikeTransaction(0, Session.getSession().getBike().getBikeId(),
							trans.getTransactionId(), Session.getSession().getUser().getUserId());
					this.rentBikeTransactionDAO.insert(rbt);
					Session.getSession().getUser().setActive(true);
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	}

	public boolean processRentBikeTransaction(PaymentTransaction trans, int stationId) {
		if (Session.getSession().getUser() == null)
			return false;
		BikeDAO bikeDAO = new BikeDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		// TODO Auto-generated method stub
		try {
			boolean updateUserActive = userDAO.updateActiveUser(Session.getSession().getUser().getUserId(), false);
			boolean updateReturnBikeById = bikeDAO.updateReturnBikeById(Session.getSession().getBike().getBikeId(),
					stationId);
			if (updateReturnBikeById && updateUserActive) {
				Session session = Session.getSession();
				// luu paymentTransaction
				 paymentTransactionDAO = new PaymentTransactionDAOImpl();

				paymentTransactionDAO.insert(trans);
				Session.getSession().getUser().setActive(false);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * lấy thông tin thuê xe của 1 người dùng
	 */
	public BikeRentingObject getInfoBikeRentingOfUser(int userId) {
		bikeRentingDAO = new BikeRentingDAOImpl();
		BikeRentingObject bikeRentingObject = bikeRentingDAO.getTransactionByUserId(userId);
		paymentTransactionDAO = new PaymentTransactionDAOImpl();
		bikeRentingObject.setPaymentTransaction(paymentTransactionDAO.findByTransactionId(bikeRentingObject.gettransactionId()));
		return bikeRentingObject;

	}

	/**
	 * lấy tát cả thông tin người dùng hiện tại đang thuê xe của admin
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<UserRentingBikeObject> getAllInfoBikeRentingByAdmin() throws SQLException {
		if (Session.getSession().getUser() != null && Session.getSession().getUser().getRole().equals("admin")) {
			userRentingDAO = new UserRentingDAOImpl();
			return userRentingDAO.findAll();
		}
		return null;
	}
	
	

}

package views.screen.home;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import controller.RentBikeController;
import dao.BikeRentingDAO;
import dao.PaymentTransactionDAO;
import daoimpl.BikeRentingDAOImpl;
import daoimpl.PaymentTransactionDAOImpl;
import entity.Bike;
import entity.BikeRentingObject;
import entity.Station;
import entity.UserRentingBikeObject;
import entity.payment.PaymentTransaction;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.return_bike.AStationReturnBike;
import views.screen.return_bike.ReturnBikeHandler;

public class AUserRentingBike extends FXMLScreenHandler {

	@FXML
	private Label infoUser;

	@FXML
	private HBox areaInfoStationHBox;

	private UserRentingBikeObject userRentingBikeObject;

	private UserRentingBikeHandler userRentingBikeHandler;

	private Bike bike;
	private static Logger LOGGER = Utils.getLogger(AStationReturnBike.class.getName());

	public AUserRentingBike(String screenPath, UserRentingBikeObject userRentingBikeObject,
			UserRentingBikeHandler userRentingBikeHandler) throws IOException {
		super(screenPath);
		// TODO Auto-generated constructor stub
		this.userRentingBikeObject = userRentingBikeObject;
		this.userRentingBikeHandler = userRentingBikeHandler;
		RentBikeController bikeRentingController = new RentBikeController();
		BikeRentingObject bikeRentingObject = bikeRentingController.getInfoBikeRentingOfUser(userRentingBikeObject.getUserId());
		this.infoUser.setText(" - Tên người dùng : " + userRentingBikeObject.getUserName() + " - Tên xe : "
				+ userRentingBikeObject.getBikeName() + " - Mã xe : " + userRentingBikeObject.getBikeCode()
				+ " - Loại xe : " + userRentingBikeObject.getType() + " - Sân thuê : "
				+ userRentingBikeObject.getStationName() + " - Thời gian thuê : "
				+ bikeRentingObject.getPaymentTransaction().getCreatedAt() + " - Đã thuê được : "
				+ String.valueOf(calculateTime(bikeRentingObject.getPaymentTransaction().getCreatedAt())) + " phút .");

	}

	/**
	 * A function to get time rented to minute
	 */
	public int calculateTime(String timeStart) {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(timeStart);
			d2 = format.parse(Utils.getToday());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long diff = d2.getTime() - d1.getTime();
		long diffMinutes = diff / (60 * 1000);
		return (int) diffMinutes;
	}

}

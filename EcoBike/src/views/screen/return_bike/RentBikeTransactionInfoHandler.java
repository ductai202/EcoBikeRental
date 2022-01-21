package views.screen.return_bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BikeController;
import controller.PaymentController;
import controller.RentBikeController;
import dao.BikeDAO;
import dao.BikeRentingDAO;
import dao.PaymentTransactionDAO;
import dao.RentBikeTransactionDAO;
import dao.StationDAO;
import dao.UserDAO;
import daoimpl.BikeDAOImpl;
import daoimpl.BikeRentingDAOImpl;
import daoimpl.PaymentTransactionDAOImpl;
import daoimpl.RentBikeTransactionDAOImpl;
import daoimpl.StationDAOImpl;
import daoimpl.UserDAOImpl;
import entity.Bike;
import entity.BikeRentingObject;
import entity.RentBikeTransaction;
import entity.Station;
import entity.User;
import entity.payment.PaymentTransaction;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.payment.PaymentScreenHandler;

public class RentBikeTransactionInfoHandler extends BaseScreenHandler implements Initializable {

	public static Logger LOGGER = Utils.getLogger(BikeDetailHandler.class.getName());

	@FXML
	private Label owner;

	@FXML
	private Label stationName;

	@FXML
	private Label bikeCode;

	@FXML
	private Label bikeName;

	@FXML
	private Label bikeType;

	@FXML
	private Label cost;

	@FXML
	private Label timeStart;

	@FXML
	private Label timeEnd;

	@FXML
	private Label deposit;

	@FXML
	private Button btnTurnBack;

	@FXML
	private Button btnConfirm;
	
	int time = 0;

	public RentBikeTransactionInfoHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		BikeController bikeController = new BikeController();
		// TODO Auto-generated constructor stub

		btnConfirm.setOnMouseClicked(event -> {
			BaseScreenHandler paymentScreen;
			try {
				Bike bike = Session.getSession().getBike();

				paymentScreen = new PaymentScreenHandler(stage, Configs.PAYMENT_FORM_SCREEN_SCREEN_PATH, bike,
						"return bike", Integer.valueOf(cost.getText()));
				paymentScreen.setBController(new PaymentController());
				paymentScreen.setPreviousScreen(this);
				paymentScreen.setHomeScreenHandler(homeScreenHandler);
				paymentScreen.setScreenTitle("Payment Screen");
				this.stage.close();
				paymentScreen.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		btnTurnBack.setOnMouseClicked(event -> {
			try {
				ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(this.stage,
						Configs.RETURN_BIKE_SCREEN_PATH);
				returnBikeHandler.setPreviousScreen(this);
				returnBikeHandler.show();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}

		});

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BikeController bikeController = new BikeController();
		RentBikeController rentBikeController = new RentBikeController();
		BikeRentingObject bikeRentingObject = rentBikeController
				.getInfoBikeRentingOfUser(Session.getSession().getUser().getUserId());
		Bike bike = bikeController.getByBikeCode(bikeRentingObject.getBikeCode());
		User user = Session.getSession().getUser();
		// get information
		owner.setText(user.getUsername());
		bikeName.setText(bike.getName());
		bikeCode.setText(bike.getBikeCode());
		bikeType.setText(bike.getTypeBike());
		timeStart.setText(bikeRentingObject.getPaymentTransaction().getCreatedAt());
		stationName.setText(bikeRentingObject.getStationName());
		this.time = calculateTime(bikeRentingObject.getPaymentTransaction().getCreatedAt());
		timeEnd.setText(Utils.getToday());
		deposit.setText(String.valueOf(bikeController.getBikeDeposit(bike.getType())));
		cost.setText(String.valueOf(bikeController.getBikeRenting(bike.getType(), time)));
		// luu session
		Session.getSession().setBike(bike);
	}
}

package views.screen.return_bike;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.BikeController;
import controller.RentBikeController;
import dao.BikeDAO;
import dao.BikeRentingDAO;
import dao.PaymentTransactionDAO;
import daoimpl.BikeDAOImpl;
import daoimpl.BikeRentingDAOImpl;
import daoimpl.PaymentTransactionDAOImpl;
import entity.Bike;
import entity.BikeRentingObject;
import entity.payment.PaymentTransaction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseButton;
import views.screen.BaseScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import entity.session.Session;

public class BikeRentingDetailHandler extends BaseScreenHandler implements Initializable {

	public static Logger LOGGER = Utils.getLogger(BikeDetailHandler.class.getName());

	// Dang mac dinh de user 1 dang thue xe
	@FXML
	private Label bikeName;

	@FXML
	private Label bikeCode;

	@FXML
	private Label bikeType;

	@FXML
	private Label stationName;

	@FXML
	private Label timeStart;

	@FXML
	private Label timeRented;

	@FXML
	private ImageView imageBike;

	@FXML
	private Button btnReturnBike;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnBikeRentingDetail;
	
	@FXML 
	private Label usernameLabel;

	@FXML
	private Button homeButton;

	public BikeRentingDetailHandler(Stage stage, String screenPath) throws IOException {
		// TODO Auto-generated constructor stub
		super(stage, screenPath);

		BaseButton.baseButton(this.getStage(), homeButton, btnBikeRentingDetail, btnLogOut);

		btnReturnBike.setOnMouseClicked(event -> {
			LOGGER.info("User clicked to choose station to return");
			try {

				ReturnBikeHandler returnBikeHandler = new ReturnBikeHandler(this.stage,
						Configs.RETURN_BIKE_SCREEN_PATH);
				returnBikeHandler.show();
			} catch (Exception e) {
				e.printStackTrace();
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

		this.usernameLabel.setText(Session.getSession().getUser().getUsername());
		// load Session
		RentBikeController rentBikeController = new RentBikeController();
		BikeRentingObject bikeRentingObject = rentBikeController.getInfoBikeRentingOfUser(Session.getSession().getUser().getUserId());
		// bike va station
		Bike bike = (new BikeController()).getByBikeCode(bikeRentingObject.getBikeCode());
		// get information
		bikeName.setText(bikeRentingObject.getBikeName());
		bikeCode.setText(bikeRentingObject.getBikeCode());
		bikeType.setText((bike.getTypeBike()));
		timeStart.setText(bikeRentingObject.getPaymentTransaction().getCreatedAt());
		stationName.setText(bikeRentingObject.getStationName());
		int time = calculateTime(bikeRentingObject.getPaymentTransaction().getCreatedAt());
		timeRented.setText(time + " phút");
		imageBike.setImage(bike.getImageBike());

	}
}

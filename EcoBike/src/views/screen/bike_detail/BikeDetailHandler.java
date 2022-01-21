package views.screen.bike_detail;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.BikeController;
import entity.Bike;
import entity.session.Session;
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
import views.screen.confirm_rent_bike.ConfirmRentBikeHandler;
import views.screen.list_bike.ListBikeHandler;

public class BikeDetailHandler extends BaseScreenHandler implements Initializable {

	public static Logger LOGGER = Utils.getLogger(BikeDetailHandler.class.getName());

	@FXML
	private Label bikeCodeLabel;

	@FXML
	private Label bikeNameLabel;

	@FXML
	private Label typeBikeLabel;
	@FXML
	private Label bikeWeightLabel;
	@FXML
	private Label bikeValueLabel;
	@FXML
	private Label bikeInUseLabel;
	@FXML
	private Label bikeProducerLabel;
	@FXML
	private Label bikeDateLabel;
	@FXML
	private Label bikeDepositLabel;
	@FXML
	private Label bikeRent1hLabel;
	@FXML
	private Label bikeRent1h30Label;
	@FXML
	private Label bikeRent2hLabel;
	@FXML
	private Button backButton;

	@FXML
	private ImageView bikeImageView;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnBikeRentingDetail;

	@FXML
	private Button homeButton;

	@FXML
	private Button rentBikeButton;

	@FXML
	private Label usernameLabel;

	private Bike bike;

	public BikeDetailHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		bikeDetailButton();
	}

	public BikeController getBController() {
		return (BikeController) super.getBController();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new BikeController());
		this.usernameLabel.setText(Session.getSession().getUser().getUsername());

		try {
			bike = Session.getSession().getBike();
			LOGGER.info(bike.toString());
			setBikeInfo();
		} catch (SQLException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
		if (bike.isInUse())
			this.rentBikeButton.setDisable(true);
		if (Session.getSession().getUser() != null
				&& Session.getSession().getUser().getRole().equals(Configs.ADMIN_ROLE)) {
			this.rentBikeButton.setVisible(false);
		}
	}

	private void setBikeInfo() throws SQLException {
		this.bikeImageView.setImage(bike.getImageBike());
		this.bikeCodeLabel.setText("Mã xe: " + bike.getBikeCode());
		this.bikeNameLabel.setText("Tên xe: " + bike.getName());
		this.bikeInUseLabel.setText(bike.isInUse() ? "Đang thuê" : "Sẵn có");
		this.typeBikeLabel.setText("Loại : " + bike.getTypeBike());
		this.bikeWeightLabel.setText("Trọng lượng: " + bike.getWeight() + " kg");
		this.bikeValueLabel.setText("Giá thành: " + Utils.getCurrencyFormat(bike.getValue()));
		this.bikeDateLabel.setText("Ngày sản xuất: " + bike.getDateSX());
		this.bikeProducerLabel.setText("Nhà sản xuất: " + bike.getProducer());
		this.bikeDepositLabel
				.setText("Tiền cọc: " + Utils.getCurrencyFormat(this.getBController().getBikeDeposit(bike.getType())));
		this.bikeRent1hLabel.setText(
				"Thuê 1h: " + Utils.getCurrencyFormat(this.getBController().getBikeRenting(bike.getType(), 60)));
		this.bikeRent1h30Label.setText(
				"Thuê 1h30: " + Utils.getCurrencyFormat(this.getBController().getBikeRenting(bike.getType(), 90)));
		this.bikeRent2hLabel.setText(
				"Thuê 2h: " + Utils.getCurrencyFormat(this.getBController().getBikeRenting(bike.getType(), 120)));
	}

	private void bikeDetailButton() {
		BaseButton.baseButton(this.getStage(), homeButton, btnBikeRentingDetail, btnLogOut);
		backButton.setOnMouseClicked(event -> {

			BaseScreenHandler listBikeHandler;
			try {
				LOGGER.info("User clicked to view detail bikes");
				listBikeHandler = new ListBikeHandler(this.stage, Configs.LIST_BIKE_SCREEN_PATH);
				listBikeHandler.setHomeScreenHandler(this.homeScreenHandler);
				listBikeHandler.setBController(new BikeController());
				listBikeHandler.show();
			} catch (IOException e1) {
				LOGGER.info(e1.getMessage());
			}
		});

		rentBikeButton.setOnMouseClicked(event -> {

			BaseScreenHandler confirmRentBike;
			try {
				confirmRentBike = new ConfirmRentBikeHandler(this.stage, bike);
				confirmRentBike.show();
			} catch (IOException e1) {
				LOGGER.info(e1.getMessage());
			}
		});
	}

}

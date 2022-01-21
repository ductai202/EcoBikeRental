package views.screen.payment;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import entity.Bike;
import entity.payment.PaymentTransaction;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.home.HomeScreenHandler;

public class RentBikeSuccessHandler extends BaseScreenHandler {

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
	private Label bikeDepositLabel;
	@FXML
	private Label bikeDate;
	@FXML
	private Label bikeProducer;

	@FXML
	private Button backButton;
	@FXML
	private Label bikeCreateAtLabel;
	@FXML
	private ImageView bikeImageView;

	private Bike bike;
	private PaymentTransaction trans;

	public RentBikeSuccessHandler(Stage stage, String screenPath, Bike bike, PaymentTransaction trans)
			throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		this.trans = trans;
		setRentBikeSuccessInfo();
		backButton.setOnMouseClicked(e -> {
			Session session = Session.getSession();
			session.setPaymentTransaction(trans);
			LOGGER.info("TransactionId : " + session.getPaymentTransaction());
			try {
				BaseScreenHandler detailBike = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
				detailBike.setPreviousScreen(this);
				detailBike.setHomeScreenHandler(homeScreenHandler);
				detailBike.setScreenTitle("Home");
				detailBike.show();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
	}

	private void setRentBikeSuccessInfo() {

		if (bike.getValue() > 0) {
			this.bikeValueLabel.setText("Giá thành: " + Utils.getCurrencyFormat(bike.getValue()));
		}
		this.bikeImageView.setImage(bike.getImageBike());
		this.typeBikeLabel.setText("Loại xe: " + bike.getTypeBike());
		this.bikeCodeLabel.setText("Mã xe: " + bike.getBikeCode());
		this.bikeDepositLabel.setText("Tiền cọc: " + Utils.getCurrencyFormat(trans.getAmount()));
		this.bikeCreateAtLabel.setText("Bắt đầu thuê: " + trans.getCreatedAt());
		this.bikeDate.setText("Ngày sản xuất: " + bike.getDateSX());
		this.bikeProducer.setText("Nhà sản xuất: " + bike.getProducer());
	}

}

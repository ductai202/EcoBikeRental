package views.screen.confirm_rent_bike;

import java.io.IOException;
import java.sql.SQLException;

import controller.BikeController;
import controller.PaymentController;
import dao.BikeDAO;
import daoimpl.BikeDAOImpl;
import entity.Bike;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.payment.PaymentScreenHandler;
import views.screen.popup.PopupScreen;

public class ConfirmRentBikeHandler extends BaseScreenHandler {

	@FXML
	ImageView tickicon;

	@FXML
	Label message;

	@FXML
	private Button confirmButton;

	@FXML
	private Button backButton;

	@FXML
	private ImageView bikeImageView;

	@FXML
	private Label typeBikeLabel;
	@FXML
	private Label bikeDepositLabel;
	@FXML
	private Label bikeCodeLabel;
	@FXML
	private Label bikeNameLabel;
	Bike bike;
	BikeController bikeController;

	public ConfirmRentBikeHandler(Stage stage, Bike bike) throws IOException {
		super(new Stage(), Configs.CONFIRM_RENT_BIKE_SCREEN_PATH);
		bikeController = new BikeController();
		this.bike = bike;
		setInfoBike();
		confirmButton.setOnMouseClicked(event -> {
			try {
				Bike checkBikeInUse = bikeController.getBikeByBikeId(bike.getBikeId());
				if (!checkBikeInUse.isInUse() && Session.getSession().getUser() != null
						&& Session.getSession().getUser().getActive() == false) {
					BaseScreenHandler paymentScreen;
					Session.getSession();
					paymentScreen = new PaymentScreenHandler(stage, Configs.PAYMENT_FORM_SCREEN_SCREEN_PATH, bike,
							"rent bike", 0);
					paymentScreen.setBController(new PaymentController());
					paymentScreen.setPreviousScreen(this);
					paymentScreen.setHomeScreenHandler(homeScreenHandler);
					paymentScreen.setScreenTitle("Payment Screen");
					this.stage.close();
					paymentScreen.show();
				} else if (Session.getSession().getUser() != null
						&& Session.getSession().getUser().getActive() == true) {
					PopupScreen.error("bạn đang thuê xe");
				} else {
					Session.getSession().setBike(checkBikeInUse);
					PopupScreen.error("xin lỗi xe đang được sử dụng");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		backButton.setOnMouseClicked(event -> {
			this.stage.close();
		});
	}

	private void setInfoBike() {
		this.bikeImageView.setImage(bike.getImageBike());
		this.bikeCodeLabel.setText("Mã xe: " + bike.getBikeCode());
		this.bikeNameLabel.setText("Tên xe: " + bike.getName());
		this.bikeDepositLabel.setText("Tiền cọc: " + Utils.getCurrencyFormat(bikeController.getBikeDeposit(bike.getType())));
		this.typeBikeLabel.setText("Loại xe: " + bike.getTypeBike());

	}

}

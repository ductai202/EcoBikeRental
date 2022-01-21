package views.screen.payment;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import controller.BikeController;
import controller.PaymentController;
import controller.RentBikeController;
import dao.BikeDAO;
import dao.BikeRentingDAO;
import daoimpl.BikeDAOImpl;
import daoimpl.BikeRentingDAOImpl;
import entity.Bike;
import entity.BikeRentingObject;
import entity.payment.PaymentTransaction;
import entity.session.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.popup.PopupScreen;
import views.screen.result.ResultScreenHandler;

public class PaymentScreenHandler extends BaseScreenHandler {

	public static Logger LOGGER = Utils.getLogger(BikeDetailHandler.class.getName());

	@FXML
	private Label pageTitle;

	@FXML
	private TextField cardNumber;

	@FXML
	private TextField holderName;

	@FXML
	private TextField expirationDate;

	@FXML
	private TextField securityCode;
	@FXML
	private Button confirmPaymentButton;

	@FXML
	private TextArea transactionContent;

	@FXML
	private Button backButton;
	@FXML
	private ImageView loadingImage;

	@FXML
	private VBox areaPaymentVBox;

	@FXML
	private RadioButton paymentRadioButton;

	private Bike bike;
	BikeController bikeController;
	int amount = -1;

	public PaymentScreenHandler(Stage stage, String screenPath, Bike bike, String type, int amount) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
		bikeController = new BikeController();
		this.amount = amount;
		confirmPaymentButton.setOnMouseClicked(e -> {

			// rent bike
			if (type.equals("rent bike")) {
				try {
					PaymentController ctrl = (PaymentController) getBController();

					PaymentTransaction transaction = ctrl.payOrder(bikeController.getBikeDeposit(bike.getType()),
							transactionContent.getText(), cardNumber.getText(), holderName.getText(),
							expirationDate.getText(), securityCode.getText());

					if (transaction != null && transaction.getErrorCode().equals("00")) {
						RentBikeController rent = new RentBikeController();
						rent.processRentBikeTransaction(transaction);
						BaseScreenHandler resultScreen = new RentBikeSuccessHandler(this.stage,
								Configs.RENT_BIKE_SUCCESS_SCREEN_PATH, bike, transaction);
						resultScreen.setPreviousScreen(this);
						resultScreen.setHomeScreenHandler(homeScreenHandler);
						resultScreen.setScreenTitle("Result Screen");
						resultScreen.show();
						// LOGGER.info("TransactionId : " +session.getTransactionId());
					} else {
						System.out.println(transaction.toString());
						PopupScreen.error("Giao dịch thất bại");
					}
				} catch (Exception exp) {
					try {
						if (exp.getMessage() != null)
							PopupScreen.error(exp.getMessage());
						else
							PopupScreen.error("Giao dịch thất bại");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			// return bike
			if (type.equals("return bike")) {
				try {
					System.out.println("amount: " + amount);
					PaymentController ctrl = (PaymentController) getBController();
					PaymentTransaction transaction = ctrl.payOrder(amount, transactionContent.getText(),
							cardNumber.getText(), holderName.getText(), expirationDate.getText(),
							securityCode.getText());

					if (transaction != null && transaction.getErrorCode().equals("00")) {
						RentBikeController rent = new RentBikeController();
						rent.processRentBikeTransaction(transaction, Session.getSession().getStation().getStationId());
						bike.setInUse(false);
						Session.getSession().setBike(null);
						Session.getSession().setPaymentTransaction(null);
						Session.getSession().setStation(null);
						BaseScreenHandler resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
								"Chúc mừng bạn đã trả xe thành công !!!");
						resultScreen.show();
					} else {
						PopupScreen.error("Giao dịch thất bại");
					}
				} catch (Exception exp) {
					// TODO Auto-generated catch block
					try {
						if (exp.getMessage() != null)
							PopupScreen.error(exp.getMessage());
						else
							PopupScreen.error("Giao dịch thất bại");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					exp.printStackTrace();
				}

			}
		});
		backButton.setOnMouseClicked(e -> {
			try {
				BaseScreenHandler detailBike = new BikeDetailHandler(this.stage, Configs.BIKE_DETAIL_SCREEN_PATH);
				detailBike.setPreviousScreen(this);
				detailBike.setHomeScreenHandler(homeScreenHandler);
				detailBike.setScreenTitle("Detail Bike");
				detailBike.show();
			} catch (Exception exp) {
				System.out.println(exp.getStackTrace());
			}
		});
	}

	public void isChooseCreaditCard(ActionEvent e) {
		if (paymentRadioButton.isSelected()) {
			this.areaPaymentVBox.setVisible(true);
			this.confirmPaymentButton.setDisable(false);
		} else {
			this.areaPaymentVBox.setVisible(false);
			this.confirmPaymentButton.setDisable(true);
		}
	}

}
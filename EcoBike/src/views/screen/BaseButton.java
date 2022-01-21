package views.screen;

import java.io.IOException;

import controller.BikeController;
import entity.session.Session;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.home.HomeScreenHandler;
import views.screen.home.UserRentingBikeHandler;
import views.screen.login.LoginScreenHandler;
import views.screen.return_bike.BikeRentingDetailHandler;

public class BaseButton {
	/**
	 * 3 nút cơ bản trên thanh tiếu đề home button, rentingDetail, logout
	 */
	public static void baseButton(Stage stage, Button homeButton, Button btnBikeRentingDetail, Button btnLogout) {
		homeButton.setOnMouseClicked(event -> {
			HomeScreenHandler homeScreen;
			try {
				homeScreen = new HomeScreenHandler(stage, Configs.HOME_SCREEN_PATH);
				homeScreen.setBController(new BikeController());
				homeScreen.show();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		if (Session.getSession().getUser() != null && Session.getSession().getUser().getActive() == true) {
			btnBikeRentingDetail.setDisable(false);
			btnBikeRentingDetail.setText("Đang thuê xe");
			btnBikeRentingDetail.setOnMouseClicked(event -> {
				try {
					BikeRentingDetailHandler bikeRentingDetail = new BikeRentingDetailHandler(stage,
							Configs.BIKE_RENTING_DETAIL_SCREEN_PATH);
					bikeRentingDetail.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		} else if (Session.getSession().getUser().getRole().equals(Configs.USER_ROLE) && Session.getSession().getUser().getActive() == false) {
			btnBikeRentingDetail.setText("Chưa thuê xe");
			btnBikeRentingDetail.setDisable(true);
		};

		if (Session.getSession().getUser() != null && Session.getSession().getUser().getRole().equals(Configs.ADMIN_ROLE)) {
			btnBikeRentingDetail.setDisable(false);
			btnBikeRentingDetail.setText("Thông tin thuê xe");
			btnBikeRentingDetail.setOnMouseClicked(event -> {
				try {
					UserRentingBikeHandler userRentingBikeHandler = new UserRentingBikeHandler(stage,
							Configs.USER_RENTING_BIKE_PATH);
					userRentingBikeHandler.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		} ;

		btnLogout.setOnMouseClicked(event -> {
			try {
				LoginScreenHandler loginScreen = new LoginScreenHandler(stage, Configs.LOGIN_PATH);
				Session.getSession().emptySession();
				loginScreen.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}
}

package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import controller.RentBikeController;
import dao.UserRentingDAO;
import daoimpl.UserRentingDAOImpl;
import entity.Station;
import entity.UserRentingBikeObject;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.return_bike.AStationReturnBike;
import views.screen.return_bike.BikeRentingDetailHandler;

public class UserRentingBikeHandler extends BaseScreenHandler implements Initializable {
	
	public static Logger LOGGER = Utils.getLogger(BikeDetailHandler.class.getName());
	
	@FXML
	private VBox listStationVBox;

	@FXML
	private Button btnBack;

	public UserRentingBikeHandler(Stage stage, String screenPath) throws IOException {
		// TODO Auto-generated constructor stub
		super(stage, screenPath);
		btnBack.setOnMouseClicked(event -> {
			HomeScreenHandler homeHandler;
			try {
				homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
				homeHandler.setScreenTitle("Home Screen");
				homeHandler.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.listStationVBox.getChildren().clear();
		try {
			RentBikeController rentBikeController = new RentBikeController();
			List<UserRentingBikeObject> userRentingBikeObjects = rentBikeController.getAllInfoBikeRentingByAdmin();
			System.out.println(userRentingBikeObjects);
			for (int i = 0; i < userRentingBikeObjects.size(); i++) {
				UserRentingBikeObject userRentingBikeObject = (UserRentingBikeObject) userRentingBikeObjects.get(i);
				AUserRentingBike tmp;
				tmp = new AUserRentingBike(Configs.A_USER_RENITNG_BIKE_PATH, userRentingBikeObject, this);
				this.listStationVBox.getChildren().add(tmp.getContent());
				VBox.setMargin(tmp.getContent(), new Insets (0,0,10,0));
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

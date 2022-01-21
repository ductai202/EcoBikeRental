package views.screen.return_bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import controller.HomeController;
import dao.StationDAO;
import daoimpl.StationDAOImpl;
import entity.Station;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.home.HomeScreenHandler;

public class ReturnBikeHandler extends BaseScreenHandler implements Initializable {

	public static Logger LOGGER = Utils.getLogger(BikeDetailHandler.class.getName());

	@FXML
	private VBox listStationVBox;

	@FXML
	private Button btnBack;

	public ReturnBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub

		btnBack.setOnMouseClicked(event -> {
			try {
				BikeRentingDetailHandler bikeRentingDetail = new BikeRentingDetailHandler(this.getStage(),
						Configs.BIKE_RENTING_DETAIL_SCREEN_PATH);
				bikeRentingDetail.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public HomeController getBController() {
		return (HomeController) super.getBController();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.listStationVBox.getChildren().clear();
		setBController(new HomeController());
		try {
			List stations = getBController().getAllStation();
			for (int i = 0; i < stations.size(); i++) {
				Station station = (Station) stations.get(i);
				LOGGER.info(station.toString());
				AStationReturnBike tmp;
				tmp = new AStationReturnBike(Configs.A_STATION_RETURN_BIKE_PATH, station, this);
				this.listStationVBox.getChildren().add(tmp.getContent());
				VBox.setMargin(tmp.getContent(), new Insets (0,0,10,0));
			}

		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

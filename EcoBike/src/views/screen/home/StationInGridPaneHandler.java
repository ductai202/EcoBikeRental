package views.screen.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.BikeController;
import controller.StationController;
import entity.Station;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import utils.Configs;
import utils.Utils;
import views.screen.FXMLScreenHandler;
import views.screen.list_bike.ListBikeHandler;
import views.screen.update_station.EditStationScreenHandler;

public class StationInGridPaneHandler extends FXMLScreenHandler implements Initializable{

	@FXML
	protected Label stationNameLabel;

	@FXML
	protected Button editButton;

	@FXML
	protected Label stationAddressLabel;

	@FXML
	protected Label stationAvailLabel;

	@FXML
	private HBox optionHBox;
	@FXML
	protected Button stationButton;

	private static Logger LOGGER = Utils.getLogger(StationInGridPaneHandler.class.getName());
	private Station station;
	private HomeScreenHandler home;

	/**
	 * @param screenPath
	 * @param station
	 * @param home
	 * @throws SQLException
	 * @throws IOException
	 */
	public StationInGridPaneHandler(String screenPath, Station station, HomeScreenHandler home)
			throws SQLException, IOException {
		super(screenPath);
		this.station = station;
		this.home = home;
		stationButton.setOnMouseClicked(event -> {
			Session session = Session.getSession();

			session.setStation(station);
			ListBikeHandler listBikeHandler;
			try {
				LOGGER.info("User clicked to view detail bikes");
				listBikeHandler = new ListBikeHandler(this.home.getStage(), Configs.LIST_BIKE_SCREEN_PATH);
				listBikeHandler.setHomeScreenHandler(home);
				listBikeHandler.setBController(new BikeController());
				listBikeHandler.requestToListBike(home);
			} catch (IOException | SQLException e1) {
				LOGGER.info(e1.getMessage());
			}
		});

		editButton.setOnMouseClicked(event -> {
			requestToEditStation();
		});

		setStationInfo();
	}

	public void requestToEditStation() {
		Session session = Session.getSession();

		session.setStation(station);
		EditStationScreenHandler editStationScreenHandler;
		try {
			LOGGER.info("Admin clicked to edit station button");
			editStationScreenHandler = new EditStationScreenHandler(this.home.getStage(), Configs.EDIT_STATION_PATH);
			editStationScreenHandler.setHomeScreenHandler(home);
			editStationScreenHandler.setBController(new StationController());
			editStationScreenHandler.requestToEditStation(home);
		} catch (IOException | SQLException e1) {
			LOGGER.info(e1.getMessage());
		}
	}

	public Station getStation() {
		return station;
	}

	private void setStationInfo() throws SQLException {
		// set the cover image of media
//        File file = new File(media.getImageURL());
//        Image image = new Image(file.toURI().toString());
//        mediaImage.setFitHeight(160);
//        mediaImage.setFitWidth(152);
//        mediaImage.setImage(image);

		stationNameLabel.setText(station.getName());
		stationAddressLabel.setText(station.getAddress());
		stationAvailLabel.setText(String.valueOf(station.getTotalBike()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Session.getSession().getUser() != null &&  Session.getSession().getUser().getRole().equals(Configs.ADMIN_ROLE)) {
			this.editButton.setVisible(true);
		}
		else {
			this.optionHBox.getChildren().remove(1);
		}
	}

}

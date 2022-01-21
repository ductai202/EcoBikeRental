package views.screen.return_bike;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import common.exception.MediaNotAvailableException;
import common.exception.UnrecognizedException;
import controller.BikeController;
import entity.Station;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.list_bike.ListBikeHandler;
import views.screen.popup.PopupScreen;

public class AStationReturnBike extends FXMLScreenHandler {

	@FXML
	private Label infoStation;

	@FXML
	private HBox areaInfoStationHBox;
	private static Logger LOGGER = Utils.getLogger(AStationReturnBike.class.getName());
	private Station station;
	private ReturnBikeHandler returnBikeHandler;

	/**
	 * @param screenPath
	 * @param station
	 * @param home
	 * @throws SQLException
	 * @throws IOException
	 */
	public AStationReturnBike(String screenPath, Station station, ReturnBikeHandler returnBikeHandler)
			throws SQLException, IOException {
		super(screenPath);
		this.station = station;
		this.returnBikeHandler = returnBikeHandler;
		areaInfoStationHBox.setOnMouseClicked(event -> {
			Session session = Session.getSession();

			session.setStation(station);
			if(this.station.getTotalBike() == this.station.getTotalParking()) {
				try {
					PopupScreen.error("Bãi xe đã đầy, xin vui lòng chọn bãi khác để trả" );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				
			RentBikeTransactionInfoHandler rentBikeTransactionInfoHandler;
			try {
				rentBikeTransactionInfoHandler = new RentBikeTransactionInfoHandler(this.returnBikeHandler.getStage(),
						Configs.RENT_BIKE_TRANSACTION_INFO_SCREEN_PATH);
				rentBikeTransactionInfoHandler.show();
			} catch (IOException e1) {
				LOGGER.info(e1.getMessage());
			}
			}
		});

		this.infoStation.setText(station.toString());
	}

	public Station getStation() {
		return station;
	}

}

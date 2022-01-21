package views.screen.list_bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.BikeController;
import controller.StationController;
import entity.Bike;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.update_bike.UpdateBikeScreenHandler;
import views.screen.update_station.EditStationScreenHandler;

public class BikeInGridPaneHandler extends FXMLScreenHandler implements Initializable{

	//		assets/images/cd/cd6.jpg
	
	private static Logger LOGGER = Utils.getLogger(BikeInGridPaneHandler.class.getName());

	@FXML
	private Label bikeCodeLabel;

	@FXML
	private Label typeBikeLabel;

	@FXML
	private Label inUseLabel;

	@FXML
	private Button viewBikeButton;

	@FXML
	private Button editBikeButton;

	@FXML
	private Label bikeNameLabel;

	private Bike bike;
	
	@FXML
	private ImageView bikeImageView;

	@FXML
	private HBox optionHBox;
	private ListBikeHandler listBikes;

	/**
	 * @param screenPath
	 * @param station
	 * @param home
	 * @throws SQLException
	 * @throws IOException
	 */
	public BikeInGridPaneHandler(String screenPath, Bike bike, ListBikeHandler listBikes)
			throws SQLException, IOException {
		super(screenPath);
		this.bike = bike;
		this.listBikes = listBikes;
		viewBikeButton.setOnMouseClicked(event -> {
			Session session = Session.getSession();

			session.setBike(bike);
			BaseScreenHandler bikeDetailHandler;
			try {
				LOGGER.info("User clicked to view detail bike");
				bikeDetailHandler = new BikeDetailHandler(this.listBikes.getStage(), Configs.BIKE_DETAIL_SCREEN_PATH);
				bikeDetailHandler.setHomeScreenHandler(this.listBikes.getHomeHandler());
				bikeDetailHandler.setBController(new BikeController());
				bikeDetailHandler.setScreenTitle("Detail Bike");
				bikeDetailHandler.show();
			} catch (IOException e1) {
				LOGGER.info(e1.getMessage());
			}
		});
		
		editBikeButton.setOnMouseClicked(event -> {
			requestToUpdateBike();
		});
		setBikeInfo();
	}
	
	public void requestToUpdateBike() {
		Session session = Session.getSession();

		session.setBike(this.bike);
		UpdateBikeScreenHandler updateBikeScreenHandler;
		try {
			LOGGER.info("Admin clicked to update bike button");
			updateBikeScreenHandler = new UpdateBikeScreenHandler(this.listBikes.getStage(), Configs.UPDATE_BIKE_PATH);
			updateBikeScreenHandler.setHomeScreenHandler(this.listBikes.getHomeHandler());
			updateBikeScreenHandler.setBController(new BikeController());
			updateBikeScreenHandler.setScreenTitle("Update Bike");
			updateBikeScreenHandler.show();
		} catch (IOException e1) {
			LOGGER.info(e1.getMessage());
		}
	}
	
	public Bike getBike() {
		return bike;
	}

	private void setBikeInfo() throws SQLException {
		this.bikeImageView.setImage(bike.getImageBike());

		this.bikeCodeLabel.setText("Mã xe: " + bike.getBikeCode());
		this.bikeNameLabel.setText(bike.getName());
		this.inUseLabel.setText(this.bike.isInUse() ? "Đang được thuê" : "Sẵn có");
		this.typeBikeLabel.setText("Loại : " + bike.getTypeBike());
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Session.getSession().getUser() != null &&  Session.getSession().getUser().getRole().equals(Configs.ADMIN_ROLE)) {
			this.editBikeButton.setVisible(true);
		}
		else {
			this.optionHBox.getChildren().remove(1);
		}
	}

}
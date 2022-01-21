package views.screen.add_bike;

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
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.FXMLScreenHandler;
import views.screen.add_bike.AddBikeScreenHandler;
import views.screen.bike_detail.BikeDetailHandler;
import views.screen.list_bike.ListBikeHandler;
import views.screen.update_bike.UpdateBikeScreenHandler;

public class AddBikeHandler extends FXMLScreenHandler implements Initializable{
	

	private static Logger LOGGER = Utils.getLogger(AddBikeHandler.class.getName());

	@FXML
	private Button add;

	/**
	 * @param screenPath
	 * @param station
	 * @param home
	 * @throws SQLException
	 * @throws IOException
	 */
	private ListBikeHandler listBikes;

	public AddBikeHandler(String screenPath, ListBikeHandler listBikes) throws IOException {
		super(screenPath);
		// TODO Auto-generated constructor stub
		this.listBikes = listBikes;
		add.setOnMouseClicked(event -> {
			clickAddButton();
		});
	}
	
	public void clickAddButton() {
		BaseScreenHandler addBikeScreenHandler;
		try {
			LOGGER.info("Admin clicked to add bike");
			addBikeScreenHandler = new AddBikeScreenHandler(this.listBikes.getStage(), Configs.ADD_BIKE);
			addBikeScreenHandler.setScreenTitle("Add Bike");
			addBikeScreenHandler.show();
		} catch (IOException e1) {
			LOGGER.info(e1.getMessage());
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}


}
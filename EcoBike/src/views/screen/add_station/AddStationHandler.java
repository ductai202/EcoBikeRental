package views.screen.add_station;

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
import views.screen.home.HomeScreenHandler;
import views.screen.list_bike.ListBikeHandler;
import views.screen.update_bike.UpdateBikeScreenHandler;

public class AddStationHandler extends FXMLScreenHandler implements Initializable{
    private HomeScreenHandler home;

    private static Logger LOGGER = Utils.getLogger(AddStationHandler.class.getName());

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

    public AddStationHandler(String screenPath, HomeScreenHandler home) throws IOException {
        super(screenPath);
        this.home = home;
        add.setOnMouseClicked(event -> {
            requestToAddStation();
        });
    }
    
    public void requestToAddStation() {
    	BaseScreenHandler addStationScreenHandler;
        try {
            LOGGER.info("User clicked to add station");
            addStationScreenHandler = new AddStationScreenHandler(this.home.getStage(), Configs.ADD_STATION_PATH);
            addStationScreenHandler.setHomeScreenHandler(this.home);
            addStationScreenHandler.setBController(new StationController());
            addStationScreenHandler.setScreenTitle("Add Station");
            addStationScreenHandler.show();
        } catch (IOException e1) {
            LOGGER.info(e1.getMessage());
        }
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }


}
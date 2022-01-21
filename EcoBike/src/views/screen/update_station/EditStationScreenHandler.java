package views.screen.update_station;

import controller.LoginController;
import controller.StationController;
import entity.Station;
import entity.User;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseButton;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class EditStationScreenHandler extends BaseScreenHandler implements Initializable {
    private Station station;
    public static Logger LOGGER = Utils.getLogger(EditStationScreenHandler.class.getName());

    @FXML
    private TextField stationName;

    @FXML
    private TextField stationAddress;

    @FXML
    private TextField totalBike;

    @FXML
    private TextField totalParking;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Label usernameLabel;

    public EditStationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        this.station = Session.getSession().getStation();
        setStationInfo();

        confirmBtn.setOnMouseClicked(event -> {
            updateStation();
        });

        cancelBtn.setOnMouseClicked(event -> {
            homeScreenHandler.show();
        });

    }

    public StationController getBController() {
        return (StationController) super.getBController();
    }

    private void updateStation() {
        HashMap<String,String> updateInfo = new HashMap<>();
        updateInfo.put("stationName", stationName.getText());
        updateInfo.put("stationAddress", stationAddress.getText());
        updateInfo.put("totalParking", totalParking.getText());
        if (getBController().validateUpdateInfo(updateInfo)) {
            try {
                Station updateStation = new Station(station.getStationId(),stationName.getText(),stationAddress.getText(), Integer.parseInt(totalParking.getText()), station.getTotalBike());
                getBController().updateStation(updateStation);
                homeScreenHandler.updateStationInGrid();
                homeScreenHandler.show();
            } catch (SQLException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            try {
                PopupScreen.error("Lỗi trường dữ liệu !");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setStationInfo() {
        this.stationName.setText(station.getName());
        this.stationAddress.setText(station.getAddress());
        this.totalBike.setText(Integer.toString(station.getTotalBike()));
        this.totalParking.setText(Integer.toString(station.getTotalParking()));
    }

    public void requestToEditStation(BaseScreenHandler prevScreen) throws SQLException {
        setPreviousScreen(prevScreen);
        setScreenTitle("Edit Station Screen");
        show();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setBController(new StationController());
        this.usernameLabel.setText(Session.getSession().getUser().getUsername());
    }

}
package views.screen.add_station;

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
import views.screen.result.ResultScreenHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class AddStationScreenHandler extends BaseScreenHandler implements Initializable {
    private Station station;
    public static Logger LOGGER = Utils.getLogger(AddStationScreenHandler.class.getName());

    @FXML
    private TextField stationName;

    @FXML
    private TextField stationAddress;

    @FXML
    private TextField totalParking;

    @FXML
    private Button confirmBtn;

    @FXML
    private Button cancelBtn;
    
    @FXML
    private Label usernameLabel;

    public AddStationScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);

        confirmBtn.setOnMouseClicked(event -> {
            insertStation();
        });

        cancelBtn.setOnMouseClicked(event -> {
            homeScreenHandler.show();
        });

    }

    public StationController getBController() {
        return (StationController) super.getBController();
    }

    private Station insertStation() {
        HashMap<String,String> insertInfo = new HashMap<>();
        insertInfo.put("stationName", stationName.getText());
        insertInfo.put("stationAddress", stationAddress.getText());
        insertInfo.put("totalParking", totalParking.getText());
        if (getBController().validateInsertInfo(insertInfo)) {
            try {
                Station insertStation = new Station(0,stationName.getText(),stationAddress.getText(), Integer.parseInt(totalParking.getText()),0);
                getBController().insertStation(insertStation);

                BaseScreenHandler resultScreen;
                try {
                    resultScreen = new ResultScreenHandler(this.stage,
                            Configs.RESULT_SCREEN_PATH, "Thêm bãi xe thành công");
                    resultScreen.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        return null;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	usernameLabel.setText(Session.getSession().getUser().getUsername());
    }

}
package views.screen.return_bike;

import java.io.IOException;

import entity.session.Session;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class ReturnBikeSuccessHandler extends BaseScreenHandler {

	@FXML
	private Button backButton;

	public ReturnBikeSuccessHandler(Stage stage, String screenPath) throws IOException {
		// TODO Auto-generated constructor stub
		super(stage, screenPath);
		backButton.setOnMouseClicked(e -> {
			HomeScreenHandler homeHandler;
			try {
				homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
				homeHandler.setScreenTitle("Home Screen");
				homeHandler.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
	}

}

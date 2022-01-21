package views.screen.result;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

public class ResultScreenHandler extends BaseScreenHandler{
	@FXML
	private Button backButton;
	@FXML
	private Label message;
	public ResultScreenHandler(Stage stage, String screenPath, String result) throws IOException {
		// TODO Auto-generated constructor stub
		super(stage, screenPath);
		this.message.setText(result);
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

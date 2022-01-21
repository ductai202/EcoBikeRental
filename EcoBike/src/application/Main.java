package application;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.login.LoginScreenHandler;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
					LoginScreenHandler homeHandler = new LoginScreenHandler(primaryStage, Configs.LOGIN_PATH);
					homeHandler.setScreenTitle("Login Screen");
					homeHandler.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

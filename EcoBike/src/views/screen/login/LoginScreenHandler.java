package views.screen.login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.LoginController;
import entity.User;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.popup.PopupScreen;

public class LoginScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger LOGGER = Utils.getLogger(LoginScreenHandler.class.getName());

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Button loginButton;


	public LoginScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

		loginButton.setOnMouseClicked(event -> {
			// login business
			try {
				User user = getBController().login(usernameTextField.getText(), passwordTextField.getText());
				if (user != null) {
					// luu session	
					Session.getSession().setUser(user);
					HomeScreenHandler homeScreen = new HomeScreenHandler(this.getStage(), Configs.HOME_SCREEN_PATH);
					homeScreen.show();
					
				} else {
					// hien thi thong bao that bai
					PopupScreen.error("Xin vui lòng đăng nhập lại!");;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
	}

	public LoginController getBController() {
		return (LoginController) super.getBController();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new LoginController());

	}

}

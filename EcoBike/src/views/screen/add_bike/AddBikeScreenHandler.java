package views.screen.add_bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.BikeController;
import entity.Bike;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseScreenHandler;
import views.screen.list_bike.ListBikeHandler;
import views.screen.popup.PopupScreen;
import views.screen.result.ResultScreenHandler;
import views.screen.return_bike.ReturnBikeSuccessHandler;

public class AddBikeScreenHandler extends BaseScreenHandler implements Initializable {
	public static Logger LOGGER = Utils.getLogger(AddBikeScreenHandler.class.getName());

	@FXML
	private TextField name;

	@FXML
	private TextField weight;

	@FXML
	private TextField bikeCode;

	@FXML
	private TextField dateSX;

	@FXML
	private TextField producer;

	@FXML
	private TextField value;

	@FXML
	private ComboBox<String> type;

	@FXML
	private ComboBox<String> isInUse;

	@FXML
	private ImageView bikeImage;

	@FXML
	private Button confirm;

	@FXML
	private Button backButton;

	public AddBikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		this.type.setValue("Xe đạp thường");
		this.isInUse.setValue("Sẵn có");
		backButton.setOnMouseClicked(event -> {
			cancelToAddBike();
		});

		confirm.setOnMouseClicked(event -> {
			confirmToAddBike();
		});

		// TODO Auto-generated constructor stub
	}
	
	public void cancelToAddBike() {
		BaseScreenHandler listBikeHandler;
		try {
			LOGGER.info("Admin clicked to back");
			listBikeHandler = new ListBikeHandler(this.stage, Configs.LIST_BIKE_SCREEN_PATH);
			listBikeHandler.setHomeScreenHandler(this.homeScreenHandler);
			listBikeHandler.setBController(new BikeController());
			listBikeHandler.show();
		} catch (IOException e1) {
			LOGGER.info(e1.getMessage());
		}
	}
	
	public void confirmToAddBike() {
		if (insertBike()) {
			BaseScreenHandler resultScreen;
			try {
				resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
						"Thêm xe thành công");
				resultScreen.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new BikeController());
		this.type.getItems().addAll(Configs.TYPES);
		this.isInUse.getItems().addAll(Configs.USE);
	}

	public BikeController getBController() {
		return (BikeController) super.getBController();
	}

	private boolean insertBike() {
		HashMap<String, String> addInfo = new HashMap<>();
		addInfo.put("name", name.getText());
		addInfo.put("weight", weight.getText());
		addInfo.put("value", value.getText());
		addInfo.put("bikeCode", bikeCode.getText());
		addInfo.put("dateSX", dateSX.getText());
		addInfo.put("producer", producer.getText());
		boolean inUse;
		if (isInUse.getValue() == "Đang được thuê") {
			addInfo.put("isInUse", "1");
			inUse = true;
		} else {
			addInfo.put("isInUse", "0");
			inUse = false;
		}

		String type_update = (new Bike()).mappingTypeBikeToType(type.getValue());
		addInfo.put("type", type_update);
		
		if (getBController().validateInfo(addInfo)) {
			try {
				Bike addBike = new Bike(0, name.getText(), Integer.parseInt(value.getText()),
						Float.parseFloat(weight.getText()), bikeCode.getText(),
						Session.getSession().getStation().getStationId(), inUse, type_update, dateSX.getText(),
						producer.getText());
				getBController().insertBike(addBike);

				try {
					PopupScreen.success("Thêm thành công");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				LOGGER.info(e.getMessage());
				return false;
			}
			return true;
		} else {
			try {
				String er = ": ";
				if (!getBController().validateName(name.getText())) er += "Tên xe,";
				if (!getBController().validateWeight(weight.getText())) er += " Trọng lượng,";
				if (!getBController().validateValue(value.getText())) er += " Giá thành,";
				if (!getBController().validateBikeCode(bikeCode.getText())) er += " Mã xe,";
				if (!getBController().validateDate(dateSX.getText())) er += " Ngày sản xuất,";
				if (!getBController().validateName(producer.getText())) er += " Nhà sản xuất,";
				er = er.substring(0, er.length() - 1);
				PopupScreen.error("Lỗi trường dữ liệu" + er);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}
}

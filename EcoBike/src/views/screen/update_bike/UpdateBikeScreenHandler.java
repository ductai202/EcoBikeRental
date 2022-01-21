package views.screen.update_bike;

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
import javafx.scene.control.Label;
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

public class UpdateBikeScreenHandler extends BaseScreenHandler implements Initializable {

	private Bike bike;
	public static Logger LOGGER = Utils.getLogger(UpdateBikeScreenHandler.class.getName());

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

	public UpdateBikeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		this.bike = Session.getSession().getBike();
		setBikeInfo();

		backButton.setOnMouseClicked(event -> {
				cancelToUpdateBike();
		});

		confirm.setOnMouseClicked(event -> {
			confirmToUpdateBike();
		});

		// TODO Auto-generated constructor stub
	}
	
	public void cancelToUpdateBike() {
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
	
	public void confirmToUpdateBike() {
		if (updateBike()) {
			BaseScreenHandler resultScreen;
			try {
				resultScreen = new ResultScreenHandler(this.stage, Configs.RESULT_SCREEN_PATH,
						"Cập nhật xe thành công");
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

	private boolean updateBike() {
		HashMap<String, String> updateInfo = new HashMap<>();
		updateInfo.put("name", name.getText());
		updateInfo.put("weight", weight.getText());
		updateInfo.put("value", value.getText());
		updateInfo.put("bikeCode", bikeCode.getText());
		updateInfo.put("dateSX", dateSX.getText());
		updateInfo.put("producer", producer.getText());
		boolean inUse;
		if (isInUse.getValue() == "Đang được thuê") {
			updateInfo.put("isInUse", "1");
			inUse = true;
		} else {
			updateInfo.put("isInUse", "0");
			inUse = false;
		}

		String type_update = bike.mappingTypeBikeToType(type.getValue());
		updateInfo.put("type", type_update);

		if (getBController().validateInfo(updateInfo)) {
			try {
				Bike updateBike = new Bike(bike.getBikeId(), name.getText(), Integer.parseInt(value.getText()),
						Float.parseFloat(weight.getText()), bikeCode.getText(), bike.getStationId(), inUse, type_update,
						dateSX.getText(), producer.getText());
				getBController().updateBike(updateBike);
			} catch (SQLException e) {
				LOGGER.info(e.getMessage());
				return false;
			}
			return true;
		} else {
			String er = ": ";
			if (!getBController().validateName(name.getText())) er += "Tên xe,";
			if (!getBController().validateWeight(weight.getText())) er += " Trọng lượng,";
			if (!getBController().validateValue(value.getText())) er += " Giá thành,";
			if (!getBController().validateBikeCode(bikeCode.getText())) er += " Mã xe,";
			if (!getBController().validateDate(dateSX.getText())) er += " Ngày sản xuất,";
			if (!getBController().validateName(producer.getText())) er += " Nhà sản xuất,";
			er = er.substring(0, er.length() - 1);
			try {
				PopupScreen.error("Lỗi trường dữ liệu" + er);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	private void setBikeInfo() {
		this.name.setText(bike.getName());
		this.weight.setText(Float.toString(bike.getWeight()));
		this.value.setText(Integer.toString(bike.getValue()));
		this.bikeCode.setText(bike.getBikeCode());
		this.dateSX.setText(bike.getDateSX());
		this.producer.setText(bike.getProducer());
		this.type.setValue(bike.getTypeBike());
		if (bike.isInUse()) {
			this.isInUse.setValue("Đang được thuê");
		} else {
			this.isInUse.setValue("Sẵn có");
		}
	}
}

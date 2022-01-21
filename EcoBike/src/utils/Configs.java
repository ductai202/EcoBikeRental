package utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author nguyenlm Contains the configs for AIMS Project
 */
public class Configs {

	// api constants
	public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
	public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
	public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset-balance";
	public final static String URL_BIKE = "assets/images/bike.jpeg";
	public final static String URL_TWIN_BIKE = "assets/images/twinbike.jpg";
	public final static String URL_EBIKE = "assets/images/ebike.jpg";
	public final static String USER_ROLE = "user";
	public final static String ADMIN_ROLE = "admin";
	public final static String TYPE_BIKE = "b";
	public final static String TYPE_EBIKE = "eb";
	public final static String TYPE_TWIN_BIKE = "tb";
	// demo data
	public static final String POST_DATA = "{" + " \"secretKey\": \"BUXj/7/gHHI=\" ," + " \"transaction\": {"
			+ " \"command\": \"pay\" ," + " \"cardCode\": \"118609_group1_2020\" ," + " \"owner\": \"Group 1\" ,"
			+ " \"cvvCode\": \"185\" ," + " \"dateExpried\": \"1125\" ," + " \"transactionContent\": \"Pei debt\" ,"
			+ " \"amount\": 50000 " + "}" + "}";
	public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

	// database Configs
	public static final String DB_NAME = "aims";
	public static final String DB_USERNAME = System.getenv("DB_USERNAME");
	public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

	public static String CURRENCY = "VND";
	public static float PERCENT_VAT = 10;

	// static resource
	public static final String IMAGE_PATH = "assets/images";
	public static final String HOME_SCREEN_PATH = "/views/fxml/HomeScreen.fxml";
	public static final String BIKE_RENTING_DETAIL_SCREEN_PATH = "/views/fxml/BikeRentingDetail.fxml";
	public static final String RENT_BIKE_TRANSACTION_INFO_SCREEN_PATH = "/views/fxml/RentBikeTransactionInfo.fxml";
	public static final String RETURN_BIKE_SCREEN_PATH = "/views/fxml/ReturnBikeScreen.fxml";
	public static final String PAYMENT_FORM_SCREEN_SCREEN_PATH = "/views/fxml/PaymentFormScreen.fxml";
	public static final String LIST_BIKE_SCREEN_PATH = "/views/fxml/ListBikeScreen.fxml";
	public static final String STATION_IN_GRID_PANE_SCREEN_PATH = "/views/fxml/StationInGridPane.fxml";
	public static final String BIKE_IN_GRID_PANE_SCREEN_PATH = "/views/fxml/BikeInGridPane.fxml";
	public static final String CONFIRM_RENT_BIKE_SCREEN_PATH = "/views/fxml/ConfirmRentBike.fxml";
	public static final String BIKE_DETAIL_SCREEN_PATH = "/views/fxml/BikeDetailScreen.fxml";
	public static final String RENT_BIKE_SUCCESS_SCREEN_PATH = "/views/fxml/RentBikeSuccess.fxml";
	public static final String RETURN_BIKE_SUCCESS_SCREEN_PATH = "/views/fxml/ReturnBikeSuccess.fxml";
	public static final String POPUP_PATH = "/views/fxml/popup.fxml";
	public static final String LOGIN_PATH = "/views/fxml/Login.fxml";
	public static final String ITEM_PATH = "/views/fxml/item.fxml";
	public static final String A_STATION_RETURN_BIKE_PATH = "/views/fxml/AStationReturnBike.fxml";
	public static final String EDIT_STATION_PATH = "/views/fxml/UpdateStationScreen.fxml";
	public static final String UPDATE_BIKE_PATH = "/views/fxml/UpdateBikeScreen.fxml";
	public static final String ADD_BIKE = "/views/fxml/AddBikeScreen.fxml";
	public static final String ADD_STATION_PATH = "/views/fxml/AddStationScreen.fxml";
	public static final String USER_RENTING_BIKE_PATH = "/views/fxml/UserRentingBikeScreen.fxml";
	public static final String A_USER_RENITNG_BIKE_PATH = "/views/fxml/AUserRentingBike.fxml";


	public static final String ADD = "/views/fxml/Add.fxml";
	public static final String RESULT_SCREEN_PATH = "/views/fxml/ResultScreen.fxml";
	public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);
	public static String[] TYPES = {"Xe đạp thường", "Xe đạp điện", "Xe đạp đôi"};
	public static String[] USE = {"Đang được thuê", "Sẵn có"};
}

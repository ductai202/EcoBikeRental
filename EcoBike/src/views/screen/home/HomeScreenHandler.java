package views.screen.home;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.HomeController;
import entity.Station;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseButton;
import views.screen.BaseScreenHandler;
import views.screen.add_station.AddStationHandler;


public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

	public static Logger LOGGER = Utils.getLogger(HomeScreenHandler.class.getName());

	@FXML
	private GridPane grid;

	@FXML
	private Button btnBikeRentingDetail;

	@FXML
	private Button btnLogOut;

	@FXML
	private Button homeButton;

	@FXML
	private ScrollPane scroll;
	
	@FXML
	private Label usernameLabel;

	private List homeItems;

	private final static int NUMBER_COLUMN_IN_GRID_PANE = 2;

	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

		BaseButton.baseButton(this.getStage(), homeButton, btnBikeRentingDetail, btnLogOut);

	}

	public HomeController getBController() {
		return (HomeController) super.getBController();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setBController(new HomeController());
		this.usernameLabel.setText(Session.getSession().getUser().getUsername());
		updateStationInGrid();
	}

	public void updateStationInGrid() {
		grid.getChildren().clear();
		try {
			List stations = getBController().getAllStation();

			int currentRows = 1;
			int currentCols = 0;
			for (int i = 0; i < stations.size() ; i++) {
				Station station = (Station) stations.get(i);
				LOGGER.info(station.toString());
				StationInGridPaneHandler tmp = new StationInGridPaneHandler(Configs.STATION_IN_GRID_PANE_SCREEN_PATH,
						station, this);
				if (currentCols == this.NUMBER_COLUMN_IN_GRID_PANE) {
					currentRows++;
					currentCols = 0;
				}
				this.grid.add(tmp.getContent(), currentCols++, currentRows);
				setAttrGrid(grid);
				GridPane.setMargin(tmp.getContent(), new Insets(10));
			}


			if (currentCols == this.NUMBER_COLUMN_IN_GRID_PANE) {
				currentRows++;
				currentCols = 0;
			}

			if (Session.getSession().getUser().getRole().equals(Configs.ADMIN_ROLE)) {
				try {
					AddStationHandler addStationHandler = new AddStationHandler(Configs.ADD, this);
					this.grid.add(addStationHandler.getContent(), currentCols++, currentRows);
					setAttrGrid(grid);
					GridPane.setMargin(addStationHandler.getContent(), new Insets(10));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException | IOException e) {
			LOGGER.info("Errors occured: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private void setAttrGrid(GridPane gridPane) {
		gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
		gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
		gridPane.setMaxWidth(Region.USE_PREF_SIZE);
		gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
		gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
		gridPane.setMaxHeight(Region.USE_PREF_SIZE);
	}
	
}

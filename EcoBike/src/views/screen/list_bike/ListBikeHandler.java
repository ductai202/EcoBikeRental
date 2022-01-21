package views.screen.list_bike;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import controller.BikeController;
import entity.Bike;
import entity.session.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import utils.Configs;
import utils.Utils;
import views.screen.BaseButton;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;
import views.screen.add_bike.AddBikeHandler;

public class ListBikeHandler extends BaseScreenHandler implements Initializable {

	private static Logger LOGGER = Utils.getLogger(ListBikeHandler.class.getName());

	@FXML
	private ScrollPane scroll;
	@FXML
	private GridPane grid;
	@FXML
	private SplitMenuButton splitMenuBtnSearch;

	@FXML
	private Label nameStationLabel;

	@FXML
	private Label resultFilterLabel;
	@FXML
	private Button btnLogOut;

	@FXML
	private Button btnBikeRentingDetail;

	@FXML
	private Button homeButton;
	
	@FXML
	private Label usernameLabel;

	@FXML
	private ImageView logoImage;
	private List bikes;

	private final static int NUMBER_COLUMN_IN_GRID_PANE = 3;
	private final static String ALL = "Tất cả";
	private final static String IN_USE = "Sử dụng";
	private final static String AVAIL = "Sẵn có";

	public ListBikeHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);

		BaseButton.baseButton(stage, homeButton, btnBikeRentingDetail, btnLogOut);
		// fix relative image path caused by fxml
		File file = new File("assets/images/Logo.png");
		Image im = new Image(file.toURI().toString());

		// on mouse clicked, we back to home
		logoImage.setOnMouseClicked(e -> {
			homeScreenHandler.show();
		});

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setBController(new BikeController());
		this.usernameLabel.setText(Session.getSession().getUser().getUsername());
		splitMenuBtnSearch.getItems().clear();
		this.nameStationLabel.setText(Session.getSession().getStation().getName());
		List items = getBController().getBikesByStationId(Session.getSession().getStation().getStationId());
		this.bikes = new ArrayList<>();
		for (Object object : items) {
			Bike bike = (Bike) object;
			BikeInGridPaneHandler tmp;
			try {
				tmp = new BikeInGridPaneHandler(Configs.BIKE_IN_GRID_PANE_SCREEN_PATH, bike, this);
				this.bikes.add(tmp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		addBikesToGridPane(bikes);
		addMenuItem(0, ALL, splitMenuBtnSearch);
		addMenuItem(1, AVAIL, splitMenuBtnSearch);
		addMenuItem(2, IN_USE, splitMenuBtnSearch);
	}

	public BikeController getBController() {
		return (BikeController) super.getBController();
	}

	public void requestToListBike(BaseScreenHandler prevScreen) throws SQLException {
		setPreviousScreen(prevScreen);
		setScreenTitle("List Bike Screen");
		show();
	}

	public HomeScreenHandler getHomeHandler() {
		return super.homeScreenHandler;
	}

	private void addMenuItem(int position, String text, MenuButton menuButton) {
		MenuItem menuItem = new MenuItem();
		Label label = new Label();
		label.prefWidthProperty().bind(menuButton.widthProperty().subtract(31));
		label.setText(text);
		label.setTextAlignment(TextAlignment.RIGHT);
		menuItem.setGraphic(label);
		menuItem.setOnAction(e -> {
			// filter only media with the choosen category
			List filteredItems = new ArrayList<>();
			if (text.equals(ALL)) {
				filteredItems = (ArrayList) ((ArrayList) bikes).clone();
				;
			} else if (text.equals(IN_USE)) {
				for (int i = 0; i < bikes.size(); i++) {
					BikeInGridPaneHandler tmp = (BikeInGridPaneHandler) bikes.get(i);
					if (tmp.getBike().isInUse()) {
						filteredItems.add(tmp);
					}
				}

			} else {
				for (int i = 0; i < bikes.size(); i++) {
					BikeInGridPaneHandler tmp = (BikeInGridPaneHandler) bikes.get(i);
					if (!tmp.getBike().isInUse()) {
						filteredItems.add(tmp);

					}
				}
			}

			addBikesToGridPane(filteredItems);
		});
		menuButton.getItems().add(position, menuItem);
	}

	public void addBikesToGridPane(List list) {
		this.grid.getChildren().clear();
		this.resultFilterLabel.setText("(" + list.size() + " xe tìm thấy)");
		int currentRows = 1;
		int currentCols = 0;
		for (int i = 0; i < list.size(); i++) {
			BikeInGridPaneHandler tmp = (BikeInGridPaneHandler) list.get(i);
			if (currentCols == this.NUMBER_COLUMN_IN_GRID_PANE) {
				currentRows++;
				currentCols = 0;
			}
			this.grid.add(tmp.getContent(), currentCols++, currentRows);
			setAttrGrid(grid);
			GridPane.setMargin(tmp.getContent(), new Insets(10));
		}
		if (Session.getSession().getStation().getTotalParking() > list.size() && 
				Session.getSession().getUser().getRole().equals(Configs.ADMIN_ROLE)) {
			
			if (currentCols == this.NUMBER_COLUMN_IN_GRID_PANE) {
				currentRows++;
				currentCols = 0;
			}
		
			try {
				AddBikeHandler add = new AddBikeHandler(Configs.ADD, this);
				this.grid.add(add.getContent(), currentCols++, currentRows);
				setAttrGrid(grid);
				GridPane.setMargin(add.getContent(), new Insets(10));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

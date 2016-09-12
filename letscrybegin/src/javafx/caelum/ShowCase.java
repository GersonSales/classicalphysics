package javafx.caelum;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShowCase extends Application {

	private AnchorPane anchorPane;
	private TextField searchField;

	private TableView<ItensProperty> table;
	private TableColumn<ItensProperty, String> nameColumn;
	private TableColumn<ItensProperty, Double> priceColumn;

	private ObservableList<ItensProperty> products;

	private static Cart cart;
	private static Stage stage;

	public ShowCase() {
		this.products = FXCollections.observableArrayList();
		cart = new Cart();
	}

	// public void add(Product product) {
	// this.products.add(product);
	// }
	//
	// public void addAll(Product... products) {
	// for (Product product : products) {
	// this.products.add(product);
	// }
	// }

	@SuppressWarnings("unchecked")
	private void initComponents() {
		anchorPane = new AnchorPane();
		anchorPane.setPrefSize(800, 600);

		searchField = new TextField();
		searchField.setPromptText("search...");

		table = new TableView<>();
		table.setPrefSize(780, 550);

		nameColumn = new TableColumn<>();
		priceColumn = new TableColumn<>();

		table.getColumns().addAll(nameColumn, priceColumn);

		anchorPane.getChildren().addAll(searchField, table);

		nameColumn.setCellValueFactory(
				new PropertyValueFactory<ItensProperty, String>("produto"));

		priceColumn.setCellValueFactory(
				new PropertyValueFactory<ItensProperty, Double>("preco"));

	}

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initItens();

		Scene scene = new Scene(anchorPane);

		stage.setScene(scene);
		stage.show();

		setStage(stage);
	}

	private void initItens() {
	}

	private static void setStage(Stage stage) {
		ShowCase.stage = stage;
	}

}

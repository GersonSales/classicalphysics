package javafx.caelum;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Login extends Application {

	private AnchorPane anchorPane;

	private TextField loginField;
	private PasswordField passwdField;

	private Button enterButton;
	private Button exitButton;

	private static Stage stage;

	private final int WIDTH = 200;
	private final int HEIGHT = 150;

	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		Scene scene = new Scene(anchorPane);

		stage.setResizable(false);
		stage.setTitle("Title");

		stage.setScene(scene);
		stage.show();

		initLayout();

		Login.setStage(stage);
	}

	private void initComponents() {

		anchorPane = new AnchorPane();
		anchorPane.setPrefSize(WIDTH, HEIGHT);
		anchorPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, white 0%, black 100%);");

		loginField = new TextField();
		loginField.setPromptText("login");

		passwdField = new PasswordField();
		passwdField.setPromptText("password");

		enterButton = new Button("Enter");
		exitButton = new Button("exit");

		Slider slider = new Slider();

		anchorPane.getChildren().addAll(loginField, passwdField, enterButton, exitButton, slider);
	}

	private void initListeners() {
		exitButton.setOnAction(event -> System.exit(0));
		enterButton.setOnAction(event -> login());
	}

	private void login() {
		if (loginField.getText().equals("")) {
			if (passwdField.getText().equals("")) {
				// TODO open shop
			} else {
				JOptionPane.showMessageDialog(null, "Invalid password!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid login!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void initLayout() {
		int yPos = 10;
		for (Node node : anchorPane.getChildren()) {
			if (node instanceof Region) {
				Region regionNode = (Region) node;
				regionNode.setLayoutX((WIDTH - regionNode.getWidth()) / 2);
				regionNode.setLayoutY(yPos);
				yPos += 30;
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public static void setStage(Stage stage) {
		Login.stage = stage;
	}

}

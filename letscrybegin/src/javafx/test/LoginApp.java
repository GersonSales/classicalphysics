package javafx.test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginApp extends Application {

	private static final double WIDTH = 200;
	private static final double HEIGTH = 160;

	private AnchorPane pane;
	private Scene scene;

	private TextField textField;
	private PasswordField passwordField;

	private Button enterButton;
	private Button exitButton;

	public LoginApp() {
		paneSetUp();

		textFieldSetUp();
		passwordFieldSetUp();

		enterButtonSetUp();
		exitButtonSetUp();

		applyStyle();

		mergeElements();

		initListeners();

		sceneSetUp();
	}

	private void initListeners() {
		exitButton.setOnAction(event -> System.exit(0));
	}

	private void applyStyle() {
		pane.setStyle("-fx-background-color: linear-gradient"
				+ "(from 0% 0% to 100% 100%, blue 0%, silver 100%);");

	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(getScene());
		stage.show();
		repositionItems();
	}

	private void mergeElements() {
		getPane().getChildren().addAll(getTextField(), getPasswordField(),
				getEnterButton(), getExitButton());
	}

	private void exitButtonSetUp() {
		setExitButton(new Button("Exit"));
	}

	private void enterButtonSetUp() {
		setEnterButton(new Button("Enter"));
	}

	private void passwordFieldSetUp() {
		setPasswordField(new PasswordField());
		getPasswordField().setPromptText("Password...");
	}

	private void textFieldSetUp() {
		setTextField(new TextField());
		getTextField().setPromptText("Login...");
	}

	private void sceneSetUp() {
		setScene(new Scene(getPane()));

	}

	private void paneSetUp() {
		setPane(new AnchorPane());
		pane.setPrefSize(WIDTH, HEIGTH);
	}

	private AnchorPane getPane() {
		return this.pane;
	}

	private Scene getScene() {
		return scene;
	}

	private void setScene(Scene scene) {
		this.scene = scene;
	}

	private TextField getTextField() {
		return textField;
	}

	private void setTextField(TextField textField) {
		this.textField = textField;
	}

	private PasswordField getPasswordField() {
		return passwordField;
	}

	private void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	private Button getEnterButton() {
		return enterButton;
	}

	private Button getExitButton() {
		return exitButton;
	}

	private void setEnterButton(Button enterButton) {
		this.enterButton = enterButton;
	}

	private void setExitButton(Button exitButton) {
		this.exitButton = exitButton;
	}

	private void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	private void repositionItems() {
		getTextField().setLayoutX(
				(getPane().getWidth() - getTextField().getWidth()) / 2);
		getTextField().setLayoutY(10);

		getPasswordField().setLayoutX(
				(getPane().getWidth() - getPasswordField().getWidth()) / 2);
		getPasswordField().setLayoutY(50);

		getEnterButton().setLayoutX(
				(getPane().getWidth() - getEnterButton().getWidth()) / 2);
		getEnterButton().setLayoutY(90);

		getExitButton().setLayoutX(
				(getPane().getWidth() - getExitButton().getWidth()) / 2);
		getExitButton().setLayoutY(120);

	}

	public static void main(String[] args) {
		launch(args);
	}

}

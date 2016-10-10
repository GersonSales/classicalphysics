package javafx.physics;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Menu {

	private final double X_SCALE = 1.5;
	private final double Y_SCALE = 1.5;
	private final double MAX_FONT_SIZE = 15;
	private final Font FONT = new Font(MAX_FONT_SIZE);

	private Slider slFieldPower;
	private Slider slBallCharge;
	private Slider slBallSpeed;

	private Label lUpperPlateDistance;
	private Label lInferiorPlateDistance;
	private Label lDistanceBetweenPlates;
	private Label lChargeValue;
	private Label lActuatingForce;
	private Label lAverageVolocity;
	private Label lAverageDistance;
	private Label lAverageTime;
	private Label lAverageAceleration;

	private Button bChargeChanger;
	private Button bResetBall;
	private Button bPause;

	private CheckBox cbMouseEnabled;

	public Menu(Pane uiRoot) {
		initComponents(uiRoot);
		initLayout();
	}

	private void initComponents(Pane uiRoot) {
		slFieldPower = new Slider();
		slBallCharge = new Slider();
		slBallSpeed = new Slider();

		lUpperPlateDistance = new Label();
		lUpperPlateDistance.setFont(FONT);

		lInferiorPlateDistance = new Label();
		lInferiorPlateDistance.setFont(FONT);

		lDistanceBetweenPlates = new Label();
		lDistanceBetweenPlates.setFont(FONT);

		lChargeValue = new Label();
		lChargeValue.setFont(FONT);

		lActuatingForce = new Label();
		lActuatingForce.setFont(FONT);

		lAverageVolocity = new Label();
		lAverageVolocity.setFont(FONT);

		lAverageDistance = new Label();
		lAverageDistance.setFont(FONT);

		lAverageTime = new Label();
		lAverageTime.setFont(FONT);

		lAverageAceleration = new Label();
		lAverageAceleration.setFont(FONT);

		bChargeChanger = new Button("Change charge");
		bResetBall = new Button("Reset ball");
		bPause = new Button("Pause");

		cbMouseEnabled = new CheckBox("Enable Mouse");

		uiRoot.getChildren().addAll(slFieldPower, slBallCharge, lUpperPlateDistance, lInferiorPlateDistance,
				bChargeChanger, lDistanceBetweenPlates, lChargeValue, lActuatingForce, lAverageVolocity, slBallSpeed,
				cbMouseEnabled, bResetBall, lAverageDistance, lAverageTime, lAverageAceleration, bPause);

	}

	private void initLayout() {
		initSlidersRange();
		initHandlers();
		slFieldPower.setLayoutX(350);
		slBallSpeed.setLayoutX(350);
		slBallCharge.setLayoutX(350);

		slFieldPower.setLayoutY(410);
		slBallSpeed.setLayoutY(430);
		slBallCharge.setLayoutY(450);

		lUpperPlateDistance.setLayoutX(10);
		lInferiorPlateDistance.setLayoutX(10);
		lDistanceBetweenPlates.setLayoutX(10);
		lAverageDistance.setLayoutX(10);
		lAverageTime.setLayoutX(10);
		lAverageVolocity.setLayoutX(10);
		lAverageAceleration.setLayoutX(10);
		lActuatingForce.setLayoutX(10);

		lUpperPlateDistance.setLayoutY(420);
		lInferiorPlateDistance.setLayoutY(440);
		lDistanceBetweenPlates.setLayoutY(460);
		lAverageTime.setLayoutY(480);
		lAverageDistance.setLayoutY(500);
		lAverageVolocity.setLayoutY(520);
		lAverageAceleration.setLayoutY(540);
		lActuatingForce.setLayoutY(560);

		bChargeChanger.setLayoutX(550);
		bResetBall.setLayoutX(550);
		bPause.setLayoutX(550);

		bChargeChanger.setLayoutY(410);
		bResetBall.setLayoutY(440);
		bPause.setLayoutY(470);

		cbMouseEnabled.setLayoutX(350);
		cbMouseEnabled.setLayoutY(475);
	}

	private boolean mouseIsEnabled = false;
	private boolean vanishIsEnabled = false;
	private boolean gravityIsEnabled = false;
	private boolean isPaused = false;

	private void initHandlers() {
		cbMouseEnabled.setOnAction(event -> {
			mouseIsEnabled = mouseIsEnabled ? false : true;
		});

	}

	private void initSlidersRange() {
		slFieldPower.setMin(0);
		slFieldPower.setMax(40);
		slBallSpeed.setMin(2);
		slBallSpeed.setMax(40);
		slBallCharge.setMin(.1);
		slBallCharge.setMax(100);
	}

	public boolean isMouseEnabled() {
		return mouseIsEnabled;
	}

	public boolean isVanishEnabled() {
		return vanishIsEnabled;
	}

	public boolean isGravityEnabled() {
		return gravityIsEnabled;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public double getSliderValue() {
		return this.slFieldPower.getValue();
	}

	public Double getChargeSliderValue() {
		return this.slBallCharge.getValue();
	}

	public double getBallSpeedValue() {
		return this.slBallSpeed.getValue();
	}

	public void setDistanceBetweenPlates(double distance) {
		lDistanceBetweenPlates.setText("Distance between plates: " + String.format("%.2f", distance) + "mm");

	}

	public void setDistanceUpperPlate(double distance) {

		lUpperPlateDistance.setText("Distance to upper plate: " + String.format("%.2f", distance) + "mm");
	}

	public void setDistanceInferiorPlate(double distance) {
		lInferiorPlateDistance.setText("Distance to inferior plate: " + String.format("%.2f", distance) + "mm");

	}

	public void setBallVelocity(double velocity, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageVolocity.setText("Velocity: " + String.format("%.2f", velocity) + "* 10" + expToStr + "mm/s");

	}

	public void setDistanceValue(double distance, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageDistance.setText("Average distance: " + String.format("%.2f", distance) + " * 10" + expToStr + " cm/s");

	}

	public void setButtonChargeEvent(EventHandler<ActionEvent> event) {
		bChargeChanger.setOnAction(event);
	}

	public void setResetBallEvent(EventHandler<ActionEvent> event) {
		bResetBall.setOnAction(event);

	}

	public void setButtonPauseEvent(EventHandler<ActionEvent> event) {
		bPause.setOnAction(event);
	}

	public void setAverageTime(double time, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageTime.setText("Average time: " + String.format("%.2f", time) + " * 10" + expToStr + " seconds");

	}

	public void setAverageAceleration(double aceleration, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageAceleration
				.setText("Average aceleration: " + String.format("%.2f", aceleration) + " * 10" + expToStr + " cm/s");
	}

	public void setActuatingForce(Double force, int exp) {
		String expToStr = Util.superscript(exp + "");
		lActuatingForce
				.setText("Actuating Force: " + String.format("%.2f", force) + " * 10" + expToStr + " N");
		
	}

}

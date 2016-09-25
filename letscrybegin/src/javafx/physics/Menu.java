package javafx.physics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Menu {

	private final double X_SCALE = 1.5;
	private final double Y_SCALE = 1.5;

	private Slider slFieldPower;
	private Slider slBallMass;
	private Slider slBallSpeed;

	private Label lUpperPlateDistance;
	private Label lInferiorPlateDistance;
	private Label lDistanceBetweenPlates;
	private Label lMassValue;
	private Label lActuatingForce;
	private Label lFinalBallSpeed;
	private Label lAverageDistance;
	private Label lAverageTime;
	private Label lAverageAceleration;

	private Button bChargeChanger;
	private Button bResetBall;

	private CheckBox cbGravityEnabled;
	private CheckBox cbMouseEnabled;
	private CheckBox cbVanishingEnabled;

	public Menu(Pane uiRoot) {
		initComponents(uiRoot);
		initLayout();
	}

	private void initComponents(Pane uiRoot) {
		slFieldPower = new Slider();
		slBallMass = new Slider();
		slBallSpeed = new Slider();

		lUpperPlateDistance = new Label();
		lInferiorPlateDistance = new Label();
		lDistanceBetweenPlates = new Label();
		lMassValue = new Label();
		lActuatingForce = new Label();
		lFinalBallSpeed = new Label();
		lAverageDistance = new Label();
		lAverageTime = new Label();
		lAverageAceleration = new Label();

		increaseLabelsTextSize();

		bChargeChanger = new Button("Change charge");
		bResetBall = new Button("Reset ball");

		cbGravityEnabled = new CheckBox("Enable gravity");
		cbMouseEnabled = new CheckBox("Enable Mouse");
		cbVanishingEnabled = new CheckBox("Enable to vanish");

		uiRoot.getChildren().addAll(slFieldPower, slBallMass,
				lUpperPlateDistance, lInferiorPlateDistance, bChargeChanger,
				lDistanceBetweenPlates, lMassValue, lActuatingForce,
				lFinalBallSpeed, slBallSpeed, cbGravityEnabled, cbMouseEnabled,
				cbVanishingEnabled, bResetBall, lAverageDistance, lAverageTime,
				lAverageAceleration);

	}

	private void increaseLabelsTextSize() {
		lUpperPlateDistance.setScaleX(X_SCALE);
		lUpperPlateDistance.setScaleY(Y_SCALE);

		lInferiorPlateDistance.setScaleX(X_SCALE);
		lInferiorPlateDistance.setScaleY(Y_SCALE);

		lDistanceBetweenPlates.setScaleX(X_SCALE);
		lDistanceBetweenPlates.setScaleY(Y_SCALE);

		lMassValue.setScaleX(X_SCALE);
		lMassValue.setScaleX(X_SCALE);
		
		lActuatingForce.setScaleX(X_SCALE);
		lActuatingForce.setScaleY(Y_SCALE);
		
		lFinalBallSpeed.setScaleX(X_SCALE);
		lFinalBallSpeed.setScaleY(Y_SCALE);
		

		lAverageDistance.setScaleX(X_SCALE);
		lAverageDistance.setScaleY(Y_SCALE);
		
		
		lAverageTime.setScaleX(X_SCALE);
		lAverageTime.setScaleY(Y_SCALE);
		
		lAverageAceleration.setScaleX(X_SCALE);
		lAverageAceleration.setScaleY(Y_SCALE);
	}

	private void initLayout() {
		initSlidersRange();
		initHandlers();
		slFieldPower.setLayoutX(510);
		slBallSpeed.setLayoutX(510);
		slBallMass.setLayoutX(510);

		slFieldPower.setLayoutY(410);
		slBallSpeed.setLayoutY(430);
		slBallMass.setLayoutY(450);

		lUpperPlateDistance.setLayoutX(70);
		lInferiorPlateDistance.setLayoutX(70);
		lDistanceBetweenPlates.setLayoutX(70);
		lFinalBallSpeed.setLayoutX(70);
		lAverageDistance.setLayoutX(70);
		lAverageTime.setLayoutX(70);
		lAverageAceleration.setLayoutX(70);

		lUpperPlateDistance.setLayoutY(425);
		lInferiorPlateDistance.setLayoutY(450);
		lDistanceBetweenPlates.setLayoutY(475);
		lFinalBallSpeed.setLayoutY(500);
		lAverageDistance.setLayoutY(525);
		lAverageTime.setLayoutY(550);
		lAverageAceleration.setLayoutY(575);

		bChargeChanger.setLayoutX(500);
		bResetBall.setLayoutX(500);

		bChargeChanger.setLayoutY(550);
		bResetBall.setLayoutY(600);

		cbGravityEnabled.setLayoutX(500);
		cbGravityEnabled.setLayoutY(450);

		cbMouseEnabled.setLayoutX(500);
		cbMouseEnabled.setLayoutY(475);

		cbVanishingEnabled.setLayoutX(500);
		cbVanishingEnabled.setLayoutY(500);

	}

	private boolean mouseIsEnabled = false;
	private boolean vanishIsEnabled = false;
	private boolean gravityIsEnabled = false;

	private void initHandlers() {
		cbMouseEnabled.setOnAction(event -> {
			mouseIsEnabled = mouseIsEnabled ? false : true;
		});

		cbVanishingEnabled.setOnAction(event -> {
			vanishIsEnabled = vanishIsEnabled ? false : true;
		});

		cbGravityEnabled.setOnAction(event -> {
			gravityIsEnabled = gravityIsEnabled ? false : true;
		});

	}

	private void initSlidersRange() {
		slFieldPower.setMin(0);
		slFieldPower.setMax(40);
		slBallSpeed.setMin(2);
		slBallSpeed.setMax(40);
		slBallMass.setMin(.1);
		slBallMass.setMax(100);
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

	public double getSliderValue() {
		return this.slFieldPower.getValue();
	}

	public Double getMassSliderValue() {
		return this.slBallMass.getValue();
	}

	public double getBallSpeedValue() {
		return this.slBallSpeed.getValue();
	}

	public void setDistanceBetweenPlates(double distance) {
		lDistanceBetweenPlates.setText("Distance between plates: "
				+ String.format("%.2f", distance) + "mm");

	}

	public void setDistanceUpperPlate(double distance) {

		lUpperPlateDistance.setText("Distance to upper plate: "
				+ String.format("%.2f", distance) + "mm");
	}

	public void setDistanceInferiorPlate(double distance) {
		lInferiorPlateDistance.setText("Distance to inferior plate: "
				+ String.format("%.2f", distance) + "mm");

	}

	public void setBallVelocity(double velocity, int exp) {
		String expToStr = Util.superscript(exp + "");
		lFinalBallSpeed.setText("Velocity: " + String.format("%.2f", velocity)
				+ "* 10" + expToStr + "mm/s");

	}

	public void setDistanceValue(double distance, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageDistance
				.setText("Average distance: " + String.format("%.2f", distance)
						+ " * 10" + expToStr + " cm/s");

	}

	public void setButtonChargeEvent(EventHandler<ActionEvent> event) {
		bChargeChanger.setOnAction(event);
	}

	public void setResetBallEvent(EventHandler<ActionEvent> event) {
		bResetBall.setOnAction(event);

	}

	public void setAverageTime(double time, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageDistance.setText("Average time: " + String.format("%.2f", time)
				+ " * 10" + expToStr + " seconds");

	}

	public void setAverageAceleration(double aceleration, int exp) {
		String expToStr = Util.superscript(exp + "");
		lAverageAceleration.setText(
				"Average aceleration: " + String.format("%.2f", aceleration)
						+ " * 10" + expToStr + " cm/s");
	}

}

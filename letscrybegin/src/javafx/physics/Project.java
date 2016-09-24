package javafx.physics;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.GameSettings;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.entity.Entity;

import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Project extends GameApplication {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;

	private Assets assets;

	private Ball ball;

	private Plate inferiorPlate;

	private Plate upperPlate;

	private Entity background;
	private Entity field;

	private Slider slider;
	private Slider massSlider;
	private Slider ballSpeed;
	private Label upperPlateDistance;
	private Label inferiorPlateDistance;
	private Label distanceBetweenPlates;
	private Label massValue;
	private Label actuatingForce;
	private Label ballAcceleraction;
	private Label finalBallVelocity;
	private Button chargeChanger;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("Classical Physic");
		settings.setVersion("DEMO");
		settings.setWidth(WIDTH);
		settings.setHeight(HEIGHT);

	}

	@Override
	protected void initAssets() throws Exception {
		assets = assetManager.cache();
		assets.logCached();
		ball = new Ball(30d, 660, 180);

	}

	@Override
	protected void initUI(Pane uiRoot) {
		initComponents(uiRoot);

		initMainMenu(uiRoot);

		initLayout();

	}

	private void initLayout() {
		slider.setLayoutX(500);
		slider.setLayoutY(500);

		slider.setMin(0);
		slider.setMax(10);

		ballSpeed.setLayoutX(500);
		ballSpeed.setLayoutY(600);

		ballSpeed.setMin(2);
		ballSpeed.setMax(40);

		massSlider.setLayoutX(500);
		massSlider.setLayoutY(550);

		massSlider.setMin(.1);
		massSlider.setMax(100);

		upperPlateDistance.setLayoutX(300);
		upperPlateDistance.setLayoutY(300);

		inferiorPlateDistance.setLayoutX(300);
		inferiorPlateDistance.setLayoutY(315);

		distanceBetweenPlates.setLayoutX(300);
		distanceBetweenPlates.setLayoutY(330);

		chargeChanger.setLayoutX(150);
		chargeChanger.setLayoutY(330);

		chargeChanger.setText("Change ball charge");

		actuatingForce.setLayoutX(200);
		actuatingForce.setLayoutY(400);

		ballAcceleraction.setLayoutX(220);
		ballAcceleraction.setLayoutY(420);

		finalBallVelocity.setLayoutX(240);
		finalBallVelocity.setLayoutY(440);
	}

	private void initComponents(Pane uiRoot) {
		slider = new Slider();
		massSlider = new Slider();
		ballSpeed = new Slider();
		upperPlateDistance = new Label();
		inferiorPlateDistance = new Label();
		distanceBetweenPlates = new Label();
		massValue = new Label();
		actuatingForce = new Label();
		ballAcceleraction = new Label();
		finalBallVelocity = new Label();
		chargeChanger = new Button();

		chargeChanger.setOnAction(event -> ball.changeCharge());
		uiRoot.getChildren().addAll(slider, massSlider, upperPlateDistance,
				inferiorPlateDistance, chargeChanger, distanceBetweenPlates,
				massValue, actuatingForce, finalBallVelocity, ballAcceleraction,
				ballSpeed);
	}

	@Override
	protected void initInput() {

	}

	private void initInferiorPlate() {
		inferiorPlate = new Plate(198, 271, Types.NEGATIVE_PLATE);
		addEntities(inferiorPlate.getEntities());

	}

	private void initUpperPlate() {
		upperPlate = new Plate(198, 75, Types.POSITIVE_PLATE);
		addEntities(upperPlate.getEntities());
	}

	private double gravityY;
	private double linearVelocity = 1;
	private Point2D previousPosition;

	private void initBall() {
		ball.changeCharge();
		ball.initEntity();
		addEntities(ball.getEntity());
		previousPosition = ball.getCenter();

		ball.setLinearVelocity(new Point2D(-linearVelocity, 0));

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initGame(Pane gameRoot) {

		physicsManager.setGravity(0, 0);

		initBackground();
		initInferiorPlate();
		initUpperPlate();

		initField();
		initBall();
	}

	private void initField() {
		field = new Entity(Types.FIELD);
		field.setPosition(198, 100);
		field.setGraphics(assets.getTexture("field.png"));
		field.setVisible(false);
		addEntities(field);

	}

	private void initBackground() {
		background = new Entity(Types.BACKGROUND);
		background.setPosition(0, 0);
		background.setGraphics(assets.getTexture("background.png"));
		addEntities(background);

	}

	@Override
	protected void onUpdate(long now) {
		upperPlate.setFieldStrength(slider.getValue());
		inferiorPlate.setFieldStrength(slider.getValue());
		

		ball.disableRotate();
		if (mouse.leftPressed) {
			ball.getEntity().setLinearVelocity(new Point2D(mouse.x, mouse.y)
					.subtract(ball.getPosition()).multiply(.1));
		}

		ball.setMass(massSlider.getValue());

		gravityY = getFieldPower();
		linearVelocity = ballSpeed.getValue();

		showInformations();

		if (isOutOfScreen())
			initBall();

		if (field.getBoundsInParent().intersects(ball.getBoundsInParent())) {
			updateGravity();
			if (isTouchingPlates() && getFieldPower() > 0)
				initBall();
		} else {
			physicsManager.setGravity(0, 0);
		}

	}

	private double getFieldPower() {
		return slider.getValue();
	}

	private boolean isOutOfScreen() {
		return !background.getBoundsInParent()
				.intersects(ball.getBoundsInParent());
	}

	private void updateGravity() {
		if (ball.isPositive()) {
			gravityY = Math.abs(gravityY) + ball.getMass();
		} else {
			gravityY = Math.abs(gravityY) * -1 + ball.getMass();
		}

		physicsManager.setGravity(0, 0);// gravityY);

	}

	private void showInformations() {
		showMassValue();

		distanceBetweenPlates
				.setText(
						"Distance between plates: "
								+ String.format("%.2f",
										upperPlate.distanceOf(
												inferiorPlate.getEntity()))
								+ "mm");

		upperPlateDistance.setText("Distance to upper plate: "
				+ String.format("%.2f", upperPlate.distanceOf(ball.getEntity()))
				+ "mm");

		inferiorPlateDistance
				.setText(
						"Distance to inferior plate: "
								+ String.format("%.2f",
										inferiorPlate
												.distanceOf(ball.getEntity()))
								+ "mm");

		actuatingForce.setText("Actuatinc force over particle: "
				+ calculateBallActuatingForce());
		ballAcceleraction.setText(
				"Particle acceleration: " + calculeteBallAcceleration());

		double distance = ball.getCenter().distance(previousPosition);
		previousPosition = ball.getCenter();

		finalBallVelocity
				.setText("Velocity: " + String.format("%.2f", distance));

	}

	private void showMassValue() {// TODO
		Double massValueDistanceX = massSlider.getChildrenUnmodifiable().get(1)
				.getLayoutX() + massSlider.getLayoutX() + 2;
		Double massValueDistanceY = 535.0;
		massValue.setLayoutX(massValueDistanceX);
		massValue.setLayoutY(massValueDistanceY);

		int valueMass = ball.getMass().intValue();
		massValue.setText("" + valueMass);
	}

	private boolean isTouchingPlates() {
		if (ball.isPositive()) {
			if (inferiorPlate.intersects(ball.getEntity())) {
				return true;
			}
		}

		else {
			if (upperPlate.intersects(ball.getEntity())) {
				return true;
			}
		}

		return false;

	}

	// Calculate the actuating force on electrical charge (ball)
	private Double calculateBallActuatingForce() {
		Double ballCharge = 10.0; // It's necessary to define a charge for the
									// ball
		Double eletricalField = 100.0; // and a value for the field
		return ballCharge * eletricalField;
	}

	private Double calculeteBallAcceleration() {
		return calculateBallActuatingForce() / ball.getMass();
	}

	private Double calculateBallFinalVelocity() {
		Double initialVelocity = 10.0; // We need set a initial velocity for the
										// ball
		Double distance = 20.0; // and put here the distance of plate
		Double result = Math.sqrt((initialVelocity * initialVelocity)
				+ 2 * (calculeteBallAcceleration() * distance));
		return result;
	}

}

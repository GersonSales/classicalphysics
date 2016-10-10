package javafx.physics;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.GameSettings;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Project extends GameApplication {

	private final int WIDTH = 790;
	private final int HEIGHT = 570;

	private Assets assets;

	private Ball ball;

	private Plate inferiorPlate;

	private Plate upperPlate;

	private Entity background;
	private Entity field;
	private Menu menu;

	@Override
	protected void initSettings(GameSettings settings) {
		System.out.println("initSettings");
		settings.setTitle("Classical Physic");
		settings.setVersion("DEMO");
		settings.setWidth(WIDTH);
		settings.setHeight(HEIGHT);
	}

	@Override
	protected void initAssets() throws Exception {
		System.out.println("initAssets");

		assets = assetManager.cache();
		assets.logCached();
		ball = new Ball(30d, 660, 180);

	}

	@Override
	protected void initUI(Pane uiRoot) {
		System.out.println("initUI");

		menu = new Menu(uiRoot);

		menu.setButtonChargeEvent(event -> ball.changeCharge());
		menu.setResetBallEvent(event -> initBall());
	}

	@Override
	protected void initInput() {
		System.out.println("initInput");

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
		System.out.println("initGame");

		physicsManager.setGravity(0, 0);

		initBackground();
		initInferiorPlate();
		initUpperPlate();

		initField();
		initBall();
		initInferiorLimit();
	}

	private PhysicsEntity upperLimit;
	private PhysicsEntity rightLimit;
	private PhysicsEntity leftLimit;

	private void initLimits() {
		upperLimit = new PhysicsEntity(Types.WALL);
		rightLimit = new PhysicsEntity(Types.WALL);
		leftLimit = new PhysicsEntity(Types.WALL);

		upperLimit.setGraphics(new Rectangle(900, 0));
		rightLimit.setGraphics(new Rectangle(0, 900));
		leftLimit.setGraphics(new Rectangle(0, 900));

		upperLimit.setPosition(0, 0);
		rightLimit.setPosition(0, 0);
		leftLimit.setPosition(WIDTH, 0);

		addEntities(upperLimit, rightLimit, leftLimit);

	}

	PhysicsEntity inferiorLimt;

	private void initInferiorLimit() {
		inferiorLimt = new PhysicsEntity(Types.WALL);
		inferiorLimt.setGraphics(new Rectangle(900, 10));
		inferiorLimt.setPosition(0, 407);
		inferiorLimt.setVisible(false);

		addEntities(inferiorLimt);
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
		upperPlate.setFieldStrength(menu.getSliderValue());
		inferiorPlate.setFieldStrength(menu.getSliderValue());

		ball.disableRotate();

		uptadeMouseControl();
		updateLimits();
		updateGravity();

		ball.setMass(menu.getMassSliderValue());// massSlider.getValue());

		fieldPower = getFieldPower();
		linearVelocity = menu.getBallSpeedValue();// ballSpeed.getValue();

		showInformations();

		if (isOutOfScreen())
			initBall();

		updateField();

	}

	private double fieldPower = 0;

	private void updateField() {
		if (field.getBoundsInParent().intersects(ball.getBoundsInParent())) {

			if (ball.isPositive()) {
				fieldPower = Math.abs(fieldPower)/* + ball.getMass() */;
			} else {
				fieldPower = Math.abs(fieldPower) * -1/* + ball.getMass() */;
			}

			physicsManager.setGravity(0, fieldPower);

			if (isTouchingPlates() && getFieldPower() > 0)
				initBall();
		}

	}

	private void updateLimits() {
		if (menu.isVanishEnabled()) {

		} else {
		}
	}

	private void uptadeMouseControl() {
		if (menu.isMouseEnabled()) {
			if (mouse.leftPressed) {
				ball.getEntity().setLinearVelocity(new Point2D(mouse.x, mouse.y)
						.subtract(ball.getPosition()).multiply(.1));
			}
		}

	}

	private double getFieldPower() {
		return menu.getSliderValue();
	}

	private boolean isOutOfScreen() {
		return !background.getBoundsInParent()
				.intersects(ball.getBoundsInParent());
	}

	private void updateGravity() {
		if (menu.isGravityEnabled()) {
			gravityY = 1000;
			if (ball.intersects(inferiorLimt)) {
				ball.setLinearVelocity(new Point2D(0, 0).multiply(.1));
			}
		} else {
			gravityY = 0;
		}

		physicsManager.setGravity(0, gravityY);

	}

	private void showInformations() {
		menu.setDistanceBetweenPlates(
				upperPlate.distanceOf(inferiorPlate.getEntity()));

		menu.setDistanceUpperPlate(upperPlate.distanceOf(ball.getEntity()));

		menu.setDistanceInferiorPlate(
				inferiorPlate.distanceOf(ball.getEntity()));

		updateBallInformation();

	}

	private long previousTime;

	private void updateBallInformation() {
		double time = (System.currentTimeMillis() - previousTime) / 1000.0;
		if (!ball.getCenter().equals(previousPosition)) {
			previousTime = System.currentTimeMillis();
		}

		double distance = (ball.getCenter().distance(previousPosition)) * 2.54
				/ 96.;
		previousPosition = ball.getCenter();

		double velocity = distance / time;

		double aceleration = velocity / time;

		menu.setDistanceValue(distance * Math.pow(10, 2), -2);
		menu.setAverageTime(time * Math.pow(10, 2), -2);
		menu.setAverageAceleration(aceleration / Math.pow(10, 1), 1);
		menu.setBallVelocity(velocity, 0);

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
}

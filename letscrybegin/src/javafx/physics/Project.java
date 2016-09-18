package javafx.physics;

import org.jbox2d.dynamics.BodyType;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.GameSettings;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsEntity;

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

	private PhysicsEntity inferiorPlate;
	private PhysicsEntity upperPlate;
	private PhysicsEntity leftWall;
	private PhysicsEntity rightWall;

	private Entity background;
	private Entity field;

	private Slider slider;
	private Slider massSlider;
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

	}

	@Override
	protected void initUI(Pane uiRoot) {
		slider = new Slider();
		massSlider = new Slider();
		upperPlateDistance = new Label();
		inferiorPlateDistance = new Label();
		distanceBetweenPlates = new Label();
		massValue = new Label();
		actuatingForce = new Label();
		ballAcceleraction = new Label();
		finalBallVelocity = new Label();
		
		chargeChanger = new Button();
		chargeChanger.setText("Change ball charge");
		chargeChanger.setOnAction(event -> ball.changeCharge());
		uiRoot.getChildren().addAll(slider, massSlider, upperPlateDistance, inferiorPlateDistance, chargeChanger,
				distanceBetweenPlates, massValue, actuatingForce, finalBallVelocity, ballAcceleraction);

		initMainMenu(uiRoot);

		slider.setLayoutX(500);
		slider.setLayoutY(500);

		slider.setMin(0);
		slider.setMax(10);
		
		massSlider.setLayoutX(500);
		massSlider.setLayoutY(550);

		upperPlateDistance.setLayoutX(300);
		upperPlateDistance.setLayoutY(300);

		inferiorPlateDistance.setLayoutX(300);
		inferiorPlateDistance.setLayoutY(315);

		distanceBetweenPlates.setLayoutX(300);
		distanceBetweenPlates.setLayoutY(330);

		chargeChanger.setLayoutX(150);
		chargeChanger.setLayoutY(330);

		actuatingForce.setLayoutX(200);
		actuatingForce.setLayoutY(400);
		
		ballAcceleraction.setLayoutX(220);
		ballAcceleraction.setLayoutY(420);
		
		finalBallVelocity.setLayoutX(240);
		finalBallVelocity.setLayoutY(440);

	}

	@Override
	protected void initInput() {
	}

	private void initInferiorPlate() {
		inferiorPlate = new PhysicsEntity(Type.WALL);
		inferiorPlate.setPosition(198, 271);
		inferiorPlate.setGraphics(assets.getTexture("inferiorPlate.png"));
		inferiorPlate.setBodyType(BodyType.KINEMATIC);

		addEntities(inferiorPlate);

	}

	private void initUpperPlate() {
		upperPlate = new PhysicsEntity(Type.WALL);
		upperPlate.setPosition(198, 75);
		upperPlate.setGraphics(assets.getTexture("upperPlate.png"));
		upperPlate.setBodyType(BodyType.KINEMATIC);

		addEntities(upperPlate);
	}

	private double gravityY;

	private void initBall() {
		ball.changeCharge();
		ball.initEntity();
		addEntities(ball.getEntity());

		ball.setLinearVelocity(new Point2D(5, 0));

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	protected void initGame(Pane gameRoot) {
		ball = new Ball(30d);

		physicsManager.setGravity(0, 0);

		initBackground();
		initInferiorPlate();
		initUpperPlate();
		// initLeftWall();
		initRightWall();

		initField();
		initBall();
	}


	private void initField() {
		field = new Entity(Type.FIELD);
		field.setPosition(198, 100);
		field.setGraphics(assets.getTexture("field.png"));
		field.setVisible(false);
		addEntities(field);

	}

	@SuppressWarnings("unused")
	private void initLeftWall() {
		leftWall = new PhysicsEntity(Type.WALL);
		leftWall.setPosition(270, 73);
		leftWall.setGraphics(assets.getTexture("leftWall.png"));
		leftWall.setVisible(false);
		addEntities(leftWall);
	}

	private void initRightWall() {
		rightWall = new PhysicsEntity(Type.WALL);
		rightWall.setPosition(600, 73);
		rightWall.setGraphics(assets.getTexture("rightWall.png"));
		rightWall.setVisible(false);
		addEntities(rightWall);
	}

	private void initBackground() {
		background = new Entity(Type.BACKGROUND);
		background.setPosition(0, 0);
		background.setGraphics(assets.getTexture("background.png"));
		addEntities(background);

	}

	@Override
	protected void onUpdate(long now) {
		showMassValue();
		ball.setMass(massSlider.getValue()); //here the mass of ball is modified according to Slider position
		gravityY = slider.getValue();
		
		updateGravity();

		showDistanceUpperPlate();
		showDistanceInferiroPlate();
		showDistanceBetweenPlates();
		
		//Need to format this values, put real informations
		// and put a strategic point on screen
		actuatingForce.setText("Actuatinc force over particle: " + calculateBallActuatingForce());
		ballAcceleraction.setText("Particle acceleration: " + calculeteBallAcceleration());
		finalBallVelocity.setText("Final velocity: " + calculateBallFinalVelocity());

		if (field.getBoundsInParent().intersects(ball.getBoundsInParent())) {
			physicsManager.setGravity(0, gravityY);
		} else {
			physicsManager.setGravity(0, 0);
		}

		checkBallIntersec();
	}

	private void updateGravity() {
		if (ball.isPositive()) {
			gravityY = Math.abs(gravityY + ball.getMass()); // Increased the mass value in the gravity 
		} else {
			gravityY = Math.abs(gravityY) * -1 + ball.getMass(); //Increased the mass value in the gravity
		}
	}

	private void showDistanceBetweenPlates() {
		int valueDistanceBetweenPlates = (int) Math.abs(
				upperPlate.getPosition().getY() - (inferiorPlate.getPosition().getY() - inferiorPlate.getHeight()));
		
		distanceBetweenPlates.setText("Distance: " + valueDistanceBetweenPlates + "mm");
	}
	
	private void showDistanceUpperPlate() {
		int valueUpperPlateDistance = (int) Math
				.abs(ball.getPosition().getY() - (upperPlate.getPosition().getY() + upperPlate.getHeight()));
		
		upperPlateDistance.setText("Distance: " + valueUpperPlateDistance + "mm");
	}
	
	private void showDistanceInferiroPlate() {
		int valueInferiorPlateDistance = (int) Math
				.abs(ball.getPosition().getY() - (inferiorPlate.getPosition().getY() - inferiorPlate.getHeight()));
		
		inferiorPlateDistance.setText("Distance: " + valueInferiorPlateDistance + "mm");
	}
	
	private void showMassValue() {
		massSlider.setMin(000.1); //if starts with 0 it will be used with gravity and the ball will get down on field area
		massSlider.setMax(10);    // we need check it out

		Double massValueDistanceX = massSlider.getChildrenUnmodifiable().get(1).getLayoutX() + massSlider.getLayoutX()
				+ 2;
		Double massValueDistanceY = 535.0;
		massValue.setLayoutX(massValueDistanceX);
		massValue.setLayoutY(massValueDistanceY);

		int valueMass = ball.getMass().intValue();
		massValue.setText("" + valueMass);
	}
	
	private void checkBallIntersec() {

		if (!background.getBoundsInParent().intersects(ball.getBoundsInParent())) {
			initBall();
		}

		if (inferiorPlate.getBoundsInParent().intersects(ball.getBoundsInParent())) {
			initBall();
		}

		if (upperPlate.getBoundsInParent().intersects(ball.getBoundsInParent())) {
			initBall();
		}
	}
	
	//Calculate the actuating force on electrical charge (ball)
	private Double calculateBallActuatingForce() {
		Double ballCharge = 10.0;  //It's necessary to define a charge for the ball 
		Double eletricalField = 100.0; // and a value for the field
		return ballCharge * eletricalField;
	}

	private Double calculeteBallAcceleration() {
		return calculateBallActuatingForce() / ball.getMass();
	}

	private Double calculateBallFinalVelocity() {
		Double initialVelocity = 10.0; //We need set a initial velocity for the ball
		Double distance = 20.0;        //and put here the distance of plate
		Double result = Math.sqrt((initialVelocity * initialVelocity) + 2 * (calculeteBallAcceleration() * distance));
		return result;
	}

}

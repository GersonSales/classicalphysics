package javafx.physics;

import org.jbox2d.dynamics.BodyType;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.GameSettings;
import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityType;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

public class Project extends GameApplication {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;

	private Assets assets;

	private Ball ball;
	private Texture ballTexture;

	private PhysicsEntity inferiorPlate;
	private PhysicsEntity upperPlate;
	private PhysicsEntity leftWall;
	private PhysicsEntity rightWall;

	private Entity background;
	private Entity field;

	private Slider slider;
	private Label upperPlateDistance;
	private Label inferiorPlateDistance;
	private Button chargeChanger;
	private Pane myRoot;

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
		myRoot = uiRoot;
		slider = new Slider();
		upperPlateDistance = new Label();
		inferiorPlateDistance = new Label();
		chargeChanger = new Button();
		chargeChanger.setText("-");

		chargeChanger.setOnAction(event -> {
			flag = !flag;
			if (flag)
				chargeChanger.setText("+");
			else
				chargeChanger.setText("-");
			;
		});
		uiRoot.getChildren().addAll(slider, upperPlateDistance,
				inferiorPlateDistance, chargeChanger);

		initMainMenu(uiRoot);

		slider.setLayoutX(500);
		slider.setLayoutY(500);

		upperPlateDistance.setLayoutX(300);
		upperPlateDistance.setLayoutY(300);

		inferiorPlateDistance.setLayoutX(300);
		inferiorPlateDistance.setLayoutY(315);

		chargeChanger.setLayoutX(200);
		chargeChanger.setLayoutY(300);

		slider.setMin(-10);
		slider.setMax(10);
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

	private boolean flag = true;

	private void initBall() {
		if (flag) {
			ball = new Ball(ballTexture, 30d, Type.POSITIVE);
		} else {
			ball = new Ball(ballTexture, 30d, Type.NEGATIVE);
		}

		addEntities(ball.getEntity());

		ball.setLinearVelocity(new Point2D(5, 0));

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
		// initLeftWall();
		initRightWall();
		initButtonMP();

		initField();
		initBall();

	}

	private void initButtonMP() {
		// TODO Auto-generated method stub
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

	private void test() {
		removeEntity(ball.getEntity());
		ball.changeCharge();
		addEntities(ball.getEntity());
		ball.setLinearVelocity(new Point2D(5, 0));
	}

	@Override
	protected void onUpdate(long now) {

		int value = (int) Math.abs(ball.getPosition().getY()
				- (upperPlate.getPosition().getY() + upperPlate.getHeight()));

		int value2 = (int) Math.abs(
				ball.getPosition().getY() - (inferiorPlate.getPosition().getY()
						- inferiorPlate.getHeight()));

		upperPlateDistance.setText("Distance: " + value + "mm");
		inferiorPlateDistance.setText("Distance: " + value2 + "mm");

		if (field.getBoundsInParent().intersects(ball.getBoundsInParent())) {
			physicsManager.setGravity(0, slider.getValue());
		} else {
			physicsManager.setGravity(0, 0);

		}

		if (!background.getBoundsInParent()
				.intersects(ball.getBoundsInParent())) {
			removeEntity(ball.getEntity());
			initBall();

		}

		if (inferiorPlate.getBoundsInParent()
				.intersects(ball.getBoundsInParent())) {
			test();
			// removeEntity(ball.getEntity());
			// initBall();

		}

		// if (upperPlate.getBoundsInParent()
		// .intersects(ball.getBoundsInParent())) {
		//
		// removeEntity(ball.getEntity());
		// initBall();
		// }

	}

}

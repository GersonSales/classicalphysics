package javafx.physics3;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.GameSettings;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.scene.Cursor;
import javafx.scene.layout.Pane;

public class Project extends GameApplication {

	private Wire wireOne;
	private Wire wireTwo;

	private Arrow arrowUp;
	private Arrow arrowDown;

	private PhysicsEntity dot;
	private Assets assets;

	private static final int WIDTH = 500;
	private static final int HEIGHT = 800;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("Classical Physics Second Stage");
		settings.setVersion("DEMO");
		settings.setWidth(WIDTH);
		settings.setWidth(HEIGHT);
	}

	private void initWires() {

		this.wireOne = new Wire(200.0, 200.0, Types.WIRE, assets.getTexture("fio1.png"));
		this.wireTwo = new Wire(400.0, 200.0, Types.WIRE, assets.getTexture("fio2.png"));
		this.arrowUp = new Arrow(212.0, 260.0, Types.ARROW, assets.getTexture("arrow-up.png"));
		this.arrowDown = new Arrow(412.0, 260.0, Types.ARROW, assets.getTexture("arrow-down.png"));

		this.dot = new PhysicsEntity(Types.DOT);
		
		dot.setPosition(200, 500);
		dot.setGraphics(assets.getTexture("dot.png"));
		
		physicsManager.setGravity(0, 0);

		addEntities(wireOne.getEntity());
		addEntities(wireTwo.getEntity());
		addEntities(arrowUp.getEntity());
		addEntities(arrowDown.getEntity());
		addEntities(dot);
		
		orgSceneX = wireTwo.getEntity().getTranslateX();
		orgSceneY = wireOne.getEntity().getTranslateX();
		dotSceneX = dot.getTranslateX();
		dotSceneY = dot.getTranslateY();
	}

	@Override
	protected void initAssets() throws Exception {
		assets = assetManager.cache();
		assets.logCached();
	}

	@Override
	protected void initGame(Pane gameRoot) {
		initWires();

	}

	@Override
	protected void initUI(Pane uiRoot) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initInput() {
		// TODO Auto-generated method stub

	}

	protected double orgSceneX;
	protected double orgSceneY;
	protected double dotSceneX;
	protected double dotSceneY;

	@Override
	protected void onUpdate(long now) {
		wireOne.getEntity().setCursor(Cursor.MOVE);
		wireTwo.getEntity().setCursor(Cursor.MOVE);
		dot.setCursor(Cursor.MOVE);

		if (wireTwo.getEntity().isPressed()) {
			wireTwo.getEntity().setPosition(mouse.x, 200);
			orgSceneX = mouse.x;
		} else {
			wireTwo.getEntity().setPosition(orgSceneX, 200);
		}

		if (wireOne.getEntity().isPressed()) {
			wireOne.getEntity().setPosition(mouse.x, 200);
			orgSceneY = mouse.x;
		} else {
			wireOne.getEntity().setPosition(orgSceneY, 200);
		}
		
		if (dot.isPressed()) {
			dot.setPosition(mouse.x, mouse.y);
			dotSceneX = mouse.x;
			dotSceneY = mouse.y;
		} else {
			dot.setPosition(dotSceneX, dotSceneY);
		}


		arrowUp.getEntity().setPosition(wireOne.getEntity().getTranslateX() + 12, 260);
		arrowDown.getEntity().setPosition(wireTwo.getEntity().getTranslateX() + 12, 260);

	}

	public static void main(String[] args) {
		launch(args);
	}

}

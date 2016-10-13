package javafx.physics3;

import java.text.DecimalFormat;

import com.almasb.fxgl.GameApplication;
import com.almasb.fxgl.GameSettings;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.physics.Util;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Project extends GameApplication {

	private Wire wireOne;
	private Wire wireTwo;

	private Arrow arrowOne;
	private Arrow arrowTwo;

	private Entity dot;
	private Assets assets;

	private Menu menu;
	private Entity background;

	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;

	@Override
	protected void initSettings(GameSettings settings) {
		settings.setTitle("Classical Physics Third Stage");
		settings.setVersion("DEMO");
		settings.setWidth(WIDTH);
		settings.setWidth(HEIGHT);

	}

	@Override
	protected void initAssets() throws Exception {
		assets = assetManager.cache();
		assets.logCached();

		this.wireOne = new Wire(200.0, 200.0, Types.WIRE, assets.getTexture("cableOne.png"));
		this.wireTwo = new Wire(400.0, 200.0, Types.WIRE, assets.getTexture("cableTwo.png"));
		this.arrowOne = new Arrow(212.0, 260.0, Types.ARROW, assets.getTexture("arrow-up.png"));
		this.arrowTwo = new Arrow(412.0, 260.0, Types.ARROW, assets.getTexture("arrow-down.png"));
		this.dot = new PhysicsEntity(Types.DOT);

		dot = new Entity(Types.PLAYER);
		dot.setGraphics(new Circle());


	}

	private void initWires() {
		dot.setPosition(300, 300);
		dot.setGraphics(assets.getTexture("dot.png"));

	}

	private void initScreenBounds() {
		PhysicsEntity top = new PhysicsEntity(Types.SCREEN);
		top.setPosition(0, -10);
		top.setGraphics(new Rectangle(getWidth(), 10));

		PhysicsEntity bot = new PhysicsEntity(Types.SCREEN);
		bot.setPosition(0, getHeight() + 30);
		bot.setGraphics(new Rectangle(getWidth(), 10));

		PhysicsEntity left = new PhysicsEntity(Types.SCREEN);
		left.setPosition(-10, 0);
		left.setGraphics(new Rectangle(10, getHeight()));

		PhysicsEntity right = new PhysicsEntity(Types.SCREEN);
		right.setPosition(getWidth() + 7, -15);
		right.setGraphics(new Rectangle(10, getHeight() + 30));
		

		addEntities(top, bot, left, right);

	}

	@Override
	protected void initGame(Pane gameRoot) {
		initBackground();
		physicsManager.setGravity(0, 0);
		initWires();
		initScreenBounds();

		addEntities(wireOne.getEntity());
		addEntities(wireTwo.getEntity());
		addEntities(arrowOne.getEntity());
		addEntities(arrowTwo.getEntity());
		addEntities(dot);

		addCollisionHandler(Types.WIRE, Types.WIRE, (p, o) -> {
			System.out.println(p.getTypeAsString() + " colide with " + p.getTypeAsString());
			wireOne.getEntity().translate(200, 200);
		});
	}

	
	private void initBackground() {
		background = new Entity(Types.BACKGROUND);
		background.setPosition(0, 0);
		background.setGraphics(assets.getTexture("background2.jpg"));
		addEntities(background);

	}
	@Override
	protected void initUI(Pane uiRoot) {
		menu = new Menu(uiRoot);

	}

	@Override
	protected void initInput() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onUpdate(long now) {
		setCursor();
		
		menu.setPositionCurrentOne(wireOne.getEntity().getTranslateX() , wireOne.getEntity().getTranslateY() - 50);
		menu.setPositionCurrentTwo(wireTwo.getEntity().getTranslateX() , wireTwo.getEntity().getTranslateY() - 50);
		
		menu.setLbCurrentDistanceOne(distanceWireOneToDot());
		menu.setLbCurrentDistanceTwo(distanceWireTwoToDot());
		
		menu.setLbCurrentOne(menu.getSlCurrentOneValue());
		menu.setLbCurrentTwo(menu.getSlCurrentTwoValue());
		
		

		if (menu.isChangArrowOneEnable()) {
			arrowOne.setTexture(assets.getTexture("arrow-down.png"));
		} else {
			arrowOne.setTexture(assets.getTexture("arrow-up.png"));
		}

		if (menu.isChangArrowTwoEnable()) {
			arrowTwo.setTexture(assets.getTexture("arrow-down.png"));
		} else {
			arrowTwo.setTexture(assets.getTexture("arrow-up.png"));
		}

		arrowOne.getEntity().setPosition(wireOne.getEntity().getTranslateX() + 5 ,
				wireOne.getEntity().getTranslateY() + 100);
		arrowTwo.getEntity().setPosition(wireTwo.getEntity().getTranslateX() + 5,
				wireTwo.getEntity().getTranslateY() + 100);

		if (menu.isCalculateEnable()) {
			poupUp();
			menu.setCalculate(false);
		}

		if (mouse.leftPressed) {

			if (wireOne.getEntity().isPressed()) {
				wireOne.getEntity()
						.setLinearVelocity(new Point2D(mouse.x, 200).subtract(wireOne.getPosition()).multiply(0.5));
			} else {
				wireOne.getEntity().setLinearVelocity(new Point2D(0, 0));

			}

			if (wireTwo.getEntity().isPressed()) {
				wireTwo.getEntity()
						.setLinearVelocity(new Point2D(mouse.x, 200).subtract(wireTwo.getPosition()).multiply(0.5));
			} else {
				wireTwo.getEntity().setLinearVelocity(new Point2D(0, 0));

			}

			if (dot.isPressed()) {
				dot.translate(new Point2D(mouse.x, mouse.y).subtract(dot.getPosition()).multiply(0.5));
			} else {
				dot.translate(new Point2D(0, 0));

			}
		}

	}

	private void setCursor() {
		wireOne.getEntity().setCursor(Cursor.MOVE);
		wireTwo.getEntity().setCursor(Cursor.MOVE);
		dot.setCursor(Cursor.MOVE);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void poupUp() {
		Stage primaryStage = new Stage();

		String wireOneStr = "Fio₁ \n" 
		+ "B₁ = (μ₀.i₁)/(2.π.r)\n" 
		+ "\n";

		String wireTwoStr = "Fio₂ \n" 
		+"B₂ = (μ₀.i₂)/(2.π.r)\n" 
		+ "\n";

		String formule = "B total = B₁ + B₂ \n" 
		+ "\n";

		String formule2 = "    = (μ₀/2π).(i₁/r₁ +- i₂/r₂)" 
		+ "\n";

		String solution = " = (4π × 10−⁷ T⋅m A)  . (i₁ . i₂)\n" 
						+ "            (2π)                   (r₁   r₂)"
		+ "\n";

		String solution2 = " = (4π × 10−⁷ T⋅m A)  .    (" + (int)menu.getSlCurrentOneValue() + "    +-    " + (int)menu.getSlCurrentTwoValue() + ")\n" 
				+ "            (2π)                   (" + Double.parseDouble(String.format("%.2f", distanceWireOneToDot()/10)) +"   "+ Double.parseDouble(String.format("%.2f", distanceWireOneToDot()/10)) + ")"
				+ "\n";
		
		
		DecimalFormat df = new DecimalFormat("0.0E00");    // Formato
		Double valorDouble =  Math.abs(calculateFiel());
		String calculus = (df.format(valorDouble)); 
		
		calculus = "B total = " + calculus + "T";
		
		Label wireOneLabel = new Label();
		wireOneLabel.setText(wireOneStr + "\n" + wireTwoStr + "\n" + formule 
				+ "\n" + formule2 + "\n" + solution + "\n" + "\n" + solution2 + "\n" + calculus);
		wireOneLabel.setMinSize(10, 10);


		HBox layout = new HBox(300);

		layout.setMinSize(400, 300);
		layout.setStyle("-fx-background-color: #D9D9F3; -fx-padding: 10;");

		layout.setAlignment(Pos.TOP_LEFT);
		layout.alignmentProperty();

		layout.getChildren().add(wireOneLabel);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	private Double distanceWireOneToDot() {
		return ((Util.distanceOf(dot, wireOne.getEntity()))* 2.54
				/ 96.);
	}

	private Double distanceWireTwoToDot() {
		return (Util.distanceOf(dot , wireTwo.getEntity())) * 2.54
				/ 96.;
	}

	private boolean isDotLeft() {
		return dot.getTranslateX() < wireOne.getEntity().getTranslateX();
	}

	private boolean isDotRight() {
		return dot.getTranslateX() > wireTwo.getEntity().getTranslateX();
	}


	
	public double calculateFiel() {
		double constants = 0.000000202;
		Double wire1 = (int)menu.getSlCurrentOneValue() / Double.parseDouble(String.format("%.2f", distanceWireOneToDot()/10));
		Double wire2 = (int)menu.getSlCurrentTwoValue() / Double.parseDouble(String.format("%.2f", distanceWireTwoToDot()/10));
		double result;
		if (menu.isChangArrowTwoEnable() && menu.isChangArrowOneEnable()) {
			if (isDotLeft()) {
				result = constants * (wire1 + wire2);
			} else if (isDotRight()){
				result = constants * (wire1 + wire2);
			} else {
				result = constants * (wire1 - wire2);
			}
		} else if (!menu.isChangArrowTwoEnable() && !menu.isChangArrowOneEnable()) {
			if (isDotLeft()) {
				result = constants * (wire1 + wire2);
			} else if (isDotRight()){
				result = constants * (wire1 + wire2);
			} else {
				result = constants * (wire1 - wire2);
			}
		} else {
			result = constants * (wire1 + wire2);
		}
		
		return result;
	}

}

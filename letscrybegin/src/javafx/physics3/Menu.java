package javafx.physics3;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Menu {

	private final double MAX_FONT_SIZE = 15;
	private final Font FONT = new Font(MAX_FONT_SIZE);

	private Slider slCurrentOne;
	private Slider slCurrentTwo;

	private Button btChangArrowOne;
	private Button btChangArrowTwo;
	private Button showCalculus;

	private Label lbCurrentOne;
	private Label lbCurrentTwo;

	private Label lbCurrentDistanceOne;
	private Label lbCurrentDistanceTwo;

	private boolean changArrowOne;
	private boolean changArrowTwo;
	private boolean calculate;

	public Menu(Pane uiRoot) {
		initComponents(uiRoot);
		initLayout();
	}

	private void initComponents(Pane uiRoot) {
		slCurrentOne = new Slider();
		slCurrentTwo = new Slider();

		btChangArrowOne = new Button("Chang arrawOne");
		btChangArrowTwo = new Button("Chang arrawTwo");
		showCalculus =  new Button("Calculate");

		lbCurrentDistanceOne = new Label();
		lbCurrentDistanceOne.setFont(FONT);

		lbCurrentDistanceTwo = new Label();
		lbCurrentDistanceTwo.setFont(FONT);

		lbCurrentDistanceOne = new Label();
		lbCurrentDistanceOne.setFont(FONT);

		lbCurrentOne = new Label();
		lbCurrentOne.setFont(FONT);

		lbCurrentTwo = new Label();
		lbCurrentTwo.setFont(FONT);

		uiRoot.getChildren().addAll(slCurrentOne, slCurrentTwo, btChangArrowOne, btChangArrowTwo, lbCurrentDistanceOne,
				showCalculus, lbCurrentDistanceTwo, lbCurrentOne, lbCurrentTwo);
	}

	private void initLayout() {
		intSliderRange();
		initHandlers();

		slCurrentOne.setLayoutX(150);
		slCurrentOne.setLayoutY(20);

		slCurrentTwo.setLayoutX(150);
		slCurrentTwo.setLayoutY(80);

		btChangArrowOne.setLayoutX(10);
		btChangArrowOne.setLayoutY(20);

		btChangArrowTwo.setLayoutX(10);
		btChangArrowTwo.setLayoutY(70);
		
		showCalculus.setLayoutX(300);
		showCalculus.setLayoutY(50);

		lbCurrentOne.setLayoutX(300);
		lbCurrentOne.setLayoutY(80);

		lbCurrentTwo.setLayoutX(300);
		lbCurrentTwo.setLayoutY(20);

		lbCurrentDistanceOne.setLayoutX(400);
		lbCurrentDistanceOne.setLayoutY(80);

		lbCurrentDistanceTwo.setLayoutX(400);
		lbCurrentDistanceTwo.setLayoutY(20);

	}

	private void initHandlers() {
		btChangArrowOne.setOnAction(event -> {
			changArrowOne = changArrowOne ? false : true;
		});

		btChangArrowTwo.setOnAction(event -> {
			changArrowTwo = changArrowTwo ? false : true;
		});
		

		showCalculus.setOnAction(event -> {
			calculate = calculate ? false : true;
		});

	}

	private void intSliderRange() {
		slCurrentOne.setMin(0);
		slCurrentOne.setMax(10);

		slCurrentTwo.setMin(0);
		slCurrentTwo.setMax(10);
	}

	public boolean isChangArrowOneEnable() {
		return changArrowOne;
	}

	public boolean isChangArrowTwoEnable() {
		return changArrowTwo;
	}
	
	public boolean isCalculateEnable() {
		return calculate;
	}

	public double getSlCurrentOneValue() {
		return this.slCurrentOne.getValue();
	}

	public double getSlCurrentTwoValue() {
		return this.slCurrentTwo.getValue();
	}

	public void setLbCurrentDistanceOne(Double distance) {
		lbCurrentDistanceOne.setText("Distance between dot and wire one: " + String.format("%.2f", distance) + "cm");
	}

	public void setLbCurrentDistanceTwo(Double distance) {
		lbCurrentDistanceTwo.setText("Distance between dot and wire two: " + String.format("%.2f", distance) + "cm");
	}

	public void setLbCurrentOne(Double distance) {
		lbCurrentOne.setText("i1 = " + distance.intValue() + " A");
	}

	public void setLbCurrentTwo(Double distance) {
		lbCurrentTwo.setText("i2 = " + distance.intValue() + " A ");
	}

	public void setPositionCurrentOne(double x, double y) {
		lbCurrentOne.setLayoutX(x);
		lbCurrentOne.setLayoutY(y);
	}

	public void setPositionCurrentTwo(double x, double y) {
		lbCurrentTwo.setLayoutX(x);
		lbCurrentTwo.setLayoutY(y);
	}

	public void setCalculate(boolean value) {
		this.calculate = value;
	}
}

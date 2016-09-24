package javafx.physics;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Menu extends Application {

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

	private Set<Node> components;
	private AnchorPane anchorPane;

	public Menu() {

	}

	private void addAll() {
		for (Field field : this.getClass().getDeclaredFields()) {
			Type type = field.getGenericType();
			if (type.equals(Node.getClassCssMetaData()))

				System.out.println(type);
			if (type instanceof Node) {

			}
		}
	}

	private void initComponents() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		launch(args);
		// System.out.println(menu.components.size());
		// menu.addAll();
		// System.out.println(menu.components.size());

		System.out.println("End");
	}

	@Override
	public void start(Stage arg0) throws Exception {
		components = new HashSet<>();
		System.out.println(components.size());
		slider = new Slider();

		addAll();

		System.out.println(components.size());

	}

}

package javafx.physics;

import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Point2D;

public class Util {

	public static double distanceOf(Point2D position, Point2D otherPosition) {
		return position.distance(otherPosition);
	}

	public static double distanceOf(PhysicsEntity entity, PhysicsEntity otherEntity) {
		double posX = entity.getPosition().getX();
		double posY = entity.getPosition().getY();

		double posXEntity = otherEntity.getPosition().getX();
		double posYEntity = otherEntity.getPosition().getY();

		double lowestDistance = distance(posX, posXEntity, posY, posYEntity);
		double lowestX = posX;
		double lowestY = posY;

		for (int x = (int) posX; x <= posX + entity.getWidth(); x++) {
			for (int y = (int) posY; y <= posY + entity.getHeight(); y++) {
				double distance = distance(x, posXEntity, y, posYEntity);
				if (distance < lowestDistance) {
					lowestDistance = distance;
					lowestX = x;
					lowestY = y;
				}
			}

		}

		for (int x = (int) posXEntity; x <= posXEntity + otherEntity.getWidth(); x++) {
			for (int y = (int) posYEntity; y <= posYEntity + otherEntity.getHeight(); y++) {
				double distance = distance(x, lowestX, y, lowestY);
				lowestDistance = distance < lowestDistance ? distance : lowestDistance;
			}

		}

		return lowestDistance;
	}

	private static double distance(double x1, double x2, double y1, double y2) {
		double a = x1 - x2;
		double b = y1 - y2;
		return Math.sqrt(a * a + b * b);
	}

	public static String superscript(String str) {
		str = str.replaceAll("0", "⁰");
		str = str.replaceAll("1", "¹");
		str = str.replaceAll("2", "²");
		str = str.replaceAll("3", "³");
		str = str.replaceAll("4", "⁴");
		str = str.replaceAll("5", "⁵");
		str = str.replaceAll("6", "⁶");
		str = str.replaceAll("7", "⁷");
		str = str.replaceAll("8", "⁸");
		str = str.replaceAll("9", "⁹");
		str = str.replaceAll("-", "⁻");

		return str;
	}

	public static String subscript(String str) {
		str = str.replaceAll("0", "₀");
		str = str.replaceAll("1", "₁");
		str = str.replaceAll("2", "₂");
		str = str.replaceAll("3", "₃");
		str = str.replaceAll("4", "₄");
		str = str.replaceAll("5", "₅");
		str = str.replaceAll("6", "₆");
		str = str.replaceAll("7", "₇");
		str = str.replaceAll("8", "₈");
		str = str.replaceAll("9", "₉");
		return str;
	}

}

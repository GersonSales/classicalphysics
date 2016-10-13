package javafx.physics3;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class Arrow {

	private Entity entity;

	public Arrow(Double posX, Double posY, Types type, Texture texture) {

		this.entity = new Entity(type);
		entity.setPosition(posX, posY);
		entity.setGraphics(texture);
	}

	public Entity getEntity() {
		return this.entity;
	}

	public double getHeight() {
		return getEntity().getHeight();
	}

	public Bounds getBoundsInParent() {
		return getEntity().getBoundsInParent();
	}

	public Point2D getPosition() {
		return getEntity().getPosition();
	}

	public boolean intersects(PhysicsEntity otherEntity) {
		return getEntity().getBoundsInParent().intersects(otherEntity.getBoundsInParent());
	}

	public double getWidth() {
		return getEntity().getWidth();
	}

	public void setTexture(Texture texture) {
		entity.setGraphics(texture);
	}

}

package javafx.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.asset.Assets;
import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.sun.corba.se.impl.logging.POASystemException;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class Plate {
	private PhysicsEntity entity;

	public Plate(double posX, double posY, Texture texture) {
		this.entity = new PhysicsEntity(Type.WALL);
		entity.setPosition(posX, posY);
		entity.setBodyType(BodyType.KINEMATIC);
		entity.setGraphics(texture);

	}

	public PhysicsEntity getEntity() {
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

	public double distanceOf(PhysicsEntity otherEntity) {
		return Util.distanceOf(getEntity(), otherEntity);
	}

}

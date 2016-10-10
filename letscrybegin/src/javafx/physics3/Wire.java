package javafx.physics3;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.physics.Util;

public class Wire {
	
	private PhysicsEntity entity;
	private BodyDef bodyDef;
	
	public Wire(Double posX, Double posY, Types type, Texture texture) {
		this.entity = new PhysicsEntity(type);
		entity.setPosition(posX,  posY);
		entity.setBodyType(BodyType.DYNAMIC);
		entity.setGraphics(texture);
		initBodyType();
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
		return getEntity().getBoundsInParent()
				.intersects(otherEntity.getBoundsInParent());
	}

	public double getWidth() {
		return getEntity().getWidth();
	}

	public double distanceOf(PhysicsEntity otherEntity) {
		return Util.distanceOf(getEntity(), otherEntity);
	}

	private void initBodyType() {
		bodyDef = new BodyDef();
		bodyDef.type =  BodyType.DYNAMIC;
	}
	
}

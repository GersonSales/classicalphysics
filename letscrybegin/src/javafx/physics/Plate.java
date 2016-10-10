package javafx.physics;

import org.jbox2d.dynamics.BodyType;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsEntity;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

public class Plate {
	private PhysicsEntity entity;
	private Entity field;
	private PlateCharge charge;
	private double posX;
	private double posY;

	public Plate(double posX, double posY, Types type) {
		charge = type.equals(Types.POSITIVE_PLATE) ? new PositivePlate() : new NegativePlate();

		this.entity = new PhysicsEntity(type);
		entity.setPosition(posX, posY);
		entity.setBodyType(BodyType.STATIC);
		entity.setGraphics(charge.getTexture());

		this.posX = posX;
		this.posY = posY;

		initField(1);

	}

	private void initField(double opacity) {
		field = new Entity(Types.FIELD);
		field.setOpacity(opacity);
		field.setGraphics(charge.getField());

		field.setPosition(posX, posY - (field.getHeight() / 2.35));

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

	public Entity getField() {
		return this.field;
	}

	public Entity[] getEntities() {
		return new Entity[] { getEntity(), getField() };

	}

	public void setFieldStrength(double value) {
		field.setOpacity(value / 40.);

	}

}

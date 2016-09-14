package javafx.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.physics.PhysicsManager;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class Ball {

	private PhysicsEntity entity;
	private Texture texture;
	private FixtureDef fixtureDef;
	private BodyDef bodyDef;

	private Double radius;
	public Charge charge;

	public Ball(Texture texture, Double radius, Type charge) {
		if (charge.equals(Type.POSITIVE)) {
			this.charge = new Positive();
		} else if (charge.equals(Type.NEGATIVE)) {
			this.charge = new Negative();

		}
		this.texture = this.charge.getTexture();
		this.radius = radius;
		initentity();
	}

	private void initentity() {
		initFixtureDef();

		initTexture();

		initBodyType();

		entity = new PhysicsEntity(Type.BALL);
		entity.setPosition(590, 173);
		entity.setGraphics(texture);
		entity.setFixtureDef(fixtureDef);
		entity.setBodyDef(bodyDef);
	}

	private void initBodyType() {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
	}

	private void initTexture() {
		texture.setFitWidth(radius);
		texture.setFitHeight(radius);
	}

	private void initFixtureDef() {
		double colisionLimit = radius * 0.4;
		fixtureDef = new FixtureDef();
		fixtureDef.restitution = .5f;
		fixtureDef.density = .5f;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(PhysicsManager.toMeters(colisionLimit));
	}

	public Entity getEntity() {
		return this.entity;
	}

	public Point2D getPosition() {
		return getEntity().getPosition();
	}

	public Bounds getBoundsInParent() {
		return getEntity().getBoundsInParent();
	}

	public void setLinearVelocity(Point2D point2d) {
		entity.setLinearVelocity(point2d);
	}

	public void changeCharge() {
		if (this.charge instanceof Negative) {
			this.charge = new Positive();
		} else {
			this.charge = new Negative();
		}

		entity.setGraphics(charge.getTexture());
	}

	public void setPosition(int posX, int posY) {
		entity.setPosition(590, 173);

	}

	@Override
	public String toString() {
		return charge.getClass().toString();
	}

}

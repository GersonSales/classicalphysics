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
import javafx.scene.image.ImageView;

public class Ball {

	private PhysicsEntity entity;
	private Texture texture;

	private Double radius;

	public Ball(Texture texture, Double radius) {
		this.texture = texture;
		this.radius = radius;
		initentity();
	}

	private void initentity() {
		double colisionLimit = radius * 0.4;

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.restitution = .5f;
		fixtureDef.density = .5f;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(PhysicsManager.toMeters(colisionLimit));

		texture.setFitWidth(radius);
		texture.setFitHeight(radius);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;

		entity = new PhysicsEntity(Type.BALL);
		entity.setPosition(590, 173);
		entity.setGraphics(texture);
		entity.setFixtureDef(fixtureDef);
		entity.setBodyDef(bodyDef);

		// new Point2D(0, 170).subtract(entity.getPosition()).multiply(.1));

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

}

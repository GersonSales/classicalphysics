package javafx.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.physics.PhysicsManager;

import javafx.geometry.Point2D;
import javafx.rainball.Utils;
import javafx.scene.paint.Color;

public class PhysicalBalls {

	private PhysicsEntity ball;

	private float posX;
	private float posY;

	private float radius;

	private BodyType bodyType;
	private Texture ballTexture;

	public PhysicalBalls(float posX, float posY) {
		this(posX, posY, Utils.BALL_SIZE, BodyType.DYNAMIC, Color.RED);
	}

	public PhysicalBalls(float posX, float posY, int radius, BodyType bodyType,
			Color color) {
		this.posX = posX;
		this.posY = posY;
		this.radius = radius;
		this.bodyType = bodyType;
		create();
	}

	private void create() {
		double collisionLimit = radius * 0.4;

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.restitution = .5f;
		fixtureDef.density = .5f;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(PhysicsManager.toMeters(collisionLimit));

		ballTexture.setFitWidth(radius);
		ballTexture.setFitHeight(radius);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bodyType;

		ball = new PhysicsEntity(Type.BALL);
		ball.setPosition(posX, posY);
		ball.setGraphics(ballTexture);
		ball.setFixtureDef(fixtureDef);
		ball.setBodyDef(bodyDef);

		ball.setLinearVelocity(
				new Point2D(0, 0).subtract(ball.getPosition()).multiply(.01));
	}

}

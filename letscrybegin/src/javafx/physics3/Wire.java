package javafx.physics3;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.physics.PhysicsManager;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.physics.Util;

public class Wire {

	private PhysicsEntity entity;
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private Texture texture;
	private Types type;
	private Double posX;
	private Double posY;

	public Wire(Double posX, Double posY, Types type, Texture texture) {

		this.texture = texture;
		this.type = type;
		this.posX = posX;
		this.posY = posY;
		initEntity();

	}

	private void initEntity() {
		initFixtureDef();


		initBodyType();
		initTexture();

		this.entity = new PhysicsEntity(Types.WIRE);
		entity.setPosition(posX, posY);
		entity.setGraphics(texture);

		entity.setFixtureDef(fixtureDef);
		entity.setBodyDef(bodyDef);
	}

	private void initTexture() {
		texture.setFitWidth(0);
		texture.setFitHeight(0);
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

	private void initBodyType() {
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
	}

	private void initFixtureDef() {
		fixtureDef = new FixtureDef();
//		fixtureDef.restitution = .01f;
		//fixtureDef.density = .5f;
		fixtureDef.shape = new CircleShape();
		fixtureDef.shape.setRadius(PhysicsManager.toMeters(15));
		texture.setFitHeight(60);
		texture.setFitWidth(60);

	}

}

package javafx.physics;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import com.almasb.fxgl.asset.Texture;
import com.almasb.fxgl.physics.PhysicsEntity;
import com.almasb.fxgl.physics.PhysicsManager;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class Ball {

	private static final Double BALL_MASS = 10.0;
	private PhysicsEntity entity;
	private Texture texture;
	private FixtureDef fixtureDef;
	private BodyDef bodyDef;

	private Double radius;
	private Charge charge;
	private Double mass;
	private double posX;
	private double posY;

	private double chargeValue;

	public Ball(Double radius, double posX, double posY) {
		this.posX = posX;
		this.posY = posY;
		this.charge = new Positive();
		this.texture = this.charge.getTexture();
		this.radius = radius;
		this.mass = BALL_MASS;
		initEntity();

	}

	public boolean isPositive() {
		return charge instanceof Positive;
	}

	public boolean isNegative() {
		return charge instanceof Negative;
	}

	public double getRadius() {
		return this.radius;
	}

	protected void initEntity() {
		initFixtureDef();

		initTexture();

		initBodyType();

		entity = new PhysicsEntity(Types.BALL);
		entity.setPosition(posX, posY);
		entity.setGraphics(texture);

		entity.setFixtureDef(fixtureDef);
		entity.setBodyDef(bodyDef);

	}

	public void disableRotate() {
		getEntity().setRotate(0);
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

	public PhysicsEntity getEntity() {
		return this.entity;
	}

	public Point2D getPosition() {
		return getEntity().getPosition();
	}

	public Bounds getBoundsInParent() {
		return getEntity().getBoundsInParent();
	}

	public Double getMass() {
		return this.mass;
	}

	public void setMass(Double mass) {
		this.mass = mass;
	}

	public void setLinearVelocity(Point2D point2d) {
		entity.setLinearVelocity(point2d);
	}

	public void increaseMass(Double mass) {
		this.mass += mass;
	}

	public void decreaseMass(Double mass) {
		this.mass -= mass;
	}

	public void changeCharge() {
		if (this.charge instanceof Negative) {
			this.charge = new Positive();
			this.chargeValue = 10;
		} else {
			this.charge = new Negative();
			this.chargeValue = -10;

		}

		texture.setImage(charge.getTexture().getImage());
		initTexture();
		entity.setGraphics(texture);
	}

	public double getChargeValue() {
		return this.chargeValue;
	}

	public void setChargeValue(double charge) {
		this.chargeValue = charge;
	}

	public boolean intersects(PhysicsEntity otherEntity) {
		return getEntity().getBoundsInParent().intersects(otherEntity.getBoundsInParent());
	}

	public double distanceOf(PhysicsEntity otherEntity) {
		return Util.distanceOf(getEntity(), otherEntity);
	}

	public void setPosition(int posX, int posY) {
		entity.setPosition(590, 173);

	}

	@Override
	public String toString() {
		return charge.getClass().toString();
	}

	public Point2D getCenter() {
		return new Point2D(getPosition().getX() + getWidth() / 2, getPosition().getY() + getHeight() / 2);
	}

	private double getHeight() {
		return getEntity().getHeight();
	}

	private double getWidth() {
		return getEntity().getWidth();
	}

}

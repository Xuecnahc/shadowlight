package game.shadowlight.entities.levelObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box {

	protected Body body;
	protected Fixture fixture;
	protected final float WIDTH, HEIGHT;

	public Box(World world, float x, float y, float width, float height) {
		this(world, x, y , width, height, null);
	}
	
	public Box(World world, float x, float y, float width, float height, FixtureDef fixtureDef) {
		WIDTH = width;
		HEIGHT = height;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		
		if(fixtureDef == null) {
			fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.restitution = 0f;
			fixtureDef.friction = 0.4f;
			fixtureDef.density = 3;
		}
		
		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
	}

	public float getRestitution() {
		return fixture.getRestitution();
	}

	public void setRestitution(float restitution) {
		fixture.setRestitution(restitution);
	}

	public Body getBody() {
		return body;
	}

	public Fixture getFixture() {
		return fixture;
	}

}

package game.shadowlight.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import game.shadowlight.jobs.Job;

public abstract class Adventurer extends InputAdapter implements ContactFilter, ContactListener {

	protected Body body;
	protected Fixture fixture;
	protected final float WIDTH, HEIGHT;
	protected Vector2 velocity = new Vector2();
	protected float jumpPower = 20, speed = 500, maxSpeed = 8, maxJump = 2, nbJump = 1;
	protected Job job;

	public Adventurer(World world, float x, float y, float width) {
		WIDTH = width;
		HEIGHT = width * 2;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(x, y);
		bodyDef.fixedRotation = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, HEIGHT / 2);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.restitution = 0;
		fixtureDef.friction = 0.5f;
		fixtureDef.density = 1f;

		body = world.createBody(bodyDef);
		fixture = body.createFixture(fixtureDef);
	}

	public void update() {
		float xSpeed = (body.getLinearVelocity().x>=maxSpeed || body.getLinearVelocity().x<=-maxSpeed)?0:velocity.x;
		body.applyForceToCenter(xSpeed, velocity.y, true);
	}

	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		return true;
	}

	@Override
	public void beginContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		if(contact.getFixtureA() == fixture || contact.getFixtureB() == fixture)
			if(contact.getWorldManifold().getPoints()[0].y <= body.getPosition().y - HEIGHT / 2) {
				nbJump = 0;
				//body.applyLinearImpulse(0, jumpPower, body.getWorldCenter().x, body.getWorldCenter().y, true);
			}
	}

	@Override
	public void endContact(Contact contact) {
	}
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode) {
		// Moves
		case Keys.LEFT:
				velocity.x = -speed;
			break;
		case Keys.RIGHT:
				velocity.x = speed;
			break;
		// Jump
		case Keys.SPACE:
			body.applyLinearImpulse(0, jumpPower, body.getWorldCenter().x, body.getWorldCenter().y, true);
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.LEFT || keycode == Keys.RIGHT) {
			float xLinearVelocity = body.getLinearVelocity().x;
			boolean isLeft = (keycode == Keys.LEFT);
			// Catch if the move is in the same direction than the key up event
			boolean isRightDirection = (xLinearVelocity < 0 && isLeft) || (xLinearVelocity > 0 && !isLeft);
			// Apply an impulse to slow down the move
			velocity.x = 0;
			if(isRightDirection) {
				float xSpeed = speed/100*(isLeft ? 1 : -1);
				if(Math.abs(xSpeed) < Math.abs(xLinearVelocity) && Math.abs(xLinearVelocity + xSpeed) < Math.abs(xLinearVelocity)) {
					body.applyLinearImpulse(xSpeed, 0, body.getWorldCenter().x, body.getWorldCenter().y, true);
				}
				return true;
			}
		}
		return false;
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

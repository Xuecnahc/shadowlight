package game.shadowlight.entities.levelObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.world.PlayWorld;

/**
 * Abstract class to create a {@link BodyType#DynamicBody DynamicBody}.
 * 
 * @author Zulrage
 *
 */
public abstract class MovableObject {

  protected Body body;
  protected Fixture fixture;
  protected float width;
  protected float height;
  protected float x;
  protected float y;
  protected PlayWorld world;

  public MovableObject(float x, float y, float width, float height) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
    

  }

  public void setWorld(PlayWorld world) {
    this.world = world;
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.DynamicBody;
    bodyDef.position.set(x, y);
    bodyDef.fixedRotation = true;

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(width / 2, height / 2);

    body = world.getWorld().createBody(bodyDef);
    body.setUserData(getUserData());
    fixture = body.createFixture(getFixtureDef(shape));
  }

  protected abstract FixtureDef getFixtureDef(Shape shape);

  protected abstract GenericUserData getUserData();

  public Body getBody() {
    return body;
  }

  public Fixture getFixture() {
    return this.fixture;
  }

}

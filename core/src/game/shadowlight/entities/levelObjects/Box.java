package game.shadowlight.entities.levelObjects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box {
  private Body body;
  private Fixture fixture;
  private float width;
  private float height;
  private float x;
  private float y;

  public Box(float x, float y, float width, float height) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
  }
 
  public Box(World world, float x, float y, float width, float height) {
    this.width = width;
    this.height = height;
    this.x = x;
    this.y = y;
    
    this.setWorld(world);
  }

  public void setWorld(World world) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.StaticBody;
    bodyDef.position.set(this.x, this.y);
    bodyDef.fixedRotation = true;

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(this.width / 2, this.height / 2);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.restitution = 0f;
    fixtureDef.friction = 0.4f;
    fixtureDef.density = 3;
    this.body = world.createBody(bodyDef);
    this.fixture = this.body.createFixture(fixtureDef);
  }

  public float getRestitution() {
    return this.fixture.getRestitution();
  }

  public void setRestitution(float restitution) {
    this.fixture.setRestitution(restitution);
  }

  public Body getBody() {
    return this.body;
  }

  public Fixture getFixture() {
    return this.fixture;
  }
  
  @Override
  public String toString() {
    return "Box [width: " + this.width + "height" + this.height + "x" + this.x + "y" + this.y;
  }

}

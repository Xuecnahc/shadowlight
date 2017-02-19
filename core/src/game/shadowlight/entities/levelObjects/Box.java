package game.shadowlight.entities.levelObjects;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

import game.shadowlight.entities.collidable.BoxCollisionReaction;
import game.shadowlight.entities.type.DefensiveProperties;
import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.entities.type.OffensiveProperties;
import game.shadowlight.utils.EnumUserDataId;

public class Box extends MovableObject {

  public Box(float x, float y, float width, float height) {
    super(x, y, width, height);
  }

  @Override
  protected FixtureDef getFixtureDef(Shape shape) {
    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.restitution = 0f;
    fixtureDef.friction = 1f;
    fixtureDef.density = 5;
    return fixtureDef;
  }

  @Override
  protected GenericUserData getUserData() {
    return new GenericUserData(EnumUserDataId.BOX, null, new OffensiveProperties(),
        new DefensiveProperties(true, 3, 1000), new BoxCollisionReaction());
  }

  @Override
  public String toString() {
    return "Box [width: " + this.width + "height" + this.height + "x" + this.x + "y" + this.y;
  }

}

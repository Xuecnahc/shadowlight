package game.shadowlight.entities.levelObjects;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import game.shadowlight.entities.collidable.BoxCollisionReaction;
import game.shadowlight.entities.type.EntityDestructable;
import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.EnumUserDataId;

public class Box extends MovableObject {

  public Box(World world, float x, float y, float width, float height) {
    super(world, x, y, width, height);
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
    return new GenericUserData(EnumUserDataId.BOX, null, new EntityDestructable(true, 10, 1),
        new BoxCollisionReaction());
  }

  @Override
  public String toString() {
    return "Box [width: " + this.width + "height" + this.height + "x" + this.x + "y" + this.y;
  }

}

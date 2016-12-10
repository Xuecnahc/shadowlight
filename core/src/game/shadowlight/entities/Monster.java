package game.shadowlight.entities;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;

import game.shadowlight.entities.collidable.BoxCollisionReaction;
import game.shadowlight.entities.levelObjects.MovableObject;
import game.shadowlight.entities.type.DefensiveProperties;
import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.entities.type.IObserver;
import game.shadowlight.entities.type.OffensiveProperties;
import game.shadowlight.utils.EnumUserDataId;
import game.shadowlight.world.PlayWorld;

public class Monster extends MovableObject implements IObserver {

  protected boolean faceRight;
  protected boolean alertState = false;
  protected float visibilityRangeFront = 10, visibilityRangeBack = 0;
  protected float alertVisibilityRangeFront = 15, alertVisibilityRangeBack = 2;
  protected long timeBetweenAttacks = 1000;

  public Monster(float x, float y, float width, float height, boolean faceRight) {
    super(x, y, width, height);
    this.faceRight = faceRight;
  }

  public Monster(float x, float y, float width, float height, boolean faceRight, boolean alertState) {
    super(x, y, width, height);
    this.faceRight = faceRight;
    this.alertState = alertState;
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
  public void setWorld(PlayWorld world) {
    super.setWorld(world);
    world.getObservers().add(this);
  }

  @Override
  protected GenericUserData getUserData() {
    return new GenericUserData(EnumUserDataId.BOX, null, new OffensiveProperties(),
        new DefensiveProperties(true, 3, 1000), new BoxCollisionReaction());
  }

  @Override
  public String toString() {
    return "Monster [faceRight=" + faceRight + ", alertState=" + alertState + ", visibilityRangeFront="
        + visibilityRangeFront + ", visibilityRangeBack=" + visibilityRangeBack + ", alertVisibilityRangeFront="
        + alertVisibilityRangeFront + ", alertVisibilityRangeBack=" + alertVisibilityRangeBack + ", timeBetweenAttacks="
        + timeBetweenAttacks + "]";
  }

  @Override
  public void move() {
    float xSpeed = (body.getLinearVelocity().x >= maxSpeed || body.getLinearVelocity().x <= -maxSpeed) ? 0 : velocity.x;
    body.applyForceToCenter(xSpeed, velocity.y, true);
  }

  @Override
  public void observe() {
    if (seePlayer()) {
      alertState = true;
    }
  }

  private boolean seePlayer() {
    for (Adventurer adventurer : world.getPlayers()) {
      float playerPosition = adventurer.getBody().getPosition().x;
      float visibilityFront = alertState ? alertVisibilityRangeFront : visibilityRangeFront;
      float visibilityBack = alertState ? alertVisibilityRangeBack : visibilityRangeBack;
      if (playerPosition > body.getPosition().x
          && playerPosition < body.getPosition().x + (faceRight ? visibilityFront : visibilityBack)) {
        return true;
      } else if (playerPosition <= body.getPosition().x
          && playerPosition >= body.getPosition().x - (!faceRight ? visibilityFront : visibilityBack)) {
        return true;
      }
    }
    return false;
  }
}

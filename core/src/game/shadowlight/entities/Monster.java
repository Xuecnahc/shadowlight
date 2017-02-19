package game.shadowlight.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.TimeUtils;

import game.shadowlight.entities.collidable.BoxCollisionReaction;
import game.shadowlight.entities.levelObjects.MovableObject;
import game.shadowlight.entities.type.DefensiveProperties;
import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.entities.type.IMovable;
import game.shadowlight.entities.type.IObserver;
import game.shadowlight.entities.type.OffensiveProperties;
import game.shadowlight.utils.Direction;
import game.shadowlight.utils.EnumUserDataId;
import game.shadowlight.weapons.Gourdin;
import game.shadowlight.weapons.WeaponEntity;
import game.shadowlight.world.PlayWorld;

public class Monster extends MovableObject implements IObserver, IMovable {

  // Visibility properties
  protected Direction direction;
  protected boolean alertState = false;
  protected float visibilityRangeFront = 5;
  protected float visibilityRangeBack = 0;
  protected float alertVisibilityRangeFront = 10;
  protected float alertVisibilityRangeBack = 2;

  protected boolean isAttacking = false;

  // Move properties
  protected Vector2 velocity = new Vector2();
  protected float jumpPower = 20, speed = 200, maxSpeed = 4, maxJump = 2, nbJump = 1;

  protected WeaponEntity weapon;

  public Monster(float x, float y, float width, float height, Direction direction) {
    this(x, y, width, height, direction, false);
  }

  public Monster(float x, float y, float width, float height, Direction direction, boolean alertState) {
    super(x, y, width, height);
    this.direction = direction;
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
    world.getMovable().add(this);
    this.weapon = new Gourdin(world, true);
  }

  @Override
  protected GenericUserData getUserData() {
    return new GenericUserData(EnumUserDataId.BOX, null, new OffensiveProperties(),
        new DefensiveProperties(true, 3, 1000), new BoxCollisionReaction());
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
    float visibilityFront = alertState ? alertVisibilityRangeFront : visibilityRangeFront;
    float visibilityBack = alertState ? alertVisibilityRangeBack : visibilityRangeBack;
    for (Adventurer adventurer : world.getPlayers()) {
      float playerPosition = adventurer.getBody().getPosition().x;
      if (playerPosition > body.getPosition().x && playerPosition < body.getPosition().x
          + (direction == Direction.RIGHT ? visibilityFront : visibilityBack)) {
        moveAndAct(true, playerPosition - body.getPosition().x);
        return true;
      } else if (playerPosition <= body.getPosition().x && playerPosition >= body.getPosition().x
          - (direction == Direction.LEFT ? visibilityFront : visibilityBack)) {
        moveAndAct(false, body.getPosition().x - playerPosition);
        return true;
      }
    }
    velocity.x = 0;
    return false;
  }

  private void moveAndAct(boolean moveRight, float absoluteDistance) {
    if (isAttacking) {
      // During an attack
      if (TimeUtils.millis() <= weapon.getLastAttackTime() + ((long) weapon.getBlockingTime() * 1000)) {
        // During animation
        return;
      }

      isAttacking = false;
    } else {
      if (weapon.getRange() > absoluteDistance) {
        // Can attack
        isAttacking = true;
        velocity.x = 0;
        weapon.attack(this.body, this.width, this.height, moveRight ? Direction.RIGHT : Direction.LEFT);
      } else {
        velocity.x = speed * (moveRight ? 1 : -1);
        direction = moveRight ? Direction.RIGHT : Direction.LEFT;
      }
    }
  }

}

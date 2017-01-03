package game.shadowlight.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;

import game.shadowlight.entities.collidable.WeaponCollisionReaction;
import game.shadowlight.entities.type.DefensiveProperties;
import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.entities.type.OffensiveProperties;
import game.shadowlight.utils.Direction;
import game.shadowlight.utils.EnumUserDataId;

public abstract class WeaponEntity {
  protected World world;

  protected Vector2 position;
  protected Vector2 size;
  protected boolean isAlly;
  protected GenericUserData userData;
  protected FixtureDef fixtureDef;

  protected float expectedRange;
  protected float maxRange;

  protected long cooldown;
  protected long lastAttackTime = 0;

  public WeaponEntity(World world) {
    this(world, false);
  }

  public WeaponEntity(World world, boolean isAlly) {
    this.cooldown = this.getAttackCooldown();
    this.world = world;
    this.size = this.initSize();
    this.isAlly = isAlly;
    WeaponCollisionReaction collisionReaction = new WeaponCollisionReaction();
    collisionReaction.setIsAlly(this.isAlly);
    this.userData = new GenericUserData(EnumUserDataId.WEAPON, null, new OffensiveProperties(this.getDamages()),
        new DefensiveProperties(), collisionReaction);
    this.fixtureDef = initFixtureDef();
    this.maxRange = this.getMaxRange();
    this.expectedRange = this.getRange();
  }

  public void attack(Body attackerBody, float attackerWidth, float attackerHeight, Direction direction) {
    long currentAttackTime = TimeUtils.millis();
    if (currentAttackTime <= this.lastAttackTime + cooldown) {
      return;
    }
    this.lastAttackTime = currentAttackTime;

    final Vector2 anchorPosition = this.getAnchorPosition(attackerWidth, attackerHeight, direction);
    final Vector2 attackerPos = attackerBody.getPosition();
    final Vector2 position = new Vector2(attackerPos.x + anchorPosition.x, attackerPos.y + anchorPosition.y);
    final Body weaponBody = this.world.createBody(this.initBodyDef(position));
    weaponBody.createFixture(this.fixtureDef);
    weaponBody.setUserData(this.userData);

    this.doAttackMove(weaponBody, direction, attackerBody);
  }

  protected Vector2 getAnchorPosition(float attackerWidth, float attackerHeight, Direction direction) {
    switch (direction) {
      case LEFT:
        return new Vector2(attackerWidth / 2, -attackerHeight / 2);
      case RIGHT:
        return new Vector2(-attackerWidth / 2, -attackerHeight / 2);
      case UP:
        return new Vector2(0, -attackerHeight);
      case DOWN:
        return new Vector2(0, attackerHeight);
      default:
        return new Vector2(0, 0);
    }
  }

  protected BodyDef initBodyDef(Vector2 position) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyType.DynamicBody;
    bodyDef.position.set(position);
    bodyDef.fixedRotation = false;
    return bodyDef;
  }

  protected FixtureDef initFixtureDef() {
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(this.size.x, this.size.y);

    FixtureDef fixtureDef = new FixtureDef();
    fixtureDef.shape = shape;
    fixtureDef.restitution = 0;
    fixtureDef.friction = 0.5f;
    fixtureDef.density = 1f;
    return fixtureDef;
  }

  protected abstract long getAttackCooldown();

  protected abstract Vector2 initSize();

  protected abstract int getDamages();

  protected abstract float getMaxRange();

  protected abstract float getRange();

  protected abstract void doAttackMove(Body body, Direction direction, Body attackerBody);

}
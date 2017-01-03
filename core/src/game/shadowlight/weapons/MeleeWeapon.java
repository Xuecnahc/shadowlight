package game.shadowlight.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.Direction;

public abstract class MeleeWeapon extends WeaponEntity {

  protected float appearTime;

  public MeleeWeapon(World world) {
    this(world, false);
  }

  public MeleeWeapon(World world, boolean isAlly) {
    super(world, isAlly);
    this.appearTime = this.getAppearTime();
  }

  protected RevoluteJointDef createJoin(Body attackerBody, Body weaponBody, Vector2 anchorPos) {
    RevoluteJointDef joinDef = new RevoluteJointDef();
    joinDef.bodyA = attackerBody;
    joinDef.bodyB = weaponBody;
    joinDef.collideConnected = false;
    joinDef.localAnchorA.set(0, 0);
    joinDef.localAnchorB.set(anchorPos.x, anchorPos.y);
    joinDef.enableMotor = true;
    joinDef.motorSpeed = 0;
    joinDef.maxMotorTorque = 1f;
    joinDef.enableLimit = true;
    
    if (anchorPos.x > 0) {
      joinDef.lowerAngle = 0.5f;
      joinDef.upperAngle = 1; 
    } else {
      joinDef.lowerAngle = -1;
      joinDef.upperAngle = -0.5f; 
    }
    

    this.world.createJoint(joinDef);
    return joinDef;
  }
  
  @Override
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
    this.createJoin(attackerBody, weaponBody, anchorPosition);

    this.doAttackMove(weaponBody, direction, attackerBody);
  }

  @Override
  protected void doAttackMove(final Body body, Direction direction, Body attackerBody) {
    body.applyAngularImpulse(this.getAngularImpulse(direction), false);
    final GenericUserData userData = (GenericUserData) body.getUserData();
    Timer.schedule(new Task() {

      @Override
      public void run() {
        world.destroyBody(body);
        userData.setDestroyable(true);
      }
    }, this.getAppearTime());
  }

  @Override
  protected float getMaxRange() {
    return this.size.y;
  }

  @Override
  protected float getRange() {
    return this.size.y;
  }

  protected abstract float getAppearTime();

  protected float getAngularImpulse(Direction direction) {
    switch (direction) {
      case RIGHT:
        return -1;
      case LEFT:
      case UP:
      case DOWN:
      default:
        return 1;
    }
  }

}

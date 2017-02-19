package game.shadowlight.weapons;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.Direction;

public abstract class RangedWeapon extends WeaponEntity {

  protected float appearTime;

  public RangedWeapon(World world) {
    this(world, false);
  }

  public RangedWeapon(World world, boolean isAlly) {
    super(world, isAlly);
    this.appearTime = this.getAppearTime();
  }

  @Override
  public void attack(Body attackerBody, float attackerWidth, float attackerHeight, Direction direction) {
    long currentAttackTime = TimeUtils.millis();
    if (currentAttackTime <= this.lastAttackTime + cooldown) {
      return;
    }
    this.lastAttackTime = currentAttackTime;

    final Body weaponBody = this.world.createBody(this.initBodyDef(attackerBody.getPosition()));
    weaponBody.createFixture(this.fixtureDef);
    weaponBody.setUserData(this.userData);

    this.doAttackMove(weaponBody, direction, attackerBody);
  }

  @Override
  protected void doAttackMove(final Body body, Direction direction, Body attackerBody) {
    body.applyLinearImpulse(this.getImpulse(direction), 0, body.getPosition().x, body.getPosition().y, false);
    final GenericUserData userData = (GenericUserData) body.getUserData();
    Timer.schedule(new Task() {

      @Override
      public void run() {
        world.destroyBody(body);
        userData.setDestroyable(true);
      }
    }, this.getAppearTime());
  }

  protected float getImpulse(Direction direction) {
    final float range = this.getRange() + (float) Math.random() * (this.getMaxRange() - this.getRange());
    switch (direction) {
      case RIGHT:
        return range;
      case LEFT:
      case UP:
      case DOWN:
      default:
        return -range;
    }
  }

  protected abstract float getAppearTime();
}

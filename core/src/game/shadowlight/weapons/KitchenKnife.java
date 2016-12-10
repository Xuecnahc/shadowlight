package game.shadowlight.weapons;

import com.badlogic.gdx.math.Vector2;

import game.shadowlight.world.PlayWorld;

public class KitchenKnife extends RangedWeapon {

  public KitchenKnife(PlayWorld world) {
    super(world);
  }

  public KitchenKnife(PlayWorld world, boolean isAlly) {
    super(world, isAlly);
  }

  @Override
  protected Vector2 initSize() {
    return new Vector2(0.2f, 0.2f);
  }

  @Override
  protected int getDamages() {
    return 1;
  }

  @Override
  protected long getAttackCooldown() {
    return 500;
  }

  @Override
  protected float getAppearTime() {
    return 1f;
  }

  @Override
  protected float getMaxRange() {
    return 5;
  }

  @Override
  protected float getRange() {
    return 3;
  }
}

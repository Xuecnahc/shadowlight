package game.shadowlight.weapons;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import game.shadowlight.world.PlayWorld;

public class Gourdin extends MeleeWeapon {

  public Gourdin(PlayWorld world) {
    super(world);
  }

  public Gourdin(PlayWorld world, boolean isAlly) {
    super(world, isAlly);
  }

  @Override
  protected Vector2 initSize() {
    return new Vector2(0.3f, 1f);
  }

  @Override
  protected int getDamages() {
    return 1;
  }

  @Override
  protected long getAttackCooldown() {
    return 1000;
  }

  @Override
  protected float getAppearTime() {
    return 0.8f;
  }
}

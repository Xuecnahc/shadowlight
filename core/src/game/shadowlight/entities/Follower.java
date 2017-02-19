package game.shadowlight.entities;

import com.badlogic.gdx.Input.Keys;

import game.shadowlight.world.PlayWorld;

public class Follower extends Adventurer {

  public Follower(PlayWorld world, float x, float y, float width) {
    super(world, x, y, width);
  }

  @Override
  public boolean keyDown(int keycode) {
    switch (keycode) {
      case Keys.LEFT:
        velocity.x = -speed / 10;
        break;
      case Keys.RIGHT:
        velocity.x = speed / 10;
        break;
      // TODO remove this case
      case Keys.SPACE:
        body.applyLinearImpulse(0, jumpPower, body.getWorldCenter().x, body.getWorldCenter().y, true);
        break;
      default:
        return false;
    }
    return true;
  }

  @Override
  public boolean keyUp(int keycode) {
    if (keycode == Keys.LEFT || keycode == Keys.RIGHT) {
      velocity.x = 0;
      return true;
    }
    return false;
  }

}

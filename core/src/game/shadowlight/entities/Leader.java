package game.shadowlight.entities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.physics.box2d.World;

import game.shadowlight.entities.type.GenericUserData;

public class Leader extends Adventurer {

  public Leader(World world, float x, float y, float width) {
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
      // TODO Apply number of jump reset when touch the ground
      case Keys.SPACE:
        GenericUserData data = (GenericUserData) body.getUserData();
        data.setNbJump(data.getNbJump() + 1);
        if (data.getNbJump() < maxJump) {
          body.applyLinearImpulse(0, jumpPower, body.getWorldCenter().x, body.getWorldCenter().y, true);
        }
        break;
      default:
        return false;
    }
    return true;
  }

}

package game.shadowlight.entities;

import com.badlogic.gdx.Input.Keys;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.Direction;
import game.shadowlight.world.PlayWorld;

public class Leader extends Adventurer {

  public Leader(PlayWorld world, float x, float y, float width) {
    super(world, x, y, width);
  }

  @Override
  public boolean keyDown(int keycode) {
    switch (keycode) {
      // Attack
      case Keys.A:
        this.weapon.attack(this.body, this.WIDTH, this.HEIGHT, this.facedDirection);
        break;
      case Keys.LEFT:
        this.facedDirection = Direction.LEFT;
        if (this.direction != Direction.UP && this.direction != Direction.DOWN) {
          this.direction = Direction.LEFT;
        }
        
        velocity.x = -speed / 10;
        break;
      case Keys.RIGHT:
        this.facedDirection = Direction.RIGHT;
        if (this.direction != Direction.UP && this.direction != Direction.DOWN) {
          this.direction = Direction.RIGHT;
        }

        velocity.x = speed / 10;
        break;
      case Keys.UP:
        this.direction = Direction.UP;
        break;
      case Keys.DOWN:
        this.direction = Direction.DOWN;
        break;
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

package game.shadowlight.entities.type;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractMovable {

  protected Vector2 velocity = new Vector2();
  protected float jumpPower = 20, speed = 500, maxSpeed = 8, maxJump = 2, nbJump = 1;
  
  abstract public void move();

}

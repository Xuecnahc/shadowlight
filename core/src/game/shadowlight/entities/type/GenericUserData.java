package game.shadowlight.entities.type;

import com.badlogic.gdx.graphics.g2d.Sprite;

import game.shadowlight.entities.collidable.ICollisionReaction;

public class GenericUserData {

  public GenericUserData(String id, Sprite sprite, EntityDestructable destructability) {
    this.id = id;
    this.sprite = sprite;
    this.destructability = destructability;
  }

  private ICollisionReaction collisionReaction;
  
  private EntityDestructable destructability;

  private String id;

  private Sprite sprite;
  
  private boolean toBeDestroyed;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Sprite getSprite() {
    return sprite;
  }

  public void setSprite(Sprite sprite) {
    this.sprite = sprite;
  }

  public EntityDestructable getDestructability() {
    return destructability;
  }

  public void setDestructability(EntityDestructable destructability) {
    this.destructability = destructability;
  }

  public boolean isToBeDestroyed() {
    return toBeDestroyed;
  }

  public void setToBeDestroyed(boolean toBeDestroyed) {
    this.toBeDestroyed = toBeDestroyed;
  }

}

package game.shadowlight.entities.type;

import com.badlogic.gdx.graphics.g2d.Sprite;

import game.shadowlight.entities.collidable.ICollisionReaction;
import game.shadowlight.utils.EnumUserDataId;

public class GenericUserData {

  public GenericUserData(EnumUserDataId id, Sprite sprite, EntityDestructable destructability, ICollisionReaction collisionReaction) {
    this.id = id;
    this.sprite = sprite;
    this.destructability = destructability;
    this.collisionReaction = collisionReaction;
  }

  private ICollisionReaction collisionReaction;
  
  private EntityDestructable destructability;

  private EnumUserDataId id;

  private Sprite sprite;
  
  private int nbJump;
  
  private boolean toBeDestroyed;

  public EnumUserDataId getId() {
    return id;
  }

  public void setId(EnumUserDataId id) {
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

  public ICollisionReaction getCollisionReaction() {
    return collisionReaction;
  }

  public void setCollisionReaction(ICollisionReaction collisionReaction) {
    this.collisionReaction = collisionReaction;
  }

  public int getNbJump() {
    return nbJump;
  }

  public void setNbJump(int nbJump) {
    this.nbJump = nbJump;
  }

}

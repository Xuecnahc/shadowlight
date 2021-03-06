package game.shadowlight.entities.type;

import com.badlogic.gdx.graphics.g2d.Sprite;

import game.shadowlight.entities.collidable.ICollisionReaction;
import game.shadowlight.utils.EnumUserDataId;

public class GenericUserData {

  public GenericUserData(EnumUserDataId id, Sprite sprite, OffensiveProperties offense, DefensiveProperties defense,
      ICollisionReaction collisionReaction) {
    this.id = id;
    this.sprite = sprite;
    this.defense = defense;
    this.collisionReaction = collisionReaction;
    this.offense = offense;
  }

  private ICollisionReaction collisionReaction;

  private DefensiveProperties defense;

  private OffensiveProperties offense;

  private EnumUserDataId id;

  private Sprite sprite;

  private int nbJump;

  private boolean destroyable;

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

  public DefensiveProperties getDefense() {
    return defense;
  }

  public void setDefense(DefensiveProperties defense) {
    this.defense = defense;
  }

  public OffensiveProperties getOffense() {
    return offense;
  }

  public void setOffense(OffensiveProperties offense) {
    this.offense = offense;
  }

  public boolean isDestroyable() {
    return destroyable;
  }

  public void setDestroyable(boolean destroyable) {
    this.destroyable = destroyable;
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

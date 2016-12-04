package game.shadowlight.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

import game.shadowlight.entities.type.EntityDestructable;

public class GenericUserData {

  public GenericUserData(String id, Sprite sprite, EntityDestructable destructability) {
    this.id = id;
    this.sprite = sprite;
    this.destructability = destructability;
  }

  private EntityDestructable destructability;

  private String id;

  private Sprite sprite;

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

}

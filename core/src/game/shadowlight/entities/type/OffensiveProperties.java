package game.shadowlight.entities.type;

public class OffensiveProperties {

  public OffensiveProperties(boolean wounding, int damage) {
    this.wounding = wounding;
    this.damage = damage;
  }

  private boolean wounding;

  private int damage;

  public boolean isWounding() {
    return wounding;
  }

  public void setWounding(boolean wounding) {
    this.wounding = wounding;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

}

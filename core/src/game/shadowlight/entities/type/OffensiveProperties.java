package game.shadowlight.entities.type;

public class OffensiveProperties {
  private boolean wounding;
  private int damage;

  public OffensiveProperties() {
    this(0);
  }

  public OffensiveProperties(int damage) {
    this.wounding = damage > 0;
    this.damage = damage;
  }

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

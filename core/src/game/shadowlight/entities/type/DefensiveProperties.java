package game.shadowlight.entities.type;

public class DefensiveProperties {
  private boolean isVulnerable;
  private int health;
  private long invulnerabilityTime;
  private long lastTimeDamaged;

  public DefensiveProperties() {
    this(true, 1, 0);
  }

  public DefensiveProperties(boolean isVulnerable, int health, long invulnerabilityTime) {
    this.isVulnerable = isVulnerable;
    this.health = health;
    this.invulnerabilityTime = invulnerabilityTime;
  }

  public boolean isVulnerable() {
    return isVulnerable;
  }

  public void setVulnerable(boolean isVulnerable) {
    this.isVulnerable = isVulnerable;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public long getInvulnerabilityTime() {
    return invulnerabilityTime;
  }

  public void setInvulnerabilityTime(long invulnerabilityTime) {
    this.invulnerabilityTime = invulnerabilityTime;
  }

  public long getLastTimeDamaged() {
    return lastTimeDamaged;
  }

  public void setLastTimeDamaged(long lastTimeDamaged) {
    this.lastTimeDamaged = lastTimeDamaged;
  }

}

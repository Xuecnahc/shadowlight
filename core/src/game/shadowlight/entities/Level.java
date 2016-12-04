package game.shadowlight.entities;

import java.awt.Point;
import java.util.ArrayList;
import game.shadowlight.entities.levelObjects.Box;

public class Level {
  private Point startPoint;
  private ArrayList<Box> boxes;
  private ArrayList<Monster> monsters;
  private String music;
  private String backgroundImg;

  public ArrayList<Box> getBoxes() {
    return this.boxes;
  }
  
  public ArrayList<Monster> getMonster() {
    return this.monsters;
  }
  
  public String getMusic() {
    return this.music;
  }

  public Point getStartPoint() {
    return this.startPoint;
  }

  public String getBackgroundImg() {
    return backgroundImg;
  }
}

package game.shadowlight.world;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import game.shadowlight.entities.Adventurer;
import game.shadowlight.entities.type.IObserver;

public class PlayWorld {

  private World world;

  private List<Adventurer> players = new ArrayList<Adventurer>();

  private List<IObserver> observers = new ArrayList<IObserver>();

  public PlayWorld(World world) {
    this.world = world;
  }

  public World getWorld() {
    return world;
  }

  public List<Adventurer> getPlayers() {
    return players;
  }

  public List<IObserver> getObservers() {
    return observers;
  }

  public Body createBody(BodyDef bodyDef) {
    return world.createBody(bodyDef);
  }

  public Joint createJoint(RevoluteJointDef joinDef) {
    return world.createJoint(joinDef);
  }

  public void destroyBody(Body body) {
    world.destroyBody(body);
  }

}

package game.shadowlight.entities.collidable;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.shadowlight.entities.type.GenericUserData;

/**
 * Interface to help handling collision within the {@link GenericUserData} when
 * the world step
 * 
 * @author Bertrand
 *
 */
public interface ICollisionReaction {

  public boolean canCollide(Fixture fixtureA, Fixture fixtureB);

  public void onCollide(Contact contact, Manifold oldManifold);

}

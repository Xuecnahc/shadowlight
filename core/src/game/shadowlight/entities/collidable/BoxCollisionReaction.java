package game.shadowlight.entities.collidable;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.EnumUserDataId;

public class BoxCollisionReaction implements ICollisionReaction {

  @Override
  public boolean canCollide(Fixture fixtureA, Fixture fixtureB) {
    return true;
  }

  @Override
  public void onCollide(Contact contact, Manifold oldManifold) {
    GenericUserData userDataA = (GenericUserData) contact.getFixtureA().getBody().getUserData();
    GenericUserData userDataB = (GenericUserData) contact.getFixtureB().getBody().getUserData();
    if((userDataA!=null && userDataA.getId().equals(EnumUserDataId.PLAYER))
        || (userDataB!=null && userDataB.getId().equals(EnumUserDataId.PLAYER))) {
      // TODO sword isntead of player, loose life
    }
  }
  
}

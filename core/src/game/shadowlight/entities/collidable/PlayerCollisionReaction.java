package game.shadowlight.entities.collidable;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.EnumUserDataId;

public class PlayerCollisionReaction implements ICollisionReaction {

  @Override
  public boolean canCollide(Fixture fixtureA, Fixture fixtureB) {
    return true;
  }

  @Override
  public void onCollide(Contact contact, Manifold oldManifold) {
    GenericUserData userDataA = (GenericUserData) contact.getFixtureA().getBody().getUserData();
    Body playerBody = null;
    if(userDataA!=null && userDataA.getId().equals(EnumUserDataId.PLAYER)) {
      playerBody = contact.getFixtureA().getBody();
    } else {
      playerBody = contact.getFixtureB().getBody();
    }
    if (contact.getWorldManifold().getPoints()[0].y <= playerBody.getPosition().y) {
      ((GenericUserData) playerBody.getUserData()).setNbJump(0);
    }
  }

}

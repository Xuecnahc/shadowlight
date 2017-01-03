package game.shadowlight.entities.collidable;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.utils.EnumUserDataId;

public class WeaponCollisionReaction implements ICollisionReaction {
  private boolean isAlly;

  public void setIsAlly(boolean isAlly) {
    this.isAlly = isAlly;
  }

  @Override
  public boolean canCollide(Fixture fixtureA, Fixture fixtureB) {
    GenericUserData userDataA = (GenericUserData) fixtureA.getBody().getUserData();
    GenericUserData userDataB = (GenericUserData) fixtureB.getBody().getUserData();

    if (this.isAlly) {
      // no friendly fire
      return !userDataA.getId().equals(EnumUserDataId.PLAYER) && !userDataB.getId().equals(EnumUserDataId.PLAYER);
    }

    return true;
  }

  @Override
  public void onCollide(Contact contact, Manifold oldManifold) {
  }

}

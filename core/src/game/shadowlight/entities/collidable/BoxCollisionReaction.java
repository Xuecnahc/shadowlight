package game.shadowlight.entities.collidable;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.TimeUtils;

import game.shadowlight.entities.type.DefensiveProperties;
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
    if (userDataA != null && userDataB != null) {
      if (userDataA.getId().equals(EnumUserDataId.BOX)) {
        getDamaged(userDataA, userDataB);
      } else {
        getDamaged(userDataB, userDataA);
      }
    }
  }

  private void getDamaged(GenericUserData boxData, GenericUserData otherData) {
    long time = TimeUtils.millis();
    DefensiveProperties boxDefense = boxData.getDefense();
    if (otherData.getOffense().isWounding() && time>boxDefense.getLastTimeDamaged()+boxDefense.getInvulnerabilityTime()) {
      boxDefense.setHealth(
          boxDefense.getHealth() - otherData.getOffense().getDamage());
      boxDefense.setLastTimeDamaged(time);
      if(boxDefense.getHealth() <= 0) {
        boxData.setDestroyable(true);
      }
      System.out.println("Blop : " + boxData.getDefense().getHealth());
    }
  }

}

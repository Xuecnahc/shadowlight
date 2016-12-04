package game.shadowlight.utils;

import java.util.HashMap;

/**
 * Statics strings and contents for the game such as keys and paths
 * 
 * @author Xuecnahc
 *
 */
public class GameStatics {
  public static final HashMap<String, String> PATHS = new HashMap<String, String>() {
    private static final long serialVersionUID = 1L;
    {
      put("levels", "bin/levels/level%d.json");
    }
  };

  public static final String GAME_NAME = "Shadowlight";

}

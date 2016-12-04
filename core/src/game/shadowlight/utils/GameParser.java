package game.shadowlight.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;

import game.shadowlight.entities.Level;

/**
 * JSON parser of the game
 *
 * @author Xuecnahc
 *
 */
public class GameParser {

  /**
   * Create a Level object containing the configuration in JSON
   * @param levelId
   *          ID of the level to display
   * @return Level
   *          Parsed object from JSON
   * @throws IOException
   *
   */
  public static Level parseLevelFromId(int levelId, World world, Array<Body> bodies) throws IOException {
    final Path path = Paths.get(String.format(GameStatics.PATHS.get("levels"), levelId));
    final String json = new String(Files.readAllBytes(path));
    final Gson gson = new Gson();
    return gson.fromJson(json, Level.class);
  }
}

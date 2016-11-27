package game.shadowlight.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.shadowlight.entities.levelObjects.Box;

/**
 * JSON parser of the game
 *
 * @author Xuecnahc
 *
 */
public class GameParser {

	public static void createLevelFromFileId(int levelId, World world) {
		GameParser.createLevelFromFileId(levelId, world, new Array<Body>());
	}

	/**
	 * createLevelFromFileId: Create the elements of the level depending on the levelId and add them to the bodies array in a given world
	 *
	 * @param levelId	ID of the level to display
	 * @param world		world to which we append the elements. Cannot be null
	 * @param bodies	array of the world element
	 *
	 * TODO: with the main menu should return an error/succeed message instead of void / handle parse exception
	 *
	 */
	public static void createLevelFromFileId(int levelId, World world, Array<Body> bodies) {
		/*try {
			int i;
			String json="";
			bodies = (bodies != null)? bodies : new Array<Body>();
			// Get the JSON depending only on the levelId
			Path path = Paths.get(String.format(GameStatics.PATHS.get("levels"), levelId));
			if(!Files.exists(path)) {
				return;
			}
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			for(i=0; i<lines.size(); i++) {
				json += lines.get(i);
			}
			JSONObject levelObject = new JSONObject(json);

			// Create boxes and add them to the world
			if(levelObject.has("box")) {
				JSONArray objectArray = (JSONArray) levelObject.get("box");
				for (i = 0; i < objectArray.length(); i++) {
					JSONObject o = (JSONObject)objectArray.get(i);
					Box box = new Box(world, (Integer)o.get("x"), (Integer)o.get("y"), (Integer)o.get("width"), (Integer)o.get("height"));
					bodies.add(box.getBody());
				}
			}
		} catch (JSONException e) {
        	e.printStackTrace();
        } catch (IOException e) {
					e.printStackTrace();
				}*/
	}
}

package game.shadowlight.utils;

import java.util.HashMap;

import game.shadowlight.entities.GenericUserData;
import game.shadowlight.entities.type.EntityDestructable;

public class GameUserData {
	public static final HashMap<String, GenericUserData> USERDATA_MAP = new HashMap<String, GenericUserData>() {
		private static final long serialVersionUID = 1L;

		{
			put("box", new GenericUserData("box", null, new EntityDestructable(true, 10, 1)));
		}
	};
}

package game.shadowlight.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GenericUserData {

	public GenericUserData(String id, Sprite sprite) {
		this.id = id;
		this.sprite = sprite;
	}

	private String id;

	private Sprite sprite;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}

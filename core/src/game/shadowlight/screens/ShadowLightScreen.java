	package game.shadowlight.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import game.shadowlight.ShadowLightGame;

public class ShadowLightScreen implements Screen {
	
	protected ShadowLightGame game;
	protected SpriteBatch batch;
	protected Array<Body> tmpBodies = new Array<Body>();

	public ShadowLightScreen(ShadowLightGame shadowLightGame) {
		super();
		this.game = shadowLightGame;
	}

	@Override
	public void render(float delta) {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
	
	public ShadowLightGame getGame() {
		return game;
	}

}

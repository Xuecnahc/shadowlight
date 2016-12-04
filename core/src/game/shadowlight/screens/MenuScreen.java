package game.shadowlight.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import game.shadowlight.ShadowLightGame;
import game.shadowlight.entities.levelObjects.Box;
import game.shadowlight.utils.GameStatics;

public class MenuScreen extends ShadowLightScreen {

	private Skin skin;
	private Stage stage;
	private Table table;

	/**
	 * Constructor
	 *
	 * @param shadowLightGame	Game of the application. Useful to change screen and get global parameters
	 */
	public MenuScreen(ShadowLightGame shadowLightGame) {
		super(shadowLightGame);
	}

	@Override
	public void render(float delta) {
		// Paint background
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the actors of the scene (mostly buttons)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.81f), true);
		batch = new SpriteBatch();
	   
		if(Gdx.app.getType() == ApplicationType.Desktop) {
			Gdx.graphics.setDisplayMode((int) (Gdx.graphics.getHeight() / 1.5f), Gdx.graphics.getHeight(), false);
		}
	    tmpBodies.add((new Box(world, 1f, 1f, 1f, 1f)).getBody());

	    stage = new Stage();
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(new InputAdapter() {

			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
				case Keys.BACK:
				case Keys.ESCAPE:
					Gdx.app.exit();
					break;
				}
				return false;
			}

		});

		stage = new Stage();
		multiplexer.addProcessor(stage);
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), new TextureAtlas("ui/atlas.pack"));
		
		createUI();

		Gdx.input.setInputProcessor(multiplexer);
	}

	private void createUI() {
		table = new Table(skin);
		table.setFillParent(true);
		
		Label titleLabel = new Label(GameStatics.GAME_NAME, skin);
		
		TextButton buttonPlay = new TextButton("Play", skin);

		buttonPlay.padRight(Gdx.graphics.getWidth()/5);
		buttonPlay.padLeft(Gdx.graphics.getWidth()/5);
		buttonPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScreen());
			}
		});
		
		table.add(titleLabel).spaceBottom(25).row();
		table.add(buttonPlay).spaceBottom(15).row();
		
		stage.addActor(table);
	}
	
}

package game.shadowlight.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.shadowlight.ShadowLightGame;
import game.shadowlight.entities.levelObjects.Box;

public class MenuScreen extends ShadowLightScreen {

	private BitmapFont font;
	private Skin skin;
	private Stage stage;

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

		batch.begin();
		//TODO: Change with localization
        font.draw(batch, "Hello World", 200, 200);
        batch.end();

        // Draw the actors of the scene (mostly buttons)
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -9.81f), true);
		batch = new SpriteBatch();
	    font = new BitmapFont();
	    font.setColor(Color.RED);
	    tmpBodies.add((new Box(world, 1f, 1f, 1f, 1f)).getBody());

	    stage = new Stage();
	    Gdx.input.setInputProcessor(stage);

		// TODO: See what the hell is going on with this textButtonStyle. Shouldn't be hard...
		skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(100, 50, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		// Store the default libgdx font under the name "default".
		BitmapFont bfont=new BitmapFont();
		skin.add("default",bfont);
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

		textButtonStyle.font = skin.getFont("default");
		final TextButton textButton=new TextButton("PLAY", textButtonStyle);
		textButton.setPosition(1, 1);
		stage.addActor(textButton);

		// Listener
		textButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float blop, float blip) {
				textButton.setText("Starting new game");
				game.setScreen(new PlayScreen());
			}
		});

		if(Gdx.app.getType() == ApplicationType.Desktop) {
			Gdx.graphics.setDisplayMode((int) (Gdx.graphics.getHeight() / 1.5f), Gdx.graphics.getHeight(), false);
		}
	}
}

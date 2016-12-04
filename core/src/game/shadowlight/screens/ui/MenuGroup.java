package game.shadowlight.screens.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import game.shadowlight.ShadowLightGame;
import game.shadowlight.screens.PlayScreen;

/**
 * UI class for creating the menu
 * @author Bertrand
 *
 */
public class MenuGroup extends WidgetGroup {

	private TextButton buttonPlay, buttonSetting;
	
	public MenuGroup(Skin skin, ShadowLightGame game) {

		createUI(skin, game);

	}

	private void createUI(Skin skin, final ShadowLightGame game) {
		addActors(skin, game);
	}

	private WidgetGroup addActors(Skin skin, final ShadowLightGame game) {
		WidgetGroup groupMain = new WidgetGroup();
		BitmapFont font = new BitmapFont();
	    font.setColor(Color.RED);
		
		buttonPlay = new TextButton("Play", skin);
		buttonPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScreen());
			}
		});
		buttonPlay.setWidth(Gdx.graphics.getWidth()/2);
		buttonPlay.setHeight(Gdx.graphics.getHeight()/15);
		buttonPlay.setPosition(Gdx.graphics.getWidth()/2-buttonPlay.getWidth()/2,Gdx.graphics.getHeight()/2f);
		
		buttonSetting = new TextButton("Settings", skin);
		buttonSetting.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new PlayScreen());
			}
		});
		buttonSetting.setWidth(Gdx.graphics.getWidth()/2);
		buttonSetting.setHeight(Gdx.graphics.getHeight()/15);
		buttonSetting.setPosition(Gdx.graphics.getWidth()/2-buttonSetting.getWidth()/2,Gdx.graphics.getHeight()/2f-buttonPlay.getHeight()-10);
		groupMain.addActor(buttonPlay);
		this.addActor(groupMain);
		return buttonPlay;
	}

}

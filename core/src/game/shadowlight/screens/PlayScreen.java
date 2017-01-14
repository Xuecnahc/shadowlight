package game.shadowlight.screens;

import java.io.IOException;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.shadowlight.entities.Adventurer;
import game.shadowlight.entities.Leader;
import game.shadowlight.entities.Level;
import game.shadowlight.entities.Monster;
import game.shadowlight.entities.collidable.GameContactListener;
import game.shadowlight.entities.levelObjects.Box;
import game.shadowlight.entities.type.GenericUserData;
import game.shadowlight.entities.type.IMovable;
import game.shadowlight.entities.type.IObserver;
import game.shadowlight.utils.GameParser;
import game.shadowlight.world.PlayWorld;

public class PlayScreen implements Screen {

  private PlayWorld playWorld;
  private Box2DDebugRenderer debugRenderer;
  private SpriteBatch batch;
  private OrthographicCamera camera;

  private final float TIMESTEP = 1 / 60f;
  private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;

  private Adventurer player;

  private Vector3 bottomLeft, bottomRight;

  private Array<Body> tmpBodies = new Array<Body>();

  @Override
  public void render(float delta) {
    // Clear stage
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    // Update player position and camera
    if (player.getBody().getPosition().x < bottomLeft.x)
      player.getBody().setTransform(bottomRight.x, player.getBody().getPosition().y, player.getBody().getAngle());
    else if (player.getBody().getPosition().x > bottomRight.x)
      player.getBody().setTransform(bottomLeft.x, player.getBody().getPosition().y, player.getBody().getAngle());
    player.update();
    playWorld.getWorld().step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);

    camera.position.y = player.getBody().getPosition().y;
    camera.position.x = player.getBody().getPosition().x;
    camera.update();

    // Regenerate stage by changing position of objects
    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    for (Body body : tmpBodies)
      if (body.getUserData() instanceof GenericUserData) {
        GenericUserData data = (GenericUserData) body.getUserData();
        if (!data.isDestroyable()) {
          Sprite sprite = data.getSprite();
          if (sprite != null) {
            sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);
            sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
            sprite.draw(batch);
          }
        } else {
          playWorld.getWorld().destroyBody(body);
          tmpBodies.removeValue(body, true);
        }
      }
    batch.end();

    for (IObserver observer : playWorld.getObservers()) {
      observer.observe();
    }

    for (IMovable movable : playWorld.getMovable()) {
      movable.move();
    }
    debugRenderer.render(playWorld.getWorld(), camera.combined);

  }

  @Override
  public void resize(int width, int height) {
    camera.viewportWidth = width / 25;
    camera.viewportHeight = height / 25;
  }

  @Override
  public void show() {
    // Set screen and init world
    if (Gdx.app.getType() == ApplicationType.Desktop) {
      Gdx.graphics.setDisplayMode((int) (Gdx.graphics.getHeight() / 1.5f), Gdx.graphics.getHeight(), false);
    }
    playWorld = new PlayWorld(new World(new Vector2(0, -9.81f), true));
    debugRenderer = new Box2DDebugRenderer();
    batch = new SpriteBatch();
    camera = new OrthographicCamera(Gdx.graphics.getWidth() / 25, Gdx.graphics.getHeight() / 25);

    // Init the stage with the corresponding JSON file
    Level level = null;
    try {
      level = GameParser.parseLevelFromId(1, playWorld.getWorld(), tmpBodies);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (level == null) {
      // TODO: show popup and redirect to MenuScreen
      return;
    }

    for (Box box : level.getBoxes()) {
      box.setWorld(playWorld);
      tmpBodies.add(box.getBody());
    }

    Monster monster = new Monster(2, 2, 1, 1, true);
    monster.setWorld(playWorld);
    // Create player and set listeners
    player = new Leader(playWorld, (float) level.getStartPoint().getX(), (float) level.getStartPoint().getY(), 1);
    playWorld.getPlayers().add(player);
    Gdx.input.setInputProcessor(new InputMultiplexer(player, new InputAdapter() {
      @Override
      public boolean keyDown(int keycode) {
        switch (keycode) {
          case Keys.ESCAPE:
            break;
        }
        return false;
      }

      @Override
      public boolean scrolled(int amount) {
        camera.zoom += amount / 25f;
        return true;
      }
    }));
    createContactListener();
    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();

    // GROUND
    // body definition
    bodyDef.type = BodyType.StaticBody;
    bodyDef.position.set(0, 0);

    // ground shape
    ChainShape groundShape = new ChainShape();
    bottomLeft = new Vector3(0, Gdx.graphics.getHeight(), 0);
    bottomRight = new Vector3(Gdx.graphics.getWidth(), bottomLeft.y, 0);
    camera.unproject(bottomLeft);
    camera.unproject(bottomRight);

    groundShape.createChain(new float[] { bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y });

    // fixture definition
    fixtureDef.shape = groundShape;
    fixtureDef.friction = .5f;
    fixtureDef.restitution = 0;

    Body ground = playWorld.getWorld().createBody(bodyDef);
    ground.createFixture(fixtureDef);

    groundShape.dispose();
  }

  private void createContactListener() {
    GameContactListener contact = new GameContactListener();
    playWorld.getWorld().setContactFilter(contact);
    playWorld.getWorld().setContactListener(contact);
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
    playWorld.getWorld().dispose();
    debugRenderer.dispose();
  }

}

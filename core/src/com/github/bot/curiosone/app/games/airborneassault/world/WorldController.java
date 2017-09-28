package com.github.bot.curiosone.app.games.airborneassault.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.ScrollingBackground;
import com.github.bot.curiosone.app.games.airborneassault.player.HealthBar;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.screens.GameOverScreen;
import com.github.bot.curiosone.app.games.airborneassault.settings.Constants;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.spawner.PlaneSpawner;
import com.github.bot.curiosone.app.chat.Chat;

import java.util.Iterator;

/**
 * This class is responsible of creating and updating the PlayScreen logic.
 */
public class WorldController {

  private Chat game;
  private SpriteBatch batch;
  protected OrthographicCamera camera;
  private Iterator<Actor> actorIterator;
  private long lastSpawnedTime;
  protected ScrollingBackground scrollingBackground;
  protected Settings settings;
  protected Music music;
  protected Stage stage;
  protected Manager manager;
  protected HealthBar healthBar;
  private PlaneSpawner spawner;
  protected ImageButton backButton,continueButton,backToButton;
  protected boolean paused = false;
  protected BitmapFont score;
  protected String scoreText;
  protected GlyphLayout layout;

  public WorldController(Chat game){
    this.game = game;
    this.batch = new SpriteBatch();
    init();
  }

  private void init(){
    manager = Manager.getIstance();
    stage = new Stage(new StretchViewport(Constants.WIDTH,Constants.HEIGHT),this.batch);
    Gdx.input.setInputProcessor(stage);

    /*Spawning the tiles*/
    settings = Settings.getIstance();
    spawner = new PlaneSpawner();
    actorIterator = spawner.iterator();
    lastSpawnedTime = 0;

    /*Camera Settings*/
    camera = new OrthographicCamera();
    camera.setToOrtho(false,Constants.WIDTH,Constants.HEIGHT);
    camera.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);

    /*Background*/
    scrollingBackground = new ScrollingBackground();

    /*Music Settings*/
    music = manager.getAssetManager().get(Assets.playMusic.getPath());
    if(settings.isMUSIC()){
      music.setLooping(true);
      music.play();
    }
    /*GUI*/
    healthBar = new HealthBar();
    scoreText = settings.getScore()+"";
    score = manager.getAssetManager().get(Assets.font.getPath());
    score.setColor(Color.WHITE);
    layout = new GlyphLayout(score,scoreText);
    createBackMenu();
  }

  public void update(float deltaTime) {
    if (!paused) {
      backButton.toFront();
      scrollingBackground.update(deltaTime);
      healthBar.update();
      //Updates the enemies
      stage.act();
      //Spawn the actors every X seconds
      if (TimeUtils.nanoTime() - lastSpawnedTime > settings.getSpawnRate() - (settings.getAccelleration() * 1000000)) {
        //Gdx.app.log("SpawnTime ",(float)(settings.getSpawnRate()-(settings.getAccelleration()*1000000))/(float)1000000000+"");
        Actor next = spawner.getActors().get(0);
        spawner.getActors().removeIndex(0);
        stage.addActor(next);
        if(spawner.getActors().size==14){
          spawner.spawn();
        }
        lastSpawnedTime = TimeUtils.nanoTime();
      }
      //updates the actors
      for (Actor actor : stage.getActors()) {
        if(!actor.getClass().equals(ImageButton.class)) {
          if (actor.remove()) {
            settings.addAccelleration(1);
            stage.getActors().removeValue(actor, true);
          }
        }
      }
      //to GameOverScreen
      if (Player.isDead()) {
        music.stop();
        Player.reset();
        game.setScreen(new GameOverScreen(game));
      }

    }
    //updates the score
    scoreText = settings.getScore()+"";
    layout.setText(score,scoreText);
  }

  /**
   * Creates the buttons for the BackMenu
   */
  public void createBackMenu(){
    TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.backButton.getPath(),Texture.class)));
    backButton = new ImageButton(texture,texture);
    backButton.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        continueButton.toFront();
        backButton.toFront();
        stage.getActors().add(continueButton);
        stage.getActors().add(backToButton);
        backButton.setTouchable(Touchable.disabled);
        backButton.setVisible(false);
        for(Actor actor:stage.getActors()){
          if(!actor.getClass().equals(ImageButton.class)){
              actor.setTouchable(Touchable.disabled);
          }
        }
        paused = true;
        return true;
      }
    });
    backButton.setBounds(420,740,60,60);
    stage.addActor(backButton);
    TextureRegionDrawable texture1 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.continueButton.getPath(),Texture.class)));
    TextureRegionDrawable texture2 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.continueButtonPressed.getPath(),Texture.class)));
    continueButton = new ImageButton(texture1,texture2);
    continueButton.setBounds(Constants.WIDTH/2-300/2,Constants.HEIGHT/2,300,60);
    TextureRegionDrawable texture3 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.backToButton.getPath(),Texture.class)));
    TextureRegionDrawable texture4 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.backToButtonPressed.getPath(),Texture.class)));
    backToButton = new ImageButton(texture3,texture4);
    backToButton.setBounds(Constants.WIDTH/2-300/2,Constants.HEIGHT/2-70,300,60);
  }

}

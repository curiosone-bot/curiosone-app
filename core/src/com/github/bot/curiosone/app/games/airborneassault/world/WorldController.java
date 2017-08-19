package com.github.bot.curiosone.app.games.airborneassault.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.github.bot.curiosone.app.workflow.Chat;

import java.util.Iterator;

public class WorldController {
  private static final String TAG = WorldController.class.getName();

  private Chat game;
  protected OrthographicCamera camera;
  private Iterator<Actor> actorIterator;
  private long lastSpawnedTime;
  protected ScrollingBackground scrollingBackground;
  private Settings settings;
  protected Music music;
  protected Stage stage;
  protected Manager manager;
  protected HealthBar healthBar;
  private PlaneSpawner spawner;
  protected ImageButton backButton,continueButton,backToButton;
  protected boolean paused = false;

  public WorldController(Chat game){
    this.game = game;
    init();
  }

  private void init(){
    manager = Manager.getIstance();
    manager.loadPlayScreen();
    stage = new Stage(new StretchViewport(480,800),game.getBatch());
    Gdx.input.setInputProcessor(stage);
    //Spawning the tiles
    settings = Settings.getIstance();
    spawner = new PlaneSpawner();
    actorIterator = spawner.iterator();
    lastSpawnedTime = 0;
    //Camera Settings
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480 / 2, 800 / 2, 0);
    //Background
    scrollingBackground = new ScrollingBackground();
    //Music Settings
    music = manager.getAssetManager().get(Assets.playMusic.getPath());
    music.setLooping(true);
    music.play();
    //GUI
    healthBar = new HealthBar();
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
      if (TimeUtils.nanoTime() - lastSpawnedTime > settings.getSpawnRate() - (settings.getAccelleration() * 1500000)) {
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
            settings.addAccelleration(3);
            stage.getActors().removeValue(actor, true);
          }
        }
      }
      //to GameOverScreen
      if (Player.isDead()) {
//            if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
        manager.getAssetManager().clear();
        Player.reset();
        game.setScreen(new GameOverScreen(game));
      }
    }
    else {

    }

  }

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
    backButton.setBounds(430,750,50,50);
    stage.addActor(backButton);
    TextureRegionDrawable texture1 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.continueButton.getPath(),Texture.class)));
    TextureRegionDrawable texture2 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.continueButtonPressed.getPath(),Texture.class)));
    continueButton = new ImageButton(texture1,texture2);
    continueButton.setBounds(Constants.WIDTH/2-200/2,Constants.HEIGHT/2,200,50);
    TextureRegionDrawable texture3 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.backToButton.getPath(),Texture.class)));
    TextureRegionDrawable texture4 = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.backToButtonPressed.getPath(),Texture.class)));
    backToButton = new ImageButton(texture3,texture4);
    backToButton.setBounds(Constants.WIDTH/2-200/2,Constants.HEIGHT/2-60,200,50);
  }

}

package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.HealthBar;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.workflow.Chat;
import java.util.Iterator;

public class TestScreen extends ScreenAdapter{
  private Chat game;
  private OrthographicCamera camera;
  private Iterator<Actor> actorIterator;
  private long lastSpawnedTime;
  private boolean gameOver = false;
  private Texture background, background2, background3;
  private float y1,y2;
  private Settings settings;
  private Music music;
  private Stage stage;
  private Manager manager;
  private HealthBar healthBar;
  private PlaneSpawnerTest spawner;

  public TestScreen(Chat game) {
    this.game = game;
    manager = Manager.getIstance();
    manager.loadPlayScreen();
    stage = new Stage(new StretchViewport(480,800),game.getBatch());
    Gdx.input.setInputProcessor(stage);
    //Spawning the tiles
    settings = Settings.getIstance();
    spawner = new PlaneSpawnerTest();
    actorIterator = spawner.iterator();
    lastSpawnedTime = 0;
    //Camera Settings
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480 / 2, 800 / 2, 0);
    //Background
    background2 = manager.getAssetManager().get(Assets.playBackground2.getPath());
    background3 = manager.getAssetManager().get(Assets.playBackground3.getPath());
    background = manager.getAssetManager().get(Assets.playBackground.getPath());
    y1=0;
    y2=800;
    //Music
//        if (settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop){
//          music = manager.getAssetManager().get(Assets.playMusic.getPath());
//          music.setLooping(true);
//          music.play();
//        }
    healthBar = new HealthBar();
  }

  public void update(float dt) {
    healthBar.update();
    //Updates the enemies
    stage.act();
    //Scrolls the background2
    float speed = dt*(Speed.BACKGROUND.getSpeed());
    y1-= speed;
    y2-= speed;
    if(y1+800<=0){
      y1=y2 + 800;
    }
    if(y2+800<=0){
      y2=y1+800;
    }

    //Spawn the actors every X seconds
    if(TimeUtils.nanoTime()-lastSpawnedTime> settings.getSpawnRate()-(settings.getAccelleration()*2000000)){
      if(actorIterator.hasNext()) {
        Actor next = actorIterator.next();
        stage.addActor(next);
        if(spawner.getActors().indexOf(next,true)==spawner.getActors().size-2){
          spawner.spawn();
        }
      }
      lastSpawnedTime = TimeUtils.nanoTime();
    }

    //updates the actors
    for(Actor actor:stage.getActors()){
      if(actor.remove()){
        settings.addAccelleration(2);
        stage.getActors().removeValue(actor,true);
      }
    }

    //to GameOverScreen
    if(Player.isDead()){
//            if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
      manager.getAssetManager().clear();
      Player.reset();
      game.setScreen(new GameOverScreen(game));
      dispose();
    }
  }

  public void draw() {
    camera.update();
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.getBatch().setProjectionMatrix(camera.combined);
    game.getBatch().begin();
    //draws background2
    game.getBatch().draw(background,0,0,480,800);
    game.getBatch().draw(background2,0,y1,480,800);
    game.getBatch().draw(background3,0,y2,480,800);
    game.getBatch().end();
    //draws the enemies
    stage.draw();
    game.getBatch().begin();
    healthBar.draw(game.getBatch());
    game.getBatch().end();
  }

  @Override
  public void render(float delta) {
    if (!gameOver) {
      update(delta);
    }
    draw();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width,height);
  }

  @Override
  public void dispose() {
    background3.dispose();
    background2.dispose();
    background.dispose();
    healthBar.dispose();
//        if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
    super.dispose();
  }
}

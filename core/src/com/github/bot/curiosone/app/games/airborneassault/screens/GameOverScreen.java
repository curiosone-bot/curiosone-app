package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.workflow.Chat;

/**
 * @author Alessandro Roic
 * This screen is shown when the player lose.
 */
public class GameOverScreen extends ScreenAdapter{

  private Chat game;
  private Texture gameOverTexture;
  private OrthographicCamera camera;
  private int count;
  private Music gameOverSound;
  private Settings settings;
  private BitmapFont bitmapFont;

  public GameOverScreen(Chat game) {
    this.game = game;
    Manager manager = Manager.getIstance();
    manager.loadGameOverScreen();
    settings = Settings.getIstance();
    settings.saveScore();
//    if(settings.MUSIC) {
//      gameOverSound = manager.getAssetManager().get(Assets.gameOverMusic.getPath());
//      gameOverSound.play();
//    }
    gameOverTexture = manager.getAssetManager().get(Assets.gameOverBackground.getPath());
    //Camera Settings
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480 / 2, 800 / 2, 0);
    //Text
    bitmapFont = manager.getAssetManager().get(Assets.font.getPath());
  }

  @Override
  public void render(float delta) {
    update();
    draw();
  }

  private void draw() {
    camera.update();
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.getBatch().setProjectionMatrix(camera.combined);
    game.getBatch().begin();
    game.getBatch().draw(gameOverTexture,0,0,480,800);
    bitmapFont.draw(game.getBatch(),"Points"+settings.getScore(),480/2,800/2);
    bitmapFont.draw(game.getBatch(),"Record"+settings.getSavedScore(),480/2,800/2);
    game.getBatch().end();
  }

  private void update() {
    count++;
    if(count>120) {
      game.setScreen(new MainMenuScreen(game));
      dispose();
    }
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
  }

  @Override
  public void dispose() {
    super.dispose();
    gameOverTexture.dispose();
//    if(settings.MUSIC)gameOverSound.dispose();
  }
}

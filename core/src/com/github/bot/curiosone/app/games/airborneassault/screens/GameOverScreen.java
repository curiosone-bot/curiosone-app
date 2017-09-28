package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Timer;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Constants;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;

/**
 * @author Alessandro Roic
 * This screen is shown when the player lose.
 */
public class GameOverScreen extends ScreenAdapter{

  private Chat game;
  private Texture gameOverTexture;
  private OrthographicCamera camera;
  private Music gameOverSound;
  private Settings settings;
  private BitmapFont bitmapFont;
  private String text1,text2;
  private float width1, width2;
  private Timer next;

  public GameOverScreen(final Chat game) {
    this.game = game;
    Manager manager = Manager.getIstance();
    settings = Settings.getIstance();
    settings.saveScore();
    next = new Timer();
    next.scheduleTask(new Timer.Task() {
      @Override
      public void run() {
        gameOverSound.stop();
        game.setScreen(new MainMenuScreen(game));
        dispose();
      }
    },3.8f);
    //Textures
    gameOverTexture = manager.getAssetManager().get(Assets.gameOverBackground.getPath());

    //Camera Settings
    camera = new OrthographicCamera();
    camera.setToOrtho(false,Constants.WIDTH, Constants.HEIGHT);
    camera.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);

    //Text
    bitmapFont = manager.getAssetManager().get(Assets.font.getPath());
    bitmapFont.setColor(Color.WHITE);
    text1 = "Points : "+settings.getScore();
    text2 = "Record : "+settings.getSavedScore();
    GlyphLayout layout1 = new GlyphLayout(bitmapFont,text1);
    GlyphLayout layout2 = new GlyphLayout(bitmapFont,text2);
    width1 = layout1.width;
    width2 = layout2.width;
    //Sound
    gameOverSound = manager.getAssetManager().get(Assets.gameOverMusic.getPath());
  }

  @Override
  public void render(float delta) {
    update();
    draw();
  }

  /**
   * Draws all the needed textures
   */
  private void draw() {
    camera.update();
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.getBatch().setProjectionMatrix(camera.combined);
    game.getBatch().begin();
    game.getBatch().draw(gameOverTexture,0,0, Constants.WIDTH, Constants.HEIGHT);
    bitmapFont.draw(game.getBatch(),"Points : "+settings.getScore(),Constants.WIDTH/2-width1/2,Constants.HEIGHT/2+100);
    bitmapFont.draw(game.getBatch(),"Record : "+settings.getSavedScore(),Constants.WIDTH/2-width2/2,Constants.HEIGHT/2+50);
    game.getBatch().end();
  }

  /**
   * Updates the screen logic
   */
  private void update() {
    gameOverSound.play();
    next.start();
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
  }

  @Override
  public void dispose() {
    settings.resetAcceleration();
    settings.resetScore();
    super.dispose();
  }
}

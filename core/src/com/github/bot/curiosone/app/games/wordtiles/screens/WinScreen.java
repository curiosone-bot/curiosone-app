package com.github.bot.curiosone.app.games.wordtiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.workflow.Chat;

/**
 * @author Alessandro Roic
 * This screen is shown when the player has won a level
 */
public class WinScreen  extends ScreenAdapter{

  private Chat game;
  private Texture winTexture;
  private OrthographicCamera camera;
  private int count;
  private Music winMusic;
  private Settings settings;

  public WinScreen(Chat game) {
    this.game = game;
    settings = Settings.getIstance();
    winTexture = new Texture("WordTiles/Win.png");
    //Camera Settings
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480 / 2, 800 / 2, 0);
    if(settings.MUSIC) {
      winMusic = Gdx.audio.newMusic(Gdx.files.internal("WordTiles/Songs/Victory.mp3"));
      winMusic.play();
    }
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
    game.getBatch().draw(winTexture,0,0,480,800);
    game.getBatch().end();
  }

  private void update() {
    count++;
    if(count>360) {
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
    winTexture.dispose();
    if(settings.MUSIC)winMusic.dispose();
    super.dispose();
  }
}

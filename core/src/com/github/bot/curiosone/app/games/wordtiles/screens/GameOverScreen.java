package com.github.bot.curiosone.app.games.wordtiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

  public GameOverScreen(Chat game) {
    this.game = game;
    gameOverTexture = new Texture("WordTiles/GameOver.png");
    //Camera Settings
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480 / 2, 800 / 2, 0);
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
  }
}

package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.github.bot.curiosone.app.games.airborneassault.world.WorldController;
import com.github.bot.curiosone.app.games.airborneassault.world.WorldRenderer;
import com.github.bot.curiosone.app.workflow.Chat;

/**
 * @author Alessandro Roic
 * This class contains the gameplay
 */
public class PlayScreen extends ScreenAdapter {
  private WorldController worldController;
  private WorldRenderer worldRenderer;
  private boolean paused = false;
  private ImageButton backButton;

  public PlayScreen(Chat game) {
    worldController = new WorldController(game);
    worldRenderer = new WorldRenderer(worldController,game);
  }

  @Override
  public void render(float delta) {
    if(!paused){
      worldController.update(delta);
    }
    worldRenderer.render();
  }

  @Override
  public void resize(int width, int height) {
    worldRenderer.resize(width, height);
  }

  @Override
  public void pause() {
    paused = true;
  }

  @Override
  public void resume() {
    paused = false;
  }

  @Override
  public void dispose() {
//        if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
    super.dispose();
    worldRenderer.dispose();
  }
}

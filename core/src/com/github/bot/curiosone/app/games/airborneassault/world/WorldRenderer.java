package com.github.bot.curiosone.app.games.airborneassault.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Disposable;
import com.github.bot.curiosone.app.games.airborneassault.screens.MainMenuScreen;
import com.github.bot.curiosone.app.workflow.Chat;

import java.awt.*;

public class WorldRenderer implements Disposable {
  private static final String TAG = WorldRenderer.class.getName();

  private OrthographicCamera camera;
  private Chat game;
  private WorldController controller;

  public WorldRenderer(WorldController controller, Chat game) {
    this.controller = controller;
    this.game = game;
    init();
  }

  private void init() {
    //Camera Settings
    camera = controller.camera;
    createBackMenu();
  }


  public void render() {
    camera.update();
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.getBatch().setProjectionMatrix(camera.combined);
    game.getBatch().begin();
    //draws background2
    controller.scrollingBackground.draw(game.getBatch());
    game.getBatch().end();
    //draws the actors
    controller.stage.draw();
    game.getBatch().begin();
    controller.healthBar.draw(game.getBatch());
    game.getBatch().end();
  }

  public void resize(int width, int height) {
    controller.stage.getViewport().update(width, height);
  }

  @Override
  public void dispose() {
    controller.scrollingBackground.dispose();
    controller.healthBar.dispose();
    controller.music.dispose();
    controller.stage.dispose();
    controller.manager.getAssetManager().clear();
  }

  private void createBackMenu() {
    controller.continueButton.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        controller.backButton.setVisible(true);
        controller.backButton.setTouchable(Touchable.enabled);
        for(Actor actor:controller.stage.getActors()){
          if(!actor.getClass().equals(ImageButton.class)){
            actor.setTouchable(Touchable.enabled);
          }
        }
        controller.stage.getActors().removeValue(controller.continueButton,true);
        controller.stage.getActors().removeValue(controller.backToButton,true);
        controller.paused = false;
      }
    });
    controller.backToButton.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        dispose();
        game.setScreen(new MainMenuScreen(game));
      }
    });
  }
}


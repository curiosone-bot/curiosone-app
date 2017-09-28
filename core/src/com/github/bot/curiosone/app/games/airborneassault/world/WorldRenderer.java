package com.github.bot.curiosone.app.games.airborneassault.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Disposable;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.screens.MainMenuScreen;
import com.github.bot.curiosone.app.games.airborneassault.settings.Constants;
import com.github.bot.curiosone.app.workflow.Chat;

import java.awt.*;

/**
 * This class is responsible of rendering the game objects initialized in the WordController class.
 */
public class WorldRenderer implements Disposable {
  private static final String TAG = WorldRenderer.class.getName();

  private OrthographicCamera camera;
  private Chat game;
  private WorldController controller;
  private BitmapFont score;

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
    //draws background
    controller.scrollingBackground.draw(game.getBatch());
    game.getBatch().end();
    //draws the actors
    controller.stage.draw();
    game.getBatch().begin();
    //draws the healthbar and score
    controller.healthBar.draw(game.getBatch());
    controller.score.draw(game.getBatch(),controller.scoreText, Constants.WIDTH/2-controller.layout.width/2,780);
    game.getBatch().end();
  }

  public void resize(int width, int height) {
    controller.stage.getViewport().update(width, height);
  }

  /**
   * Disposes every assets not needed and reset the game stats
   */
  @Override
  public void dispose() {
    controller.settings.resetScore();
    controller.settings.resetAcceleration();
    controller.music.stop();
    controller.stage.dispose();
  }

  /**
   * Adds listeners to the backMenu buttons
   */
  private void createBackMenu() {
    //The continue button needs to restore everything back to normal
    controller.continueButton.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        controller.backButton.setVisible(true);
        controller.backButton.setTouchable(Touchable.enabled);
        //Restore the Touchable condition to every Plane
        for(Actor actor:controller.stage.getActors()){
          if(!actor.getClass().equals(ImageButton.class)){
            actor.setTouchable(Touchable.enabled);
          }
        }
        //Removes the BackMenu buttons and resumes the game
        controller.stage.getActors().removeValue(controller.continueButton,true);
        controller.stage.getActors().removeValue(controller.backToButton,true);
        controller.paused = false;
      }
    });
    //The backTo button resets the Players stats and game stats and then sets the screen to the Main Menu
    controller.backToButton.addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Player.reset();
        dispose();
        game.setScreen(new MainMenuScreen(game));
      }
    });
  }
}


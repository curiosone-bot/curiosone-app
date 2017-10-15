package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.bot.curiosone.app.chat.helpers.Actionable;
import com.github.bot.curiosone.app.chat.helpers.Backable;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.world.ChatRender;
import com.github.bot.curiosone.app.chat.world.ChatWorld;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;
import com.github.bot.curiosone.app.games.wordcrush.loaders.AudioManager;


public class ChatScreen implements Screen, Actionable, Backable {
  private ChatRender renderer;
  private ChatWorld world;
  private static Screen prevScreen;

  public ChatScreen() {
    this.world = new ChatWorld();
    this.renderer = new ChatRender(world);
    world.setRender(renderer);
  }


  @Override
  public void show() {
    InputProcessor backProcessor = new InputAdapter() {
      @Override
      public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) ) {
          ScreenManager.getInstance().showScreen(prevScreen);
        }
        return false;
      }
    };
    InputMultiplexer multiplexer = new InputMultiplexer(backProcessor, renderer.getStage());
    Gdx.input.setInputProcessor(multiplexer);
  }

  @Override
  public void render(float delta) {
    world.update(delta);
    renderer.render();
  }

  @Override
  public void resize(int width, int height) {
    Gdx.app.log("Chat", "resize called");
    renderer.getView().update(width, height);
  }

  @Override
  public void pause() {
    //Gdx.app.log("Chat", "pause called");
  }

  @Override
  public void resume() {
    //Gdx.app.log("Chat", "resume called");
  }

  @Override
  public void hide() {
    //Gdx.app.log("Chat", "hide called");
  }

  @Override
  public void dispose() {
    renderer.dispose();
    Gdx.input.setOnscreenKeyboardVisible(false);
  }

  @Override
  public void entryTransition() {
    renderer.getStage().addAction(Actions.sequence(Actions.alpha(0.9f), Actions.fadeIn(0.3f)));
  }

  @Override
  public void setPrevScreen(Screen screen) {
    prevScreen = screen;
  }
}

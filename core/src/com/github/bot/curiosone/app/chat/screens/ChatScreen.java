package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.bot.curiosone.app.chat.helpers.Actionable;
import com.github.bot.curiosone.app.chat.world.ChatRender;
import com.github.bot.curiosone.app.chat.world.ChatWorld;


public class ChatScreen implements Screen, Actionable {

  private ChatRender renderer;
  private ChatWorld world;

  public ChatScreen() {
    this.world = new ChatWorld();
    this.renderer = new ChatRender(world);
    world.setRender(renderer);
  }


  @Override
  public void show() {
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
    renderer.getStage().addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.2f)));
  }

}

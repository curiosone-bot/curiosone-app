package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.helpers.BuildableStageScreen;
import com.github.bot.curiosone.app.chat.world.ChatRender;
import com.github.bot.curiosone.app.chat.world.ChatWorld;

import java.io.IOException;

public class ChatScreen implements BuildableStageScreen {

  private ChatRender renderer;
  private ChatWorld world;

  public ChatScreen() throws IOException {
    this.world = new ChatWorld();
    this.renderer = new ChatRender(world);
    world.setRender(renderer);
  }


  @Override
  public void show() {
    //Gdx.app.log("Chat", "show called");
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
    //Gdx.app.log("Chat", "dispose called");
  }

  @Override
  public void buildStage() {
    renderer.buildStage();
  }
}

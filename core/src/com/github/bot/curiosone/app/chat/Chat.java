package com.github.bot.curiosone.app.chat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;

public class Chat extends Game {

  private SpriteBatch batch ;

  public SpriteBatch getBatch() {
    return batch;
  }

  @Override
  public void create() {
    batch = new SpriteBatch();
    setScreen(new ChatScreen(this));
    setScreen(new ChatScreen(this));
  }

  @Override
  public void dispose() {
    batch.dispose();
    super.dispose();
  }
}

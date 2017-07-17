package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
<<<<<<< HEAD
import com.github.bot.curiosone.app.workflow.chatscreens.ChatScreen;
=======
import com.github.bot.curiosone.app.workflow.screens.ChatScreen;
>>>>>>> master

public class Chat extends Game {

  private SpriteBatch batch ;

  public SpriteBatch getBatch() {
    return batch;
  }

  @Override
  public void create() {
    batch = new SpriteBatch();
    setScreen(new ChatScreen(this));
  }

  @Override
  public void dispose() {
    batch.dispose();
    super.dispose();
  }
}

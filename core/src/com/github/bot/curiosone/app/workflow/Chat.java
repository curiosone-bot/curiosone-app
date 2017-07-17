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
<<<<<<< HEAD
    setScreen(new ChatScreen(this));
=======
<<<<<<< HEAD
    setScreen(new ChatScreen(this));
=======
    
>>>>>>> 4cc0d37a460a88e299f55e9153bf51c32449de4f
>>>>>>> master
  }

  @Override
  public void dispose() {
    batch.dispose();
    super.dispose();
  }
}

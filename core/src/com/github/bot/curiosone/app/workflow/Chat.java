package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chat extends Game {

  private SpriteBatch batch ;

  public SpriteBatch getBatch() { return batch; }

  @Override
  public void create() {
    batch = new SpriteBatch();

  }

  @Override
  public void dispose() {
    batch.dispose();
    super.dispose();
  }
}

package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class AbstractScreen extends Stage implements BuildableStageScreen, Actionable {
  public AbstractScreen() {
    super(new StretchViewport(480, 800, new OrthographicCamera()));
  }

  protected static void reorder(float width, float height, Button... buttons) {
    reorder(width, height, 0f, buttons);
  }

  protected static void reorder(float width, float height, float topOffset, Button... buttons) {
    final float POSX = 240 - (width / 2);
    final float CONSTY = ((800 - topOffset) / (buttons.length + 1));
    float posY = CONSTY  - (height / 2);
    Gdx.app.log("len", buttons.length + "");
    for(Button button : buttons) {
      button.setSize(width, height);
      button.setPosition(POSX, posY);
      posY += CONSTY;
    };
  }

  protected void addActors(Actor... actors) {
    for(Actor a : actors) {
      addActor(a);
    }
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    super.act(delta);
    super.draw();
  }

  @Override
  public void show() {
    Gdx.input.setInputProcessor(this);
  }

  @Override
  public void resize(int width, int height) {
    getViewport().update(width, height, true);
  }

  @Override public void hide() {}
  @Override public void pause() {}
  @Override public void resume() {}
}

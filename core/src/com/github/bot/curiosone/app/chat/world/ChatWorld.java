package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;

public class ChatWorld {

  private Stage stage;
  private SendButton send;
  private TextField field;

  public ChatWorld() {
    this.stage = new Stage();
    Gdx.input.setInputProcessor(stage);
    this.send = new SendButton(100, 100, 0, 0);
    stage.addActor(send);
  }

  public void update(float delta) {
    Gdx.app.log("ChatWorld", "update");
    stage.act(delta);
  }

  public Stage getStage() {
    return stage;
  }
}

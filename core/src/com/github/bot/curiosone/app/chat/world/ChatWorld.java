package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.chatObjs.Inserimento;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;

public class ChatWorld {

  private SendButton send;
  private Inserimento inserimento;
  private ChatRender render;

  public ChatWorld() {
    this.inserimento = new Inserimento(172, 100, 100, 0);
    this.send = new SendButton(inserimento, 100, 100, 0, 0);

  }

  public void update(float delta) {
    //Gdx.app.log("ChatWorld", "update");
    render.getStage().act(delta);
  }

  public void setRender(ChatRender render) {
    this.render = render;
  }

  public SendButton getSendButton() {
    return send;
  }

  public Inserimento getInserimento() {
    return inserimento;
  }
}

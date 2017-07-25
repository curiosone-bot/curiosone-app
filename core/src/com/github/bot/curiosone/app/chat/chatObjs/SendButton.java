package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class SendButton extends TextButton {

  private Inserimento inserimento;

  public SendButton(Inserimento inserimento, float width, float height, float x, float y) {
    super("send", new Skin(Gdx.files.internal("chat-asset/uiskin.json")));
    this.setPosition(x, y);
    this.setSize(width, height);
    this.inserimento = inserimento;
    this.addListener(this.click());
  }

  private void onClick() {
    inserimento.setText("");
    this.addListener(this.click());
  }

  private ClickListener click() {
    return new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        onClick();
      }
    };
  }
}

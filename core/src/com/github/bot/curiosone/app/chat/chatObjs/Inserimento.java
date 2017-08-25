package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Inserimento extends TextField {

  public Inserimento(int width, int height, int x, int y) {
    super("", new Skin(Gdx.files.internal("chat-asset/uiskin.json")));
    this.setSize(width, height);
    this.setPosition(x,y);
  }
}

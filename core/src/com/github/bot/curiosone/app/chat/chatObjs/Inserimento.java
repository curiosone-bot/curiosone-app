package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;

public class Inserimento extends TextField {

  public Inserimento(int width, int height, int x, int y, Skin skin) {
    super("", skin);
    this.setSize(width, height);
    this.setPosition(x,y);
    this.getStyle().background.setLeftWidth(this.getStyle().background.getLeftWidth()+20);
    this.getStyle().background.setRightWidth(this.getStyle().background.getRightWidth()+20);
  }
}

package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.chatObjs.Inserimento;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;


public class ChatWorld {

  private SendButton send;
  private Inserimento inserimento;
  private ChatRender render;
  private Table table;
  private ScrollPane scrollpane;
  private Skin skin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));  // Da mettere in AssetLoader
  public Table table2 = new Table();

  public ChatWorld() {
    this.inserimento = new Inserimento(172, 100, 100, 0);
    this.table = new Table();
    this.send = new SendButton(inserimento, 100, 100, 0, 0, table2);
    table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getWidth() - send.getWidth());
    table.setPosition(0, 204);
    table2.setSize(table.getWidth(), table.getHeight());
    scrollpane = new ScrollPane(table2, skin);
    scrollpane.setSize(table.getWidth(), table.getHeight());
    table.add(scrollpane).bottom().width(table.getWidth()).height(table.getHeight());
    scrollpane.setScrollingDisabled(true, false);
    scrollpane.setupFadeScrollBars(0, 0);
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

  public Table getTable() {
    return table;
  }

  public ScrollPane getScrollpane()
  {return scrollpane;}
}

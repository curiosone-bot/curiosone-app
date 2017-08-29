package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.chatObjs.Inserimento;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;


public class ChatWorld {

  private SendButton send;
  private Inserimento inserimento;
  private ChatRender render;
  private Table table;
  private ScrollPane scrollpane;
  public Table scrollTable = new Table();

  public ChatWorld() {
    this.inserimento = new Inserimento(347, 98, 0, 0);
    this.table = new Table();
    this.send = new SendButton(132, 98, 347, 0);
    table.setSize(480, 800 - send.getHeight());
    table.setPosition(0, send.getY() + send.getHeight());
    scrollTable.setSize(table.getWidth(), table.getHeight());
    scrollpane = new ScrollPane(scrollTable, new Skin(Gdx.files.internal("chat-asset/Skin.json")));
    scrollpane.setSize(table.getWidth(), table.getHeight());
    table.add(scrollpane).bottom().width(table.getWidth()).height(table.getHeight());
    scrollpane.setScrollingDisabled(true, false);
    scrollpane.setupFadeScrollBars(0, 0);
  }

  public void update(float delta) {
    //Gdx.app.log("ChatWorld", "update");
    render.getStage().act(delta);
    inserimento.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight());
    scrollpane.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight());
    send.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight());
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

  public Table getScrollTable() {
    return scrollTable;
  }
}


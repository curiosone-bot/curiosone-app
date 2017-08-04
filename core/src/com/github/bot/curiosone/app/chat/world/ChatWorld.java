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
  private List lista;

  public ChatWorld() {
     lista=new List(skin);
    this.inserimento = new Inserimento(172, 100, 100, 0);
    this.table = new Table();



    this.send = new SendButton(inserimento, 100, 100, 0, 0, table);
    table.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getWidth()-send.getWidth());
    table.setPosition(0, 204);
    scrollpane =new ScrollPane(lista,skin);
    scrollpane.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    scrollpane.setPosition(0,-send.getHeight());

    table.add(scrollpane);
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

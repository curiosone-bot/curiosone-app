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
  private Skin skin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));  // Da mettere in AssetLoader
  public Table scrollTable = new Table();
  private Skin skinScrollPane = new Skin(Gdx.files.internal("chat-asset/bgScrollPane.json"));  // Da mettere in AssetLoader

  public ChatWorld() {
    this.inserimento = new Inserimento(172, 100, 0, 0);
   /* inserimento.setOnscreenKeyboard(new TextField.OnscreenKeyboard() {
      @Override
      public void show(boolean visible) {
        //Gdx.input.setOnscreenKeyboardVisible(true);
        Gdx.input.getTextInput(new Input.TextInputListener() {
          @Override
          public void input(String text) {
            inserimento.setText(text);
          }

          @Override
          public void canceled() {
            System.out.println("Cancelled.");
          }
        }, "talk to curiosoneBot", "","insert text...");
      }
    });*/
    inserimento.setOnscreenKeyboard(new TextField.OnscreenKeyboard()
    {
      public void show(boolean visible)
      {
        //richiamo metodo per spostare
      }
    }
    );
    this.table = new Table();
    this.send = new SendButton(100, 100, 172, 0);
    table.setSize(272, 408 - send.getHeight());
    table.setPosition(0, send.getY()+send.getHeight());
    scrollTable.setSize(table.getWidth(), table.getHeight());
    scrollpane = new ScrollPane(scrollTable, skinScrollPane);
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

  public Table getScrollTable() {
    return scrollTable;
  }

}


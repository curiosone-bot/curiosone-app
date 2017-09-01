package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.chatObjs.Inserimento;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;


public class ChatWorld {

  private SendButton send;
  private Inserimento inserimento;
  private ChatRender render;
  private ScrollPane scrollpane;
  public Table scrollTable = new Table();
  public Image bg = new Image(AssetLoader.bg);
  private TextButton gameButton;
  private TextButton optionButton;
  private int cliccato=2;

  public ChatWorld() {
    gameButton = new TextButton("", AssetLoader.defaultSkin);
    gameButton.setPosition(54, 727);
    gameButton.setSize(75, 30);

    optionButton = new TextButton("", AssetLoader.defaultSkin);
    optionButton.setPosition(354, 727);
    optionButton.setSize(75, 30);

    this.inserimento = new Inserimento(290, 80, 45, 40);
    this.send = new SendButton(75, 58, 362, 52);

    scrollpane = new ScrollPane(scrollTable, AssetLoader.skin);
    scrollpane.setPosition(45, inserimento.getY() + inserimento.getHeight() + 20);   // 20 = textField offset
    scrollpane.setSize(390, 700 - scrollpane.getY());
    scrollpane.setScrollingDisabled(true, false);
    scrollpane.setupFadeScrollBars(0, 0);
  }

  public void update(float delta) {
    //Gdx.app.log("ChatWorld", "update");


    render.getStage().act(delta);
    inserimento.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight() + getIncremento(40));
    scrollpane.setY(inserimento.getY()+ inserimento.getHeight() + 20);
    scrollpane.setHeight(700 - inserimento.getY() - inserimento.getHeight() - 20);
    send.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight() + getIncremento(52));

    if(Chat.keyboard) {
      if (cliccato > 0) {
        scrollpane.scrollTo(0, 0, 0, 0);
        cliccato--;
      }
    }
    else {
      cliccato = 2;
    }
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


  public ScrollPane getScrollpane()
  {return scrollpane;}

  public Table getScrollTable() {
    return scrollTable;
  }

  public Image getBg() {
    return bg;
  }

  public TextButton getGameButton() {
    return gameButton;
  }

  public TextButton getOptionButton() {
    return optionButton;
  }

  public int getIncremento(int n) {
    if(Chat.keyboardHeight != 0) {
      return 17;
    }
    else {

      return n;
    }
  }
}


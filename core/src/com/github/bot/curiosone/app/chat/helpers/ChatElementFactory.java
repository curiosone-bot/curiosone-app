package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.chat.chatObjs.Inserimento;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;

import java.io.IOException;

/*
 * this class is used for create all assets used in program but the difference
 * between one to another is that every style has a particular position and other thing
 * that need to be refreshed every time that style change.
 * */
public class ChatElementFactory {
  private static StyleEnum style;

  public static void setStyle(StyleEnum sstyle) {
    style = sstyle;
  }

  public static ImageButton getGameButton() {
    ImageButton gameButton = new ImageButton(AssetLoader.skin.get("gamecenter", ImageButton.ImageButtonStyle.class));
    gameButton.addListener(ScreenManager.getListener(ScreenEnum.GAMECENTER));
    switch (style) {
      case MODERN :
        gameButton.setPosition(368, 717);
        return gameButton;
      case FUTURISTIC :
        gameButton.setPosition(50, 710);
        return gameButton;
      default: return null;
    }
  }

  public static int getPadBottomInserimento() {
    switch (style) {
      case MODERN :
        return 10;
      case FUTURISTIC :
        return 40;
      default: return 0;
    }
  }

  public static int getPadBottomSend() {
    switch (style) {
      case MODERN :
        return 10;
      case FUTURISTIC :
        return 52;
      default: return 0;
    }
  }

  public static Button getMenuButton() {
    ImageButton menuButton = new ImageButton(AssetLoader.skin.get("menu", ImageButton.ImageButtonStyle.class));
    switch (style) {
      case MODERN :
        menuButton.setPosition(0, 717);
        menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));
        break;
      case FUTURISTIC :
        menuButton.setPosition(332, 710);
        menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));
        break;
    }
    return menuButton;
  }

  public static SendButton getSendButton() {
    switch (style) {
      case MODERN :
        try {
          return new SendButton(71, 64, 402, 10, AssetLoader.skin.get("send", ImageButton.ImageButtonStyle.class));
        } catch (IOException e) {
          e.printStackTrace();
        }
      case FUTURISTIC :
        try {
          return new SendButton(75, 58, 362, 52, AssetLoader.skin.get("send", ImageButton.ImageButtonStyle.class));
        } catch (IOException e) {
          e.printStackTrace();
        }
      default: return null;
    }
  }

  public static Inserimento getInserimento() {
    switch (style) {
      case MODERN :
        return new Inserimento(386, 71, 10, 17, AssetLoader.skin);
      case FUTURISTIC :
        return new Inserimento(290, 80, 45, 40, AssetLoader.skin);
      default: return null;
    }
  }

  public static Image getChatBackground() {
    return AssetLoader.bg;
  }

  public static Image getMenuBackground() {
    return AssetLoader.menuBg;
  }

  public static Label getLabel(String text, Label.LabelStyle labelStyle) {
    Label l = new Label(text, labelStyle);
    l.setFontScale(0.9f);
    return l;
  }

  public static TextButton getButton(String name, String buttonStyle) {
    switch (style) {
      case MODERN :
        return new TextButton(name, AssetLoader.skin.get("textButton1", TextButton.TextButtonStyle.class));
      case FUTURISTIC :
        return new TextButton(name, AssetLoader.skin.get(buttonStyle, TextButton.TextButtonStyle.class));
      default: return null;
    }
  }

  public static TextButton getButton(String name, String buttonStyle, float fontScale) {
    TextButton tb=getButton(name , buttonStyle);
    tb.getLabel().setFontScale(fontScale);
    return tb;
  }

  public static ScrollPane getScrollPane(Table scrollTable, Inserimento inserimento) {
    ScrollPane scrollPane;
    switch (style) {
      case MODERN :
        scrollPane = new ScrollPane(scrollTable, AssetLoader.skin);
        scrollPane.setPosition(20, 97);   // 20 = textField offset
        scrollPane.setSize(430, 620);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setupFadeScrollBars(0, 0);
        return scrollPane;
      case FUTURISTIC :
        scrollPane = new ScrollPane(scrollTable, AssetLoader.skin);
        scrollPane.setPosition(45, inserimento.getY() + inserimento.getHeight() + 20);   // 20 = textField offset
        scrollPane.setSize(390, 700 - scrollPane.getY());
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setupFadeScrollBars(0, 0);
        return scrollPane;
      default: return null;
    }
  }

  public enum StyleEnum {
    FUTURISTIC(0), MODERN(1);

    private int id;
    StyleEnum(int id) {
      this.id = id;
    }
    public int getValue() {
      return id;
    }
    public static StyleEnum getType(int id) {
      return values()[id];
    }
  }
}

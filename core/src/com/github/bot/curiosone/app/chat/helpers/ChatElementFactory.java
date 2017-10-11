package com.github.bot.curiosone.app.chat.helpers;

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
        gameButton.setPosition(400, 708);
        gameButton.setSize(92, 92);
        return gameButton;
      case FUTURISTIC :
        gameButton.setPosition(54, 727);
        gameButton.setSize(75, 30);
        return gameButton;
      default: return null;
    }
  }

  public static Button getMenuButton() {
    switch (style) {
      case MODERN :
        ImageButton menuButton = new ImageButton(AssetLoader.skin.get("menu", ImageButton.ImageButtonStyle.class));
        menuButton.setPosition(0, 708);
        menuButton.setSize(92, 92);
        menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));
        return menuButton;
      case FUTURISTIC :
        TextButton menuButtonFuturistic = new TextButton("", AssetLoader.defaultSkin);
        menuButtonFuturistic.setPosition(354, 727);
        menuButtonFuturistic.setSize(75, 30);
        menuButtonFuturistic.addListener(ScreenManager.getListener(ScreenEnum.MENU));
        return menuButtonFuturistic;
      default: return null;
    }
  }

  public static SendButton getSendButton() {
    switch (style) {
      case MODERN :
        try {
          return new SendButton(60, 65, 400, 20, AssetLoader.skin.get("send", ImageButton.ImageButtonStyle.class));
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
        return new Inserimento(365, 60, 18, 20, AssetLoader.skin);
      case FUTURISTIC :
        return new Inserimento(290, 80, 45, 40, AssetLoader.skin);
      default: return null;
    }
  }

  public static Image getChatBackground() {
    return new Image(AssetLoader.bg);
  }

  public static Label getLabel(String text, Label.LabelStyle labelStyle) {
    Label l = new Label(text, labelStyle);
    l.setFontScale(0.9f);
    return l;
  }

  public static ScrollPane getScrollPane(Table scrollTable, Inserimento inserimento) {
    ScrollPane scrollPane;
    switch (style) {
      case MODERN :
        scrollPane = new ScrollPane(scrollTable, AssetLoader.defaultSkin);
        scrollPane.setPosition(45, inserimento.getY() + inserimento.getHeight() + 20);   // 20 = textField offset
        scrollPane.setSize(390, 700 - scrollPane.getY());
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

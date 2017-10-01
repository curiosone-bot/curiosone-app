package com.github.bot.curiosone.app.chat.helpers;

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
  private static StyleEnum style = StyleEnum.FUTURISTIC;

  public static void setStyle(StyleEnum style) {
    style = style;
  }

  public static ImageButton getGameButton() {
    switch (style) {
      case MODERN :
        return null;
      case FUTURISTIC :
        ImageButton gameButton = new ImageButton(AssetLoader.skin.get("gamecenter", ImageButton.ImageButtonStyle.class));
        gameButton.setPosition(54, 727);
        gameButton.setSize(75, 30);
        gameButton.addListener(ScreenManager.getListener(ScreenEnum.GAMECENTER));
        return gameButton;
      default: return null;
    }
  }

  public static TextButton getMenuButton() {
    switch (style) {
      case MODERN :
        return null;
      case FUTURISTIC :
        TextButton menuButton = new TextButton("", AssetLoader.defaultSkin);
        menuButton.setPosition(354, 727);
        menuButton.setSize(75, 30);
        menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));
        return menuButton;
      default: return null;
    }
  }

  public static SendButton getSendButton() {
    switch (style) {
      case MODERN :
        return null;
      case FUTURISTIC :
        try {
          return new SendButton(75, 58, 362, 52, AssetLoader.skin);
        } catch (IOException e) {
          e.printStackTrace();
        }
      default: return null;
    }
  }

  public static Inserimento getInserimento() {
    switch (style) {
      case MODERN :
      return null;
      case FUTURISTIC :
        return new Inserimento(290, 80, 45, 40, AssetLoader.skin);
      default: return null;
    }
  }

  public static Image getChatBackground() {
    switch (style) {
      case MODERN :
        return null;
      case FUTURISTIC :
        return new Image(AssetLoader.bg);
      default: return null;
    }
  }

  public static Label getLabel(String text, Label.LabelStyle labelStyle) {
    switch (style) {
      case MODERN :
        return null;
      case FUTURISTIC :
        Label l = new Label(text, labelStyle);
        l.setFontScale(0.9f);
        return l;
      default: return null;
    }
  }

  public static ScrollPane getScrollPane(Table scrollTable, Inserimento inserimento) {
    switch (style) {
      case MODERN :
        return null;
      case FUTURISTIC :
        ScrollPane scrollPane = new ScrollPane(scrollTable, AssetLoader.skin);
        scrollPane.setPosition(45, inserimento.getY() + inserimento.getHeight() + 20);   // 20 = textField offset
        scrollPane.setSize(390, 700 - scrollPane.getY());
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setupFadeScrollBars(0, 0);
        return scrollPane;
      default: return null;
    }
  }

  private enum StyleEnum {
    FUTURISTIC, MODERN;
  }
}

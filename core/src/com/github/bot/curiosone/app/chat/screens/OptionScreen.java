package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;

public class OptionScreen extends AbstractScreen {
  private TextButton style1, style2, style3, menuButton;

  @Override
  public void buildStage() {
    style1 = new TextButton("style1", AssetLoader.defaultSkin);
    style2 = new TextButton("style2", AssetLoader.defaultSkin);
    style3 = new TextButton("style3", AssetLoader.defaultSkin);
    menuButton = new TextButton("Menu", AssetLoader.defaultSkin);

    menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));

    reorder(250, 55, menuButton, style1, style2, style3);
    addActors(style1, style2, style3, menuButton);
  }
}

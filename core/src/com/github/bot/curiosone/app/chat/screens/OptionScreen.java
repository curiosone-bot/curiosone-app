package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;

public class OptionScreen extends AbstractScreen {
  private TextButton textButton = new TextButton("ascasc", AssetLoader.defaultSkin);

  @Override
  public void buildStage() {
    textButton.setPosition(0, 0);
    textButton.setSize(100,100);
    addActor(textButton);
  }
}

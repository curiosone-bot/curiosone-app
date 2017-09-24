package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;

public class OptionScreen extends AbstractScreen {
  private TextButton textButton, textButton2;

  @Override
  public void buildStage() {
    textButton = new TextButton("ascasc", AssetLoader.defaultSkin);
    textButton2 = new TextButton("ascasc", AssetLoader.defaultSkin);
    reorder(250f, 55f, textButton, textButton2);
    addActors(textButton, textButton2);
  }
}

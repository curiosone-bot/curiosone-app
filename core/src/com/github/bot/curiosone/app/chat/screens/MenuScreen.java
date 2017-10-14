package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;

public class MenuScreen extends AbstractScreen {
  private TextButton chatButton, gameButton, optionButton;

  @Override
  public void buildStage() {
    chatButton = new TextButton("Chat", AssetLoader.defaultSkin);
    gameButton = new TextButton("Gamecenter", AssetLoader.defaultSkin);
    optionButton = new TextButton("Option", AssetLoader.defaultSkin);

    chatButton.addListener(ScreenManager.getListener(ScreenEnum.CHAT));
    gameButton.addListener(ScreenManager.getListener(ScreenEnum.GAMECENTER));
    optionButton.addListener(ScreenManager.getListener(ScreenEnum.OPTION));

    reorder(250f, 55f, 300f, optionButton, gameButton, chatButton);
    addActors(chatButton, gameButton, optionButton);
  }

  @Override
  public void entryTransition() {
    this.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.2f)));
  }

}

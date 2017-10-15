package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
/*
 * menu screen is the screen of main menu
 * */
public class MenuScreen extends AbstractScreen {
  private TextButton chatButton, gameButton, optionButton;
  private Image bg;

  @Override
  public void buildStage() {
    chatButton = ChatElementFactory.getButton("Chat", "textButton1");
    gameButton = ChatElementFactory.getButton("Games", "textButton2");
    optionButton = ChatElementFactory.getButton("Styles", "textButton3");
    bg = ChatElementFactory.getMenuBackground();

    chatButton.addListener(ScreenManager.getListener(ScreenEnum.CHAT));
    gameButton.addListener(ScreenManager.getListener(ScreenEnum.GAMECENTER));
    optionButton.addListener(ScreenManager.getListener(ScreenEnum.OPTION));

    reorder(0f, optionButton, gameButton, chatButton);
    addActors(bg, chatButton, gameButton, optionButton);
  }

  @Override
  public void entryTransition() {
    this.addAction(Actions.sequence(Actions.alpha(0.9f), Actions.fadeIn(0.3f)));
  }

  @Override
  public void show() {
    InputProcessor backProcessor = new InputAdapter() {
      @Override
      public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) ) {
          Gdx.app.exit();
        }
        return false;
      }
    };
    InputMultiplexer multiplexer = new InputMultiplexer(backProcessor, this);
    Gdx.input.setInputProcessor(multiplexer);
    Gdx.input.setCatchBackKey(true);
  }

}

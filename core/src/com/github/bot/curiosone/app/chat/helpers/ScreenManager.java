package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;

import java.io.IOException;

public class ScreenManager {
  private static ScreenManager instance;
  private Game game;

  private ScreenManager() {
    super();
  }

  public static ScreenManager getInstance() {
    if (instance == null) {
      instance = new ScreenManager();
    }
    return instance;
  }

  public void initialize(Game game) {
    this.game = game;
  }

  public void showScreen(ScreenEnum screenEnum) throws IOException {
    Screen currentScreen = game.getScreen();
    AbstractScreen newScreen = screenEnum.getScreen();
    newScreen.buildStage();
    game.setScreen(newScreen);
    if(currentScreen != null) {
      currentScreen.dispose();
    }
  }

  public void showScreen(Screen screen) throws IOException {
    Screen currentScreen = game.getScreen();
    Screen newScreen = screen;
    game.setScreen(newScreen);
    if(currentScreen != null) {
      currentScreen.dispose();
    }
  }

}

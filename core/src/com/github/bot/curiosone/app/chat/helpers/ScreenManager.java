package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class ScreenManager {
  private static ScreenManager instance;
  private static Game game;

  private ScreenManager() {
    super();
  }

  public static ScreenManager getInstance() {
    if (instance == null) {
      instance = new ScreenManager();
    }
    return instance;
  }

  public void initialize(Game gameInit) {
    game = gameInit;
  }

  public void showScreen(ScreenEnum screenEnum) {
    showScreen(screenEnum.getScreen());
  }

  public void showScreen(Screen screen) {
    Screen currentScreen = game.getScreen();
    Screen newScreen = screen;
    if (newScreen instanceof Actionable) {
      ((Actionable) newScreen).entryTransition();
      if (newScreen instanceof AbstractScreen) {
        ((AbstractScreen) newScreen).buildStage();
      }
    }
    game.setScreen(newScreen);
    if(currentScreen != null) {
      currentScreen.dispose();
    }
  }

  public static ClickListener getListener(final ScreenEnum screenEnum) {
    return new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button)  {
        ScreenManager.getInstance().showScreen(screenEnum);
      }
    };
  }

  public static Game getGame(){
    return game;
  }

}

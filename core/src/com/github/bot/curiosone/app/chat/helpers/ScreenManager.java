package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    showScreen(screenEnum.getScreen());
  }

  public void showScreen(Screen screen) throws IOException {
    Screen currentScreen = game.getScreen();
    Screen newScreen = screen;
    if (newScreen instanceof BuildableStageScreen) {
      ((BuildableStageScreen) newScreen).buildStage();
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
        try {
          ScreenManager.getInstance().showScreen(screenEnum);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    };
  }

  public Game getGame(){
    return this.game;
  }

}

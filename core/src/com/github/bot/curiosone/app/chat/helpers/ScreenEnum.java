package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Screen;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;
import com.github.bot.curiosone.app.chat.screens.MenuScreen;
import com.github.bot.curiosone.app.chat.screens.OptionScreen;
import com.github.bot.curiosone.app.games.airborneassault.screens.MainMenuScreen;
import com.github.bot.curiosone.app.games.endlessroad.scenes.EndlessRoad;
import com.github.bot.curiosone.app.games.wordcrush.WordCrushSE;
import com.github.bot.curiosone.app.workflow.GameCenter2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 *is the enum of screens
 * */
public enum ScreenEnum {
  CHAT {
    public Screen getScreen() {
      return new ChatScreen();
    }
  },
  GAMECENTER {
    public Screen getScreen() {
      return new GameCenter2();
    }
  },
  MENU {
    public Screen getScreen() {
      return new MenuScreen();
    }
  },
  OPTION {
    public Screen getScreen() {
      return new OptionScreen();
    }
  },
  ENDLESSROAD {
    public Screen getScreen() {
      return new EndlessRoad();
    }
  },
  WORDCRUSH {
    public Screen getScreen() {
      return new com.github.bot.curiosone.app.games.wordcrush.screen.MenuScreen(new WordCrushSE(), (Chat) ScreenManager.getGame());
    }
  },
  AIRBORNASSAULT {
    public Screen getScreen() {
      return new MainMenuScreen((Chat) ScreenManager.getGame());
    }
  };

  private static final List<ScreenEnum> list = Arrays.asList(ENDLESSROAD, WORDCRUSH, AIRBORNASSAULT);
  public abstract Screen getScreen();
  public static ScreenEnum getRandomGame() {
    Random random = new Random();
    return list.get(random.nextInt(list.size()));
  }
}

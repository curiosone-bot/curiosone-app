package com.github.bot.curiosone.app.chat.helpers;

import com.github.bot.curiosone.app.chat.screens.ChatScreen;
import com.github.bot.curiosone.app.chat.screens.MenuScreen;
import com.github.bot.curiosone.app.chat.screens.OptionScreen;
import com.github.bot.curiosone.app.workflow.GameCenter2;

import java.io.IOException;

public enum ScreenEnum {
  CHAT {
    public BuildableStageScreen getScreen() throws IOException {
      return new ChatScreen();
    }
  },
  GAMECENTER {
    public BuildableStageScreen getScreen() throws IOException {
      return new GameCenter2();
    }
  },
  MENU {
    public BuildableStageScreen getScreen() throws IOException {
      return new MenuScreen();
    }
  },
  OPTION {
    public BuildableStageScreen getScreen() throws IOException {
      return new OptionScreen();
    }
  };

  public abstract BuildableStageScreen getScreen() throws IOException;
}

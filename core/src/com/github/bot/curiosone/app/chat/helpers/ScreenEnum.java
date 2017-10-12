package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;
import com.github.bot.curiosone.app.chat.screens.MenuScreen;
import com.github.bot.curiosone.app.chat.screens.OptionScreen;
import com.github.bot.curiosone.app.workflow.GameCenter2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum ScreenEnum {
  CHAT {
    public Screen getScreen() throws IOException {
      return new ChatScreen();
    }
  },
  GAMECENTER {
    public Screen getScreen() throws IOException {
      return new GameCenter2();
    }
  },
  MENU {
    public Screen getScreen() throws IOException {
      return new MenuScreen();
    }
  },
  OPTION {
    public Screen getScreen() throws IOException {
      return new OptionScreen();
    }
  },
  ENDLESSROAD {
    public Screen getScreen() throws IOException {
      return new MenuScreen();
    }
  },
  WORDCRUSH {
    public Screen getScreen() throws IOException {
      return new MenuScreen();
    }
  },
  AIRBORNASSAULT {
    public Screen getScreen() throws IOException {
      return new MenuScreen();
    }
  };

  private static final List<ScreenEnum> list = Arrays.asList(ENDLESSROAD, WORDCRUSH, AIRBORNASSAULT);
  public abstract Screen getScreen() throws IOException;
  public static ScreenEnum getRandomGame() {
    Random random = new Random();
    return list.get(random.nextInt(list.size()));
  }
}

package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Screen;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;
import com.github.bot.curiosone.app.workflow.GameCenter2;

import java.io.IOException;

public enum ScreenEnum {
  CHAT {
    public Screen getScreen() throws IOException {
      return new ChatScreen();
    }
  },
  GAMECENTER {
    public Screen getScreen() throws IOException {
      return new GameCenter2();
    };
  };
  public abstract Screen getScreen() throws IOException;
}

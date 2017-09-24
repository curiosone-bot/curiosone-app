package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;

public enum Listeners {
  CHAT {
    public InputListener getListener() {
      return new ClickListener() {
        @Override
        public void touchUp(InputEvent e, float x, float y, int point, int button)  {
          try {
            ScreenManager.getInstance().showScreen(ScreenEnum.CHAT);
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      };
    }
  },
  GAMECENTER {
    public InputListener getListener() {
      return new ClickListener() {
        @Override
        public void touchUp(InputEvent e, float x, float y, int point, int button)  {
          try {
            ScreenManager.getInstance().showScreen(ScreenEnum.GAMECENTER);
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      };
    }
  },
  MENU {
    public InputListener getListener() {
      return new ClickListener() {
        @Override
        public void touchUp(InputEvent e, float x, float y, int point, int button)  {
          try {
            ScreenManager.getInstance().showScreen(ScreenEnum.MENU);
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
      };
    }
  };

  public abstract InputListener getListener();
}

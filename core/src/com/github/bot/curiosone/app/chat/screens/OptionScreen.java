package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.world.ChatWorld;

import java.io.IOException;

import static com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum.*;

public class OptionScreen extends AbstractScreen {
  private TextButton style1, style2, style3, menuButton;

  @Override
  public void buildStage() {
    final Preferences prefs = Gdx.app.getPreferences("Preferences");

    style1 = new TextButton("style1", AssetLoader.defaultSkin);
    style2 = new TextButton("style2", AssetLoader.defaultSkin);
    style3 = new TextButton("style3", AssetLoader.defaultSkin);
    menuButton = new TextButton("Menu", AssetLoader.defaultSkin);

    menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));
    style1.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        ChatWorld.resetScrollpane();
        AssetLoader.load(FUTURISTIC);
        prefs.putInteger("style", 0);
        prefs.flush();
        try {
          ScreenManager.getInstance().showScreen(ScreenEnum.MENU);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });

    style2.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        ChatWorld.resetScrollpane();
        AssetLoader.load(MODERN);
        prefs.putInteger("style", 1);
        prefs.flush();
        try {
          ScreenManager.getInstance().showScreen(ScreenEnum.MENU);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });

    reorder(250, 55, menuButton, style1, style2, style3);
    addActors(style1, style2, style3, menuButton);
  }
}

package com.github.bot.curiosone.app.chat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.world.ChatWorld;

import static com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum.*;
import static com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum;
/*
 * Option Screen is the screen of options
 * */
public class OptionScreen extends AbstractScreen {
  private TextButton style1, style2, style3, menuButton;
  private Dialog dialog;
  private Preferences prefs;
  private StyleEnum selectedStyle;
  private Image bg;

  @Override
  public void buildStage() {
    this.prefs = Gdx.app.getPreferences("Preferences");

    style1 = ChatElementFactory.getButton("Futuristic", "textButton1");
    style2 = ChatElementFactory.getButton("Modern", "textButton2");
    style3 = ChatElementFactory.getButton("Dark Blu","textButton3");
    menuButton = new TextButton("Menu da eliminare", AssetLoader.defaultSkin);
    bg = ChatElementFactory.getMenuBackground();

    this.dialog =
      new Dialog("", AssetLoader.defaultSkin, "dialog") {
        protected void result (Object object) {
          dialogThen((boolean) object, selectedStyle);
        }
      };

    dialog.padTop(20).padBottom(20);
    dialog.text("Are you sure to change style? \n (if you do that, chat will be deleted)").button("Yes", true).button("No", false).key(Input.Keys.ENTER, true).key(Input.Keys.ESCAPE, false);

    menuButton.addListener(ScreenManager.getListener(ScreenEnum.MENU));
    style1.addListener(getStyleClickListener(FUTURISTIC, style1));

    style2.addListener(getStyleClickListener(MODERN, style2));

    reorder(0f, menuButton, style1, style2, style3);
    addActors(bg, style1, style2, style3, menuButton);
  }

  private void dialogThen(boolean b, StyleEnum style) {
    if (b) {
      ChatWorld.resetScrollpane();
      AssetLoader.load(style);
      prefs.putInteger("style", style.getValue());
      prefs.flush();
      ScreenManager.getInstance().showScreen(ScreenEnum.MENU);
    }
  }

  private ClickListener getStyleClickListener(final StyleEnum styleEnum, final Button styleButton) {
    return new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        selectedStyle = styleEnum;
        dialog.show(OptionScreen.this);
        styleButton.addListener(getStyleClickListener(styleEnum,styleButton));
      }
    };
  }


  @Override
  public void entryTransition() {
    this.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.2f)));
  }

}

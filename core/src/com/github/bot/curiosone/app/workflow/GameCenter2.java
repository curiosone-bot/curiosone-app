package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.Backable;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.games.airborneassault.screens.MainMenuScreen;
import com.github.bot.curiosone.app.games.endlessroad.scenes.EndlessRoad;
import com.github.bot.curiosone.app.games.wordcrush.WordCrushSE;
import com.github.bot.curiosone.app.games.wordcrush.screen.MenuScreen;
/*
 * this class is the gamecenter screen
 * */
public class GameCenter2 extends AbstractScreen implements Backable {
  private static Screen prevScreen;
  private TextButton arkanoid,wordCrush,endlessRoad, buildWords,chat, airborneassault;
  private Image bg;

  @Override
  public void buildStage() {
    airborneassault = ChatElementFactory.getButton("Airborneassault", "textButton1", 0.55f);
    arkanoid = ChatElementFactory.getButton("Arkanoid", "textButton1", 0.55f);
    wordCrush = ChatElementFactory.getButton("WordCrush", "textButton1", 0.55f);
    endlessRoad = ChatElementFactory.getButton("EndlessRoad", "textButton1", 0.55f);
    buildWords = ChatElementFactory.getButton("BuildWords", "textButton1", 0.55f);
    chat = ChatElementFactory.getButton("Chat", "textButton1", 0.55f);
    bg = ChatElementFactory.getMenuBackground();

    airborneassault.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.showScreen(new MainMenuScreen((Chat) sm.getGame()));
      }
    });
    arkanoid.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    wordCrush.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.showScreen( new MenuScreen(new WordCrushSE(), (Chat) sm.getGame()));
      }
    });
    endlessRoad.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.showScreen( new EndlessRoad());
      }
    });
    buildWords.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    chat.addListener(ScreenManager.getListener(ScreenEnum.CHAT));

    reorder(250, 55, chat, buildWords, endlessRoad, wordCrush, arkanoid, airborneassault);
    addActors(bg, chat, buildWords, endlessRoad, wordCrush, arkanoid, airborneassault);
  }

  @Override
  public void dispose() {
    super.dispose();
  }

  @Override
  public void entryTransition() {
    this.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.2f)));
  }

  @Override
  public void show() {
    InputProcessor backProcessor = new InputAdapter() {
      @Override
      public boolean keyDown(int keycode) {
        if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) ) {
          System.out.print(true);
          ScreenManager.getInstance().showScreen(prevScreen);
        }
        return false;
      }
    };
    InputMultiplexer multiplexer = new InputMultiplexer(backProcessor, this);
    Gdx.input.setInputProcessor(multiplexer);
    Gdx.input.setCatchBackKey(true);
  }

  @Override
  public void setPrevScreen(Screen screen) {
    prevScreen = screen;
  }
}

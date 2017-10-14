package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.games.airborneassault.screens.MainMenuScreen;
import com.github.bot.curiosone.app.games.endlessroad.scenes.EndlessRoad;
import com.github.bot.curiosone.app.games.wordcrush.WordCrushSE;
import com.github.bot.curiosone.app.games.wordcrush.screen.MenuScreen;

public class GameCenter2 extends AbstractScreen {
  private TextButton arkanoid,wordCrush,endlessRoad, buildWords,chat, airborneassault;

  @Override
  public void buildStage() {
    airborneassault = new TextButton("Airborneassault", AssetLoader.defaultSkin);
    arkanoid = new TextButton("Arkanoid", AssetLoader.defaultSkin);
    wordCrush = new TextButton("WordCrush", AssetLoader.defaultSkin);
    endlessRoad = new TextButton("EndlessRoad", AssetLoader.defaultSkin);
    buildWords = new TextButton("BuildWords", AssetLoader.defaultSkin);
    chat = new TextButton("Chat", AssetLoader.defaultSkin);


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
    addActors(chat, buildWords, endlessRoad, wordCrush, arkanoid, airborneassault);
  }

  @Override
  public void dispose() {
    super.dispose();
  }

  @Override
  public void entryTransition() {
    this.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.2f)));
  }


}

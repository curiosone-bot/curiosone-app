package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.chat.helpers.AbstractScreen;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;

import java.io.IOException;


public class GameCenter2 extends AbstractScreen {
  private TextButton wordTiles,arkanoid,wordCrush,endlessRoad, buildWords,chat;

  @Override
  public void buildStage() {
    wordTiles = new TextButton("WordTiles", AssetLoader.defaultSkin);
    arkanoid = new TextButton("Arkanoid", AssetLoader.defaultSkin);
    wordCrush = new TextButton("WordCrush", AssetLoader.defaultSkin);
    endlessRoad = new TextButton("EndlessRoad", AssetLoader.defaultSkin);
    buildWords = new TextButton("BuildWords", AssetLoader.defaultSkin);
    chat = new TextButton("Chat", AssetLoader.defaultSkin);

    wordTiles.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

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

      }
    });
    endlessRoad.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    buildWords.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    chat.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button)  {
        try {
          ScreenManager.getInstance().showScreen(ScreenEnum.CHAT);
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });

    reorder(250, 55, chat, buildWords, endlessRoad, wordCrush, arkanoid, wordTiles);
    addActors(chat, buildWords, endlessRoad, wordCrush, arkanoid, wordTiles);
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}

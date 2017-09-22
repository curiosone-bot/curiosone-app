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
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;

import java.io.IOException;


public class GameCenter2 extends AbstractScreen {
  private TextButton wordTiles,arkanoid,wordCrush,endlessRoad, buildWords,chat;

  @Override
  public void buildStage() {
    wordTiles = CreateButton("WordTiles",480/2-250/2,800/2,250,55);
    wordTiles.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    arkanoid = CreateButton("Arkanoid",480/2-250/2,800/2-60,250,55);
    arkanoid.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    wordCrush = CreateButton("WordCrush",480/2-250/2,800/2-120,250,55);
    wordCrush.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    endlessRoad = CreateButton("EndlessRoad",480/2-250/2,800/2-180,250,55);
    endlessRoad.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    buildWords = CreateButton("BuildWords",480/2-250/2,800/2-240,250,55);
    buildWords.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {

      }
    });
    chat = CreateButton("Chat",480/2-250/2,800/2-300,250,55);
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

    addActor(wordTiles);
    addActor(arkanoid);
    addActor(wordCrush);
    addActor(endlessRoad);
    addActor(buildWords);
    addActor(chat);
  }

  private TextButton CreateButton(String name, float x, float y, int width, int height) {
    TextButton b;
    b = new TextButton(name, com.github.bot.curiosone.app.chat.helpers.AssetLoader.defaultSkin);
    b.setPosition(x,y);
    b.setSize(width,height);
    return b;
  }

  @Override
  public void dispose() {
    super.dispose();
  }
}

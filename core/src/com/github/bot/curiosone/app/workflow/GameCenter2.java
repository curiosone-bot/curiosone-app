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
import com.github.bot.curiosone.app.chat.screens.ChatScreen;

import java.io.IOException;

/**
 * Created by federico-pc on 15/09/2017.
 */

public class GameCenter2 extends ScreenAdapter {
  private SpriteBatch batch;
  private OrthographicCamera camera;
  private TextButton wordTiles,arkanoid,wordCrush,endlessRoad, buildWords,chat;
  private StretchViewport viewp;
  private Stage stage;

  public GameCenter2(final Game game) throws IOException{
    this.batch = new SpriteBatch();

    /*Camera Settings*/
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480/2,800/2,0);
    viewp = new StretchViewport(480, 800, camera);
    //480/2-250/2,800/2,250,55
    /*Button Areas*/
    wordTiles = CreateButton("WordTiles",480/2-250/2,800/2,250,55);
    wordTiles.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        try {  //Mettete qui sotto lo screen principale
          game.setScreen(new GameCenter2(game));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    arkanoid = CreateButton("Arkanoid",480/2-250/2,800/2-60,250,55);
    arkanoid.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        try { //Mettete qui sotto lo screen principale
          game.setScreen(new GameCenter2(game));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    wordCrush = CreateButton("WordCrush",480/2-250/2,800/2-120,250,55);
    wordCrush.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        try { //Mettete qui sotto lo screen principale
          game.setScreen(new GameCenter2(game));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    endlessRoad = CreateButton("EndlessRoad",480/2-250/2,800/2-180,250,55);
    endlessRoad.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        try { //Mettete qui sotto lo screen principale
          game.setScreen(new GameCenter2(game));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    buildWords = CreateButton("Bottone4",480/2-250/2,800/2-240,250,55);
    buildWords.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        try { //Mettete qui sotto lo screen principale
          game.setScreen(new GameCenter2(game));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    chat = CreateButton("Chat",480/2-250/2,800/2-300,250,55);
    chat.addListener(new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button)  {
        try {
          game.setScreen(new ChatScreen(game));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
    this.stage = new Stage(viewp, batch);
    Gdx.input.setInputProcessor(stage);

    stage.addActor(wordTiles);
    stage.addActor(arkanoid);
    stage.addActor(wordCrush);
    stage.addActor(endlessRoad);
    stage.addActor(buildWords);
    stage.addActor(chat);

  }

  private TextButton CreateButton(String name, float x, float y, int width, int height) {
    TextButton b;
    b = new TextButton(name, com.github.bot.curiosone.app.chat.helpers.AssetLoader.defaultSkin);
    b.setPosition(x,y);
    b.setSize(width,height);
    return b;
  }

  @Override
  public void render(float dt) {
    update();
    draw();
  }

  private void draw() {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
  }

  @Override
  public void dispose() {
    super.dispose();
    stage.dispose();
  }

  private void update() {
    camera.update();
    stage.act();
  }


}

package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by federico-pc on 15/09/2017.
 */

public class GameCenter2 extends ScreenAdapter {

  private SpriteBatch batch;
  private Game game;
  private OrthographicCamera camera;
  private Vector3 touch;
  private Button wordTiles,Arkanoid,WordCrush,EndlessRoad,bottone4,chat;
  private Texture buttonTexture;
  private StretchViewport viewp;
  private Stage stage;
  public GameCenter2(Game game){

    this.game = game;
    this.batch = new SpriteBatch();

    /*Camera Settings*/
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480/2,800/2,0);
    viewp = new StretchViewport(480, 800, camera);
    //480/2-250/2,800/2,250,55
    /*Button Areas*/
    wordTiles = CreateButton("wordTiles",480/2-250/2,800/2,250,55);
    Arkanoid = CreateButton("Arkanoid",480/2-250/2,800/2-60,250,55);
    WordCrush = CreateButton("WordCrush",480/2-250/2,800/2-120,250,55);
    EndlessRoad = CreateButton("EndlessRoad",480/2-250/2,800/2-180,250,55);
    bottone4 = CreateButton("bottone4",480/2-250/2,800/2-240,250,55);
    chat = CreateButton("chat",480/2-250/2,800/2-300,250,55);

    this.stage = new Stage(viewp, batch);
    Gdx.input.setInputProcessor(stage);

    stage.addActor(wordTiles);

  }

  private Button CreateButton(String name,float x,float y,int width,int height)
  {
    Button b;
    b=new Button(com.github.bot.curiosone.app.chat.helpers.AssetLoader.skin);
    b.setPosition(x,y);
    b.setSize(width,height);
    b.setName(name);
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
    camera.update();
    batch.begin();
   /* batch.draw(buttonTexture, wordTiles.getX(),wordTiles.getY(),wordTiles.getWidth(),wordTiles.getHeight());
    batch.draw(buttonTexture,Arkanoid.getX(),Arkanoid.getY(),Arkanoid.getWidth(),Arkanoid.getHeight());
    batch.draw(buttonTexture,WordCrush.getX(),WordCrush.getY(),WordCrush.getWidth(),WordCrush.getHeight());
    batch.draw(buttonTexture,EndlessRoad.getX(),EndlessRoad.y,EndlessRoad.width,EndlessRoad.height);
    batch.draw(buttonTexture,bottone4.getX(),bottone4.y,bottone4.width,bottone4.height);
    batch.draw(buttonTexture,chat.x,chat.y,chat.width,chat.height);*/
    batch.end();
    drawButtons(wordTiles);
    drawButtons(Arkanoid);
    drawButtons(WordCrush);
    drawButtons(EndlessRoad);
    drawButtons(bottone4);
    drawButtons(chat);
  }

  private void drawButtons(Button b)
  {
      stage.addActor(b);
  }

  @Override
  public void dispose() {
    batch.dispose();
  }

  private ClickListener update()
  {

  }


}

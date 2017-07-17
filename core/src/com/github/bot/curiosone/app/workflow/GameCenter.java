package com.github.bot.curiosone.app.workflow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.github.bot.curiosone.app.workflow.com.curiosone.WordTilesSrc.Screens.MainMenuScreen;

public class GameCenter extends ScreenAdapter {

  private Chat game;
  private OrthographicCamera camera;
  private Vector3 touch;
  private Rectangle wordTiles,Arkanoid,WordCrush,EndlessRoad,bottone4;
  private Texture buttonTexture;

  public GameCenter(Chat game){
    this.game = game;

    /*Camera Settings*/
    camera = new OrthographicCamera();
    camera.setToOrtho(false,480,800);
    camera.position.set(480/2,800/2,0);

    /*Button Areas*/
    wordTiles = new Rectangle(480/2-250/2,800/2,250,55);
    Arkanoid = new Rectangle(480/2-250/2,800/2-60,250,55);
    WordCrush = new Rectangle(480/2-250/2,800/2-120,250,55);
    EndlessRoad = new Rectangle(480/2-250/2,800/2-180,250,55);
    bottone4 = new Rectangle(480/2-250/2,800/2-240,250,55);
    buttonTexture = new Texture("green_button00.png");
    touch = new Vector3();
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
    game.getBatch().begin();
    game.getBatch().draw(buttonTexture, wordTiles.x,wordTiles.y,wordTiles.width,wordTiles.height);
    game.getBatch().draw(buttonTexture,Arkanoid.x,Arkanoid.y,Arkanoid.width,Arkanoid.height);
    game.getBatch().draw(buttonTexture,WordCrush.x,WordCrush.y,WordCrush.width,WordCrush.height);
    game.getBatch().draw(buttonTexture,EndlessRoad.x,EndlessRoad.y,EndlessRoad.width,EndlessRoad.height);
    game.getBatch().draw(buttonTexture,bottone4.x,bottone4.y,bottone4.width,bottone4.height);
    game.getBatch().end();
  }

  /**
   *  Update controlla l'input. Se una casella viene toccata, sarà istanziato
   *  il gioco corrispondente
   */
  private void update() {

    if(Gdx.input.isTouched()) {  //If receives input
      touch = touch.set(Gdx.input.getX(),Gdx.input.getY(),0); //Sets its coordinates to the touch coordinates
      camera.unproject(touch);   //Transforms the input coordinates to camera coordinates
      if(wordTiles.contains(touch.x,touch.y)) {
        Gdx.app.log("Touched","WordTiles");
        game.setScreen(new MainMenuScreen(game));
      }
      if(Arkanoid.contains(touch.x,touch.y)) {
        //game.setScreen() <-- place your game here
      }
      if(WordCrush.contains(touch.x,touch.y)){
        //game.setScreen() <-- place your game here
      }
      if(EndlessRoad.contains(touch.x,touch.y)){
        //game.setScreen() <-- place your game here
      }
    }
  }
  @Override
  public void dispose() {
    game.getBatch().dispose();
  }
}

package com.github.bot.curiosone.app.games.wordtiles.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.bot.curiosone.app.games.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.Spawner.TileSpawner;
import com.github.bot.curiosone.app.workflow.Chat;

public class DifficultyMenuScreen extends ScreenAdapter {

    private Chat game;
    private OrthographicCamera camera;
    private Vector3 touch;
    private TextButton easy,normal,hard,extreme;
    private Rectangle easyArea, normalArea,hardArea,extremeArea,backArea;
    private Texture background;
    private BitmapFont bitmapFont;
    private Sound clickSound;

    public DifficultyMenuScreen(Chat game) {
      this.game = game;
      touch = new Vector3();
      clickSound = Gdx.audio.newSound(Gdx.files.internal("WordTiles/Sound Effects/Click.wav"));
      background = new Texture("WordTiles/Background_Clean.jpg");
      /*Camera Settings*/
      camera = new OrthographicCamera();
      camera.setToOrtho(false,480,800);
      camera.position.set(480 / 2, 800 / 2, 0);

      /*General Style Settings*/
      TextureRegionDrawable up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/ButtonTextures/button1.png"))));
      TextureRegionDrawable down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/ButtonTextures/button2.png"))));
      TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(up,down,null, TileSpawner.font);

      /*Easy Button*/
      easy = new TextButton("Play",style);
      easyArea = new Rectangle(480/2-250/2,800/2,250,55);
      easy.setPosition(easyArea.x,easyArea.y);
      easy.setSize(easyArea.width,easyArea.height);

      /*Normal Button*/
      normal = new TextButton("Normal",style);
      normalArea = new Rectangle(480/2-250/2,800/2-65,250,55);
      normal.setPosition(normalArea.x,normalArea.y);
      normal.setSize(normalArea.width,normalArea.height);

      /*Hard Button*/
      hard = new TextButton("Hard",style);
      hardArea = new Rectangle(480/2-250/2,800/2-130,250,55);
      hard.setPosition(hardArea.x,hardArea.y);
      hard.setSize(hardArea.width,hardArea.height);
      /*Extreme Button*/
      extreme = new TextButton("Extreme",style);
      extremeArea = new Rectangle(480/2-250/2,800/2-195,250,55);
      extreme.setPosition(extremeArea.x,extremeArea.y);
      extreme.setSize(extremeArea.width,extremeArea.height);

      /*Back Box*/
      bitmapFont = new BitmapFont(Gdx.files.internal("WordTiles/Font/lexie.fnt"));
      backArea = new Rectangle(480/2-240/2,100,300,50);

    }



    private void draw() {
      camera.update();
      game.getBatch().setProjectionMatrix(camera.combined);
      game.getBatch().begin();
      game.getBatch().draw(background,0,0,480,800);
      easy.draw(game.getBatch(),1);
      normal.draw(game.getBatch(),1);
      hard.draw(game.getBatch(),1);
      extreme.draw(game.getBatch(),1);
      bitmapFont.draw(game.getBatch(),"Back",480/2-50,150);
      game.getBatch().end();
    }

    private void update() {
      if(Gdx.input.isTouched()){
        //Transforms the input coordinates to camera coordinates
        camera.unproject(touch.set(Gdx.input.getX(),Gdx.input.getY(),0));

        if(easyArea.contains(touch.x,touch.y)){
          Gdx.app.log("Touched","Easy");
          if(Settings.SFX) clickSound.play();
          Settings.MODE = Settings.Difficulty.EASY;
          game.setScreen(new PlayScreen(game));
          dispose();
        }

        if(normalArea.contains(touch.x,touch.y)){
          Gdx.app.log("Touched","Normal");
          if(Settings.SFX) clickSound.play();
          Settings.MODE = Settings.Difficulty.NORMAL;
          game.setScreen(new PlayScreen(game));
          dispose();
        }

        if(hardArea.contains(touch.x,touch.y)){
          Gdx.app.log("Touched","Hard");
          if(Settings.SFX) clickSound.play();
          Settings.MODE = Settings.Difficulty.HARD;
          game.setScreen(new PlayScreen(game));
          dispose();
        }

        if(extremeArea.contains(touch.x,touch.y)){
          Gdx.app.log("Touched","Extreme");
          if(Settings.SFX) clickSound.play();
          Settings.MODE = Settings.Difficulty.EXTREME;
          game.setScreen(new PlayScreen(game));
          dispose();
        }
        //Go back to the main menu
        if(backArea.contains(touch.x,touch.y)){
          Gdx.app.log("Touched","Back");
          if(Settings.SFX) clickSound.play();
          game.setScreen(new MainMenuScreen(game));
          dispose();
        }
      }
    }

    @Override
    public void render(float delta) {
      update();
      draw();
    }

    @Override
    public void dispose() {
        clickSound.dispose();
        background.dispose();
        super.dispose();
    }
}

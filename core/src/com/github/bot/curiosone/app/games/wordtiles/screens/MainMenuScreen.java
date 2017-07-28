package com.github.bot.curiosone.app.games.wordtiles.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Assets;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Manager;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.spawner.TileSpawner;
import com.github.bot.curiosone.app.workflow.Chat;
import com.github.bot.curiosone.app.workflow.GameCenter;

/**
 * @author Alessandro Roic
 * This class is the main menu
 */
public class MainMenuScreen extends ScreenAdapter
{
    private Chat game;
    private Texture background;
    private Music music;
    private Sound clickSound;
    private Stage stage;
    private Manager manager;

    public MainMenuScreen(final Chat game) {
        this.game=game;
        Settings settings = Settings.getIstance();
        stage = new Stage(new StretchViewport(480,800));
        Gdx.input.setInputProcessor(stage);
        manager = Manager.getIstance();
        manager.loadMainMenuScreen();
        //Background
        background = manager.getAssetManager().get(Assets.background.getPath());
        //General
        TextButton.TextButtonStyle style = Manager.getImageButtonStyle();
        //Play Button
        TextButton playButton = new TextButton("Play", style);
        playButton.setPosition(480/2-250/2,800/2);
        playButton.setSize(250,55);
        playButton.addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {return true;}

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new PlayScreen(game));
            dispose();
          }
        });
        stage.addActor(playButton);
        /*Option Button*/
        TextButton optionButton = new TextButton("Options", style);
        optionButton.setPosition(480/2-250/2,800/2-65);
        optionButton.setSize(250,55);
        optionButton.addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            game.setScreen(new OptionScreen(game));
            dispose();
          }
        });
        stage.addActor(optionButton);
        /*Exit Button*/
        TextButton exitButton = new TextButton("Exit", style);
        exitButton.setPosition(480/2-250/2,800/2-130);
        exitButton.setSize(250,55);
        exitButton.addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            manager.getAssetManager().clear();
            game.setScreen(new GameCenter(game));
            dispose();
          }
        });
        stage.addActor(exitButton);
        /*Music & Sound Settings*/
        this.music = manager.getAssetManager().get(Assets.musicMenu.getPath());
        if(settings.MUSIC) {
         music.setLooping(true);
          if(Gdx.app.getType()!= Application.ApplicationType.Desktop) music.play();
        }
        this.clickSound = manager.getAssetManager().get(Assets.click.getPath());
    }

    @Override
    public void render(float delta) {
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      game.getBatch().begin();
      game.getBatch().draw(background,0,0,480,800);
      game.getBatch().end();
      stage.act();
      stage.draw();
    }

    @Override
    public void resize(int width,int height) {
        super.resize(width,height);
    }

    @Override
    public void dispose() {
        music.dispose();
        clickSound.dispose();
        background.dispose();
        stage.dispose();
        super.dispose();
    }

}

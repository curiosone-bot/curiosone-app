package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Constants;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
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
    private OrthographicCamera camera;
    private int count;

    public MainMenuScreen(final Chat game) {
        this.game=game;
        Settings settings = Settings.getIstance();
        stage = new Stage(new StretchViewport(Constants.WIDTH,Constants.HEIGHT));
        Gdx.input.setInputProcessor(stage);
        manager = Manager.getIstance();
        manager.loadMainMenuScreen();
        //Background
        background = manager.getAssetManager().get(Assets.menubackground.getPath());
        //Music Settings
        music = manager.getAssetManager().get(Assets.menuMusic.getPath());
        music.play();
        //Sfx Settings
        clickSound = manager.getAssetManager().get(Assets.click.getPath());
        //Camera Settings
        camera = new OrthographicCamera();
        camera.setToOrtho(false,1080,1920);
        camera.position.set(1080 / 2, 1920 / 2, 0); //Need to fill the background image dimensions
        //Play Button
        TextureRegionDrawable buttonOn = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.buttonOn.getPath(),Texture.class)));
        TextureRegionDrawable buttonOff = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.buttonOff.getPath(),Texture.class)));
        ImageButton playButton = new ImageButton(buttonOn,buttonOff);
        playButton.setSize(400,80);
        playButton.setPosition(Constants.WIDTH/2-playButton.getWidth()/2,Constants.HEIGHT/2);
        playButton.addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            clickSound.play(2f);
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            manager.getAssetManager().clear();
            game.setScreen(new PlayScreen(game));
            dispose();
          }
        });
        stage.addActor(playButton);
        /*Option Button*/
        ImageButton optionButton = new ImageButton(buttonOn,buttonOff);
        optionButton.setSize(400,80);
        optionButton.setPosition(Constants.WIDTH/2-optionButton.getWidth()/2,Constants.HEIGHT/2-80);
        optionButton.addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            clickSound.play();
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            manager.getAssetManager().clear();
            game.setScreen(new OptionScreen(game));
            dispose();
          }
        });
        stage.addActor(optionButton);
        /*Exit Button*/
        ImageButton exitButton = new ImageButton(buttonOn,buttonOff);
        exitButton.setSize(400,80);
        exitButton.setPosition(Constants.WIDTH/2-exitButton.getWidth()/2,Constants.HEIGHT/2-160);
        exitButton.addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            clickSound.play();
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
    }

    @Override
    public void render(float delta) {
        count++;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,1080,1920);
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width,int height) {
        super.resize(width,height);
        stage.getViewport().update(width,height);
    }

    @Override
    public void dispose() {
        music.dispose();
        background.dispose();
        stage.dispose();
        super.dispose();
    }

}

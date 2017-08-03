package com.github.bot.curiosone.app.games.airborneassault.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.gameobjects.AlliedPlane;
import com.github.bot.curiosone.app.workflow.Chat;

/**
 * @author Alessandro Roic
 * This class let the player set the music and sfx.
 */
public class OptionScreen extends ScreenAdapter{

    private Chat game;
    private Texture background;
    private CheckBox musicCheckBox,sfxCheckBox;
    private CheckBox.CheckBoxStyle style,style2;
    private TextureRegionDrawable checked,unchecked;
    private TextButton back;
    private BitmapFont bitmapFont;
    private Stage stage;
    private Manager manager;
    private OrthographicCamera camera;

    public OptionScreen(final Chat game) {
        this.game = game;
        stage = new Stage(new StretchViewport(480,800));
        Gdx.input.setInputProcessor(stage);
        manager = Manager.getIstance();
        manager.loadOptionScreen();
         /*Background*/
        background =  manager.getAssetManager().get(Assets.cleanBackground.getPath());
        /*CheckBoxs Style Settings*/
        unchecked = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.checkbox.getPath(),Texture.class)));
        checked = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.checkbox2.getPath(),Texture.class)));
        style = new CheckBox.CheckBoxStyle(unchecked,checked,manager.getAssetManager().get(Assets.font.getPath(),BitmapFont.class) , Color.WHITE);
        style2 = new CheckBox.CheckBoxStyle(style);

        /*Music CheckBox*/
        musicCheckBox = new CheckBox("Music",style);
        musicCheckBox.setPosition(480/2-60/2,800/2);
        musicCheckBox.setSize(60,60);
        musicCheckBox.getLabelCell().padLeft(-200);
        musicCheckBox.getImageCell().padLeft(100);
        musicCheckBox.addListener(new ChangeListener() {
          public void changed (ChangeEvent event, Actor actor) {
            Gdx.graphics.setContinuousRendering(musicCheckBox.isChecked());
          }
        });
        stage.addActor(musicCheckBox);
        /*SFX CheckBox*/
        sfxCheckBox = new CheckBox("Effects",style2);
        sfxCheckBox.setPosition(480/2-60/2,800/2-80);
        sfxCheckBox.setSize(60,60);
        sfxCheckBox.getLabelCell().padLeft(-200);
        sfxCheckBox.getImageCell().padLeft(100);
        sfxCheckBox.addListener(new ChangeListener() {
          public void changed (ChangeEvent event, Actor actor) {
            Gdx.graphics.setContinuousRendering(musicCheckBox.isChecked());
          }
        });
        stage.addActor(sfxCheckBox);
        /*Back Box*/
        bitmapFont = manager.getAssetManager().get(Assets.font.getPath());
        back = new TextButton("Back",new TextButton.TextButtonStyle(null,null,null,bitmapFont));
        back.setPosition(480/2-50,150);
        back.addListener(new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            manager.getAssetManager().clear();
            game.setScreen(new MainMenuScreen(game));
            dispose();
          }
        });
        stage.addActor(back);
      //Camera Settings
      camera = new OrthographicCamera();
      camera.setToOrtho(false,480,800);
      camera.position.set(480 / 2, 800 / 2, 0);
    }

    @Override
    public void render(float delta) {
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      camera.update();
      game.getBatch().setProjectionMatrix(camera.combined);
      game.getBatch().begin();
      game.getBatch().draw(background,0,0,480,800);
      game.getBatch().end();
      stage.act();
      stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
    }
}

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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.settings.Constants;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.workflow.Chat;

/**
 * @author Alessandro Roic
 * This class let the player set the music and sfx.
 */
public class OptionScreen extends ScreenAdapter{

    private Chat game;
    private Texture background;
    private CheckBox musicCheckBox,sfxCheckBox;
    private TextureRegionDrawable checked,unchecked;
    private TextButton back;
    private BitmapFont bitmapFont;
    private Stage stage;
    private Manager manager;
    private OrthographicCamera camera;
    private Settings settings;

    public OptionScreen(final Chat game) {
        this.game = game;
        settings = Settings.getIstance();
        stage = new Stage(new StretchViewport(Constants.WIDTH, Constants.HEIGHT),game.getBatch());
        Gdx.input.setInputProcessor(stage);
        manager = Manager.getIstance();

        /*Background*/
        background =  manager.getAssetManager().get(Assets.optionBackground.getPath());

        /*CheckBox Style Settings*/
        unchecked = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.checkbox.getPath(),Texture.class)));
        checked = new TextureRegionDrawable(new TextureRegion(manager.getAssetManager().get(Assets.checkbox2.getPath(),Texture.class)));
        CheckBox.CheckBoxStyle style = new CheckBox.CheckBoxStyle(checked,unchecked,manager.getAssetManager().get(Assets.font.getPath(),BitmapFont.class) , Color.BLACK);
        CheckBox.CheckBoxStyle style2 = new CheckBox.CheckBoxStyle(style);

        /*Music CheckBox*/
        musicCheckBox = new CheckBox("Music",style);
        musicCheckBox.setPosition(Constants.WIDTH/2-60/2, Constants.HEIGHT/2);
        musicCheckBox.setSize(60,60);
        musicCheckBox.getLabelCell().padLeft(-200);
        musicCheckBox.getImageCell().padLeft(100);
        musicCheckBox.addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          if(settings.isMUSIC()){
            settings.setMUSIC(false);
          }
          else {
            settings.setMUSIC(true);
          }
        }
        });
        stage.addActor(musicCheckBox);

        /*SFX CheckBox*/
        sfxCheckBox = new CheckBox("Effects",style2);
        sfxCheckBox.setPosition(Constants.WIDTH/2-60/2, Constants.HEIGHT/2-80);
        sfxCheckBox.setSize(60,60);
        sfxCheckBox.getLabelCell().padLeft(-200);
        sfxCheckBox.getImageCell().padLeft(100);
        sfxCheckBox.addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          if(settings.isSFX()){
            settings.setSFX(false);
          }
          else {
            settings.setSFX(true);
          }
        }
        });
        stage.addActor(sfxCheckBox);

        /*Back Box*/
        bitmapFont = manager.getAssetManager().get(Assets.font.getPath());
        back = new TextButton("Back",new TextButton.TextButtonStyle(null,null,null,bitmapFont));
        back.getLabel().setColor(Color.BLACK);
        back.setPosition(Constants.WIDTH/2-50,150);
        back.addListener(new ChangeListener() {
          @Override
          public void changed(ChangeEvent event, Actor actor) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
          }
        });
        stage.addActor(back);

        /*Camera Settings*/
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        camera.position.set(Constants.WIDTH / 2, Constants.HEIGHT / 2, 0);
    }

    @Override
    public void render(float delta) {
        update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0, Constants.WIDTH, Constants.HEIGHT);
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    /**
     * Updates the checkboxes
     */
    private void update(){
        if(settings.isMUSIC()){
          musicCheckBox.setChecked(true);
        }
        else {
          musicCheckBox.setChecked(false);
        }
        if(settings.isSFX()){
          sfxCheckBox.setChecked(true);
        }
        else {
          sfxCheckBox.setChecked(false);
        }
      }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }
}
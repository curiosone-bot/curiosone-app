package com.github.bot.curiosone.app.games.wordtiles.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.bot.curiosone.app.games.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.Spawner.TileSpawner;
import com.github.bot.curiosone.app.workflow.Chat;

public class OptionScreen extends ScreenAdapter{

    private Chat game;
    private OrthographicCamera camera;
    private Texture background;
    private Music music;
    private Sound clickSound;
    private Vector3 touch;
    private Settings settings;

    private Rectangle musicCheckBoxArea,sfxCheckBoxArea,backArea;
    private CheckBox musicCheckBox,sfxCheckBox;
    private CheckBox.CheckBoxStyle style,style2;
    private TextureRegionDrawable checked,unchecked;
    private BitmapFont bitmapFont;
    private long lastTouched;

    public OptionScreen(Chat game) {
        this.game = game;
        this.settings = new Settings();
        touch = new Vector3();

        /*Camera Settings*/
        camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);


         /*Background*/
        background = new Texture("WordTiles/Background_Clean.jpg");

        /*CheckBoxs Style Settings*/
        unchecked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/checkbox-textures/blue_boxCheckmark.png"))));
        checked = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/checkbox-textures/blue_boxCheckmark2.png"))));
        style = new CheckBox.CheckBoxStyle(checked,unchecked, TileSpawner.font, Color.WHITE);
        style2 = new CheckBox.CheckBoxStyle(checked,unchecked,TileSpawner.font,Color.WHITE);

        /*Music CheckBox*/
        musicCheckBox = new CheckBox("Music",style);
        musicCheckBoxArea = new Rectangle(480/2-60/2,800/2,150,60);
        musicCheckBox.setPosition(480/2-60/2,800/2);
        musicCheckBox.setSize(60,60);
        musicCheckBox.getLabelCell().padLeft(-200);
        musicCheckBox.getImageCell().padLeft(100);


        /*SFX CheckBox*/
        sfxCheckBox = new CheckBox("Effects",style2);
        sfxCheckBoxArea = new Rectangle(480/2-60/2,800/2-80,150,60);
        sfxCheckBox.setPosition(480/2-60/2,800/2-80);
        sfxCheckBox.setSize(60,60);
        sfxCheckBox.getLabelCell().padLeft(-200);
        sfxCheckBox.getImageCell().padLeft(100);

        /*Back Box*/
        bitmapFont = new BitmapFont(Gdx.files.internal("WordTiles/Font/lexie.fnt"));
        backArea = new Rectangle(480/2-240/2,100,300,50);
    }

    private void update() {
      if (Gdx.input.isTouched() & TimeUtils.nanoTime() - lastTouched > 100000000) {
        //Transforms the input coordinates to camera coordinates
        camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));
        //If the music checkbox is touched
        if (musicCheckBoxArea.contains(touch.x, touch.y)) {
          Gdx.app.log("Touched", "Music CheckBox");
          if (Settings.MUSIC) {
            Settings.setMUSIC(false);
            musicCheckBox.getStyle().checkboxOff = checked;
          } else {
            Settings.setMUSIC(true);
            musicCheckBox.getStyle().checkboxOff = unchecked;
          }
        }
        //If the sfx checkbox is touched
        if (sfxCheckBoxArea.contains(touch.x, touch.y)) {
          Gdx.app.log("Touched", "SFX Checkbox");
          if (Settings.SFX) {
            Settings.setSFX(false);
            sfxCheckBox.getStyle().checkboxOff = checked;
          } else {
            Settings.setSFX(true);
            sfxCheckBox.getStyle().checkboxOff = unchecked;
          }
        }
        lastTouched = TimeUtils.nanoTime();
      }
      //Go back to the main menu
      if(backArea.contains(touch.x,touch.y)){
        Gdx.app.log("Touched","Back");
        dispose();
        game.setScreen(new MainMenuScreen(game));
      }
    }

    private void draw() {
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,480,800);
        sfxCheckBox.draw(game.getBatch(),1);
        musicCheckBox.draw(game.getBatch(),1);
        bitmapFont.draw(game.getBatch(),"Back",480/2-50,150);
        game.getBatch().end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}

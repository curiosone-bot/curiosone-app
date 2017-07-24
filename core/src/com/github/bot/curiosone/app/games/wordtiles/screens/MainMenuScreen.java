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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private OrthographicCamera camera;
    private Texture background;
    private Vector3 touch;
    private Music music;
    private Sound clickSound;
    private Rectangle playButtonArea,optionButtonArea,exitButtonArea;
    private TextButton playButton,optionButton,exitButton;
    private Settings settings;

    public MainMenuScreen(Chat game) {
        this.game=game;
        touch = new Vector3();
        settings = Settings.getIstance();
        /*Camera Settings*/
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);
        /*Music & Sound Settings*/
        this.music = Gdx.audio.newMusic(Gdx.files.internal("WordTiles/Songs/Five Card Shuffle.mp3"));
        if(settings.MUSIC) {
            music.setLooping(true);
           if(Gdx.app.getType()!= Application.ApplicationType.Desktop) music.play();
        }
        this.clickSound = Gdx.audio.newSound(Gdx.files.internal("WordTiles/Sound Effects/Click.wav"));
        /*Background*/
        background = new Texture("WordTiles/Background.png");
        /*General Button Style*/
        TextureRegionDrawable up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/ButtonTextures/button1.png"))));
        TextureRegionDrawable down = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("WordTiles/ButtonTextures/button2.png"))));
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(up,down,null,TileSpawner.font);
        /*Play Button*/
        playButton = new TextButton("Play",style);
        playButtonArea = new Rectangle(480/2-250/2,800/2,250,55);
        playButton.setPosition(playButtonArea.x,playButtonArea.y);
        playButton.setSize(playButtonArea.width,playButtonArea.height);
        /*Option Button*/
        optionButton = new TextButton("Options",style);
        optionButtonArea = new Rectangle(480/2-250/2,800/2-65,250,55);
        optionButton.setPosition(optionButtonArea.x,optionButtonArea.y);
        optionButton.setSize(optionButtonArea.width,optionButtonArea.height);
        /*Exit Button*/
        exitButton = new TextButton("Exit",style);
        exitButtonArea = new Rectangle(480/2-250/2,800/2-130,250,55);
        exitButton.setPosition(exitButtonArea.x,exitButtonArea.y);
        exitButton.setSize(exitButtonArea.width,exitButtonArea.height);
    }

  /**
   * Updates the buttons and handle input
   */
  public void update() {
        if(Gdx.input.isTouched()){
            //Transforms the input coordinates to camera coordinates
            camera.unproject(touch.set(Gdx.input.getX(),Gdx.input.getY(),0));

            if(playButtonArea.contains(touch.x,touch.y)){
                playButton.getStyle().up = playButton.getStyle().down;
                if(settings.SFX)clickSound.play();
                Gdx.app.log("Touched","PlayButton");
                game.setScreen(new DifficultyMenuScreen(game));
                dispose();
            }

            if(optionButtonArea.contains(touch.x,touch.y)){
              optionButton.getStyle().up = optionButton.getStyle().down;
              if(settings.SFX)clickSound.play();
              Gdx.app.log("Touched","OptionButton");
              game.setScreen(new OptionScreen(game));
              dispose();
            }
            if(exitButtonArea.contains(touch.x,touch.y)){
              optionButton.getStyle().up = optionButton.getStyle().down;
              if(settings.SFX) clickSound.play();
              Gdx.app.log("Touched","ExitButton");
              game.setScreen(new GameCenter(game));
            }
            //Crediti
        }
    }

    /**
     * Draws the Background and the Menu Buttons
     */
    public void draw() {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,480,800);
        playButton.draw(game.getBatch(),1);
        optionButton.draw(game.getBatch(),1);
        exitButton.draw(game.getBatch(),1);
        game.getBatch().end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
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
        super.dispose();
    }

}

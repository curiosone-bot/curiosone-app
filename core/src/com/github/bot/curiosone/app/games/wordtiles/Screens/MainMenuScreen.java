package com.github.bot.curiosone.app.games.wordtiles.Screens;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.bot.curiosone.app.games.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.Spawner.TileSpawner;
import com.github.bot.curiosone.app.workflow.Chat;

public class MainMenuScreen extends ScreenAdapter
{
    private Chat game;
    private OrthographicCamera camera;
    private Texture background;
    private Vector3 touch;
    private Music music;
    private Sound clickSound;

    private Rectangle playButtonArea,optionButtonArea;
    private TextButton playButton,optionButton;

    public MainMenuScreen(Chat game) {
        this.game=game;

        /*Camera Settings*/
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);

        /*Music & Sound Settings*/
        this.music = Gdx.audio.newMusic(Gdx.files.internal("WordTiles/Songs/Five Card Shuffle.mp3"));
        if(Settings.MUSIC) {

            music.setLooping(true);
            music.play();
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

        touch = new Vector3();
    }

    /*MainMenu Logic*/
    public void update() {
        if(Gdx.input.isTouched()){
            //Transforms the input coordinates to camera coordinates
            camera.unproject(touch.set(Gdx.input.getX(),Gdx.input.getY(),0));

            if(playButtonArea.contains(touch.x,touch.y)){
                playButton.getStyle().up = playButton.getStyle().down;
                if(Settings.SFX)clickSound.play();
                Gdx.app.log("Touched","PlayButton");
                dispose();
                game.setScreen(new PlayScreen(game));
            }

            if(optionButtonArea.contains(touch.x,touch.y)){
              optionButton.getStyle().up = optionButton.getStyle().down;
              if(Settings.SFX)clickSound.play();
              Gdx.app.log("Touched","OptionButton");
              dispose();
              game.setScreen(new OptionScreen(game));
            }
            //Crediti
        }
    }

    /**
     * Draws the Background and the Menu Buttons
     */
    public void draw() {
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        game.getBatch().draw(background,0,0,480,800);
        playButton.draw(game.getBatch(),1);
        optionButton.draw(game.getBatch(),1);
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

package com.github.bot.curiosone.app.workflow.com.curiosone.WordTilesSrc.Screens;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.github.bot.curiosone.app.workflow.Chat;
import com.github.bot.curiosone.app.workflow.com.curiosone.WordTilesSrc.Settings.*;


public class MainMenuScreen extends ScreenAdapter
{
    private Chat game;
    private OrthographicCamera camera;
    private Texture background;
    private Vector3 touch;
    private Music music;
    private Sound clickSound;

    private Rectangle playButton;
    private Texture playTexture;

    public MainMenuScreen(Chat game) {
        this.game=game;

        /*Camera Settings*/
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);

        /*Music & Sound Settings*/
        if(Settings.MUSIC) {
            this.music = Gdx.audio.newMusic(Gdx.files.internal("WordTiles/Songs/Five Card Shuffle.mp3"));
            music.setLooping(true);
            if(Gdx.app.getType()!= Application.ApplicationType.Desktop)music.play();
        }
        if(Settings.SFX){ this.clickSound = Gdx.audio.newSound(Gdx.files.internal("WordTiles/Sound Effects/Click.wav"));}

        /*Camera Settings*/
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);
        /*Background*/
        background = new Texture("WordTiles/Background.png");
        /*Play Button*/
        playTexture = new Texture("WordTiles/play2.png");
        playButton = new Rectangle(480/2-250/2,800/2,250,55);
        touch = new Vector3();
    }

    /*MainMenu Logic*/
    public void update() {
        if(Gdx.input.isTouched()){
            //Transforms the input coordinates to camera coordinates
            camera.unproject(touch.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(playButton.contains(touch.x,touch.y)){
                if(Settings.SFX)clickSound.play(1.0f);
                Gdx.app.log("1","PLAY IS TOUCHED");
                dispose();
                game.setScreen(new PlayScreen(game));
                //return;
            }
            //Opzioni,Crediti
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
        game.getBatch().draw(playTexture,playButton.getX(),playButton.getY(),playButton.getWidth(),playButton.getHeight());
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
        playTexture.dispose();
        super.dispose();
    }

}
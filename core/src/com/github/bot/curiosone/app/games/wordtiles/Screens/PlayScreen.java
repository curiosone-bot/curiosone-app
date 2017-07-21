package com.github.bot.curiosone.app.games.wordtiles.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.bot.curiosone.app.games.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.Spawner.TileSpawner;
import com.github.bot.curiosone.app.games.wordtiles.Sprites.AbstractTile;
import com.github.bot.curiosone.app.workflow.Chat;
import java.util.Iterator;

/**
 * @author Alessandro Roic
 * The PlayScreen is the main game class
 */
public class PlayScreen extends ScreenAdapter {

    private Chat game;
    private OrthographicCamera camera;
    private Iterator<AbstractTile> tileIterator;
    private long lastSpawnedTime;
    private Array<AbstractTile> drawer;

    private boolean gameOver = false;
    private boolean done = false;
    private boolean win = false;

    private Vector3 touch;
    private Sound gameOverSound;

    private Texture gameOverTexture;
    private Rectangle backButton;
    private Texture winTexure;

    public PlayScreen(Chat game) {
        //Spawning the tiles
        TileSpawner spawner = new TileSpawner();
        this.tileIterator = spawner.iterator();
        this.lastSpawnedTime = 0;
        this.drawer = new Array<AbstractTile>();
        this.game = game;
        done = true;         //when it's done spawning the tiles,the game can start

        //Camera Settings
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);

        //Miscellaneous
        this.gameOverSound = Gdx.audio.newSound(Gdx.files.internal("WordTiles/Sound Effects/GameOver.wav"));
        this.gameOverTexture = new Texture("WordTiles/GameOver.png");
        this.backButton = new Rectangle(325,180,84,84);
        this.winTexure = new Texture("WordTiles/Win.png");
        this.touch = new Vector3();
    }

    public void update(float dt) {
        //Spawn the tiles every tot seconds
        if(TimeUtils.nanoTime()-lastSpawnedTime> Settings.SPAWN_RATE){
           if(tileIterator.hasNext()) {drawer.add(tileIterator.next());}
            lastSpawnedTime = TimeUtils.nanoTime();
        }

        touch = touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(touch);

        for(AbstractTile tile:drawer){
            if(!tile.isDisposable()){
                if(tile.isGameOver()){gameOver = true;}
                tile.update(touch,dt);
            }
            else{
                drawer.removeValue(tile,false);
                if(drawer.size==0){win= true;}
            }
        }
        if(gameOver){
            gameOverSound.play();
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();

        if(done) {
                for (AbstractTile tile : drawer) {
                    if (!tile.isDisposable()) {
                        tile.draw(game.getBatch());
                    }
                }

        }
        //To Game Over
        if(gameOver){
            game.getBatch().draw(gameOverTexture,camera.viewportWidth/2-350/2,camera.viewportHeight/2-450/2,350,450);
            if(Gdx.input.isTouched()) {
                touch = touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touch);
                if (backButton.contains(touch.x, touch.y)) {
                    dispose();
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }
        //To Win Screen
        if(done&&win){
            game.getBatch().draw(winTexure,0,0,480,800);
            if(Gdx.input.isTouched()){
                dispose();
                winTexure.dispose();
                game.setScreen(new MainMenuScreen(game));
            }
        }

        game.getBatch().end();
    }

    @Override
    public void render(float delta) {
       if (!gameOver&&done) {
           update(delta);
       }
       draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        gameOverSound.dispose();
        super.dispose();
    }
}

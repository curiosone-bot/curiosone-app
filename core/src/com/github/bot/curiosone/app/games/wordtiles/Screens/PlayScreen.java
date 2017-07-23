package com.github.bot.curiosone.app.games.wordtiles.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    private boolean gameOver = false,done = false,win = false;
    private Vector3 touch;
    private Sound gameOverSound;
    private Texture gameOverTexture,winTexure,background,background2;
    private Rectangle backButton;
    private float y1,y2;
    private BitmapFont category;
    private GlyphLayout layout;
    private int categoryCounter;
    private Array<String> categories;
    private long timer=0,timer2=0,timer3=0,timer4=0;

    public PlayScreen(Chat game) {
        //Spawning the tiles
        TileSpawner spawner = new TileSpawner();
        this.tileIterator = spawner.iterator();
        this.lastSpawnedTime = 0;
        this.drawer = new Array<AbstractTile>();
        this.game = game;

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

        //Background
        background = new Texture(Gdx.files.internal("WordTiles/playbackground.png"));
        background2 = new Texture(Gdx.files.internal("WordTiles/playbackground.png"));
        y1=0;
        y2=800;

        //Category
        category = new BitmapFont(Gdx.files.internal("WordTiles/Font/lexie.fnt"));
        layout = new GlyphLayout();
        categories = new Array<String>(new String[]{"Animals","Body Parts","Clothes","Science"});
        categoryCounter = 0;
    }

    public void update(float dt) {
        //Scrolls the background
        float speed = dt*(Settings.SPEED-50);
        y1-= speed;
        y2-= speed;
        if(y1+800<=0){
          y1=y2 + 800;
        }
        if(y2+800<=0){
          y2=y1+800;
        }

        //Spawn the tiles every X seconds
        if(TimeUtils.nanoTime()-lastSpawnedTime> Settings.SPAWN_RATE){
           if(tileIterator.hasNext()) {drawer.add(tileIterator.next());}
            lastSpawnedTime = TimeUtils.nanoTime();
        }

        touch = touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(touch);
        //updates the tiles
        for(AbstractTile tile:drawer){
            if(!tile.isDisposable()){
                if(tile.isGameOver()){gameOver = true;}
                tile.update(touch,dt);
            }
            else{
                drawer.removeValue(tile,false);
                categoryCounter++;
                if(drawer.size==0){win= true;}
            }
        }
        if(gameOver){
            gameOverSound.play();
        }
    }

    public void draw(float delta) {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        //draw background
        game.getBatch().draw(background,0,y1,480,800);
        game.getBatch().draw(background2,0,y2,480,800);
        //draws the tiles
        for (AbstractTile tile : drawer) {
          if (!tile.isDisposable()) {
            tile.draw(game.getBatch());
          }
        }

        //draw category
        switch (categoryCounter){
          case 0:
            timer += 1;
            if(timer <150){
              String text = categories.get(0);
              layout.setText(category,text);
              category.draw(game.getBatch(),text,240-layout.width/2,750);
            }
            break;
          case 48:
            timer2 += 1;
            if(timer2 <150){
              String text = categories.get(1);
              layout.setText(category,text);
              category.draw(game.getBatch(),text,240-layout.width/2,750);
            }
            break;
          case 98:
            timer3 += 1;
            if(timer3 <150){
              String text = categories.get(2);
              layout.setText(category,text);
              category.draw(game.getBatch(),text,240-layout.width/2,750);
            }
            break;
          case 148:
            timer4 += 1;
            if(timer4 <150){
              String text = categories.get(3);
              layout.setText(category,text);
              category.draw(game.getBatch(),text,240-layout.width/2,750);
            }
            break;
        }


        //To Game Over
        if(gameOver){
            game.getBatch().draw(gameOverTexture,480/2-350/2,800/2-450/2,350,450);
            if(Gdx.input.isTouched()) {
                touch = touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touch);
                if (backButton.contains(touch.x, touch.y)) {
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }
            }
        }
        //To Win Screen
        if(win){
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
       if (!gameOver) {
           update(delta);
       }
       draw(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        gameOverSound.dispose();
        background2.dispose();
        background.dispose();
        super.dispose();
    }
}

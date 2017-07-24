package com.github.bot.curiosone.app.games.wordtiles.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.spawner.TileSpawner;
import com.github.bot.curiosone.app.games.wordtiles.tiles.AbstractTile;
import com.github.bot.curiosone.app.workflow.Chat;
import java.util.Iterator;

/**
 * @author Alessandro Roic
 * This class contains the gameplay
 */
public class PlayScreen extends ScreenAdapter {

    private Chat game;
    private OrthographicCamera camera;
    private Iterator<AbstractTile> tileIterator;
    private long lastSpawnedTime;
    private Array<AbstractTile> drawer;
    private boolean gameOver = false,win = false;
    private Vector3 touch;
    private Texture background,background2;
    private float y1,y2;
    private BitmapFont category;
    private GlyphLayout layout;
    private int categoryCounter;
    private Array<String> categories;
    private long timer=0,timer2=0,timer3=0,timer4=0;
    private Settings settings;
    private Music music;

    public PlayScreen(Chat game) {
        //Spawning the tiles
        settings = Settings.getIstance();
        TileSpawner spawner = new TileSpawner();
        tileIterator = spawner.iterator();
        lastSpawnedTime = 0;
        drawer = new Array<AbstractTile>();
        //Camera Settings
        camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);
        //Miscellaneous
        touch = new Vector3();
        this.game = game;
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
        //Music
        if (settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop){
          music = Gdx.audio.newMusic(Gdx.files.internal("WordTiles/Songs/Funky Chunk.mp3"));
          music.setLooping(true);
          music.play();
        }
    }

    public void update(float dt) {
        //Scrolls the background
        float speed = dt*(settings.SPEED-50);
        y1-= speed;
        y2-= speed;
        if(y1+800<=0){
          y1=y2 + 800;
        }
        if(y2+800<=0){
          y2=y1+800;
        }

        //Spawn the tiles every X seconds
        if(TimeUtils.nanoTime()-lastSpawnedTime> settings.SPAWN_RATE){
           if(tileIterator.hasNext()) {drawer.add(tileIterator.next());}
            lastSpawnedTime = TimeUtils.nanoTime();
        }


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
        touch = touch.set(Gdx.input.getX(),Gdx.input.getY(),0);
        camera.unproject(touch);
        //to GameOverScreen
        if(gameOver){
            if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
            game.setScreen(new GameOverScreen(game));
            dispose();
        }
        //to WinScreen
        if(win){
          if(settings.MUSIC)music.dispose();
          game.setScreen(new WinScreen(game));
          dispose();
        }
    }

    public void draw(float delta) {
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().setProjectionMatrix(camera.combined);
        game.getBatch().begin();
        //draws background
        game.getBatch().draw(background,0,y1,480,800);
        game.getBatch().draw(background2,0,y2,480,800);
        //draws the tiles
        for (AbstractTile tile : drawer) {
          if (!tile.isDisposable()) {
            tile.draw(game.getBatch());
          }
        }
        //draws category
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
        background2.dispose();
        background.dispose();
        if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
        super.dispose();
    }
}

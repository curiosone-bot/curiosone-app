package com.github.bot.curiosone.app.games.wordtiles.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Assets;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Manager;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.spawner.TileSpawner;
import com.github.bot.curiosone.app.games.wordtiles.gameobjects.AbstractTile;
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
    private Array<AbstractTile> updater;
    private boolean gameOver = false,win = false;
    private Texture background,background2;
    private float y1,y2;
    private Array<String> categories;
    private Settings settings;
    private Music music;
    private Stage stage;
    private Manager manager;

    public PlayScreen(Chat game) {
        this.game = game;
        manager = Manager.getIstance();
        manager.loadPlayScreen();
        stage = new Stage(new StretchViewport(480,800),game.getBatch());
        Gdx.input.setInputProcessor(stage);
        //Spawning the tiles
        settings = Settings.getIstance();
        TileSpawner spawner = new TileSpawner();
        tileIterator = spawner.iterator();
        lastSpawnedTime = 0;
        updater = new Array<AbstractTile>();
        //Camera Settings
        camera = new OrthographicCamera();
        camera.setToOrtho(false,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);

        //Background
        background = manager.getAssetManager().get(Assets.playBackground.getPath());
        background2 = manager.getAssetManager().get(Assets.playBackground.getPath());
        y1=0;
        y2=800;

        //Music
        if (settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop){
          music = manager.getAssetManager().get(Assets.playMusic.getPath());
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
           if(tileIterator.hasNext()) {
             AbstractTile tile = tileIterator.next();
             updater.add(tile);
             stage.addActor(tile.getActor());
           }
            lastSpawnedTime = TimeUtils.nanoTime();
        }
        //updates the tiles
        for(AbstractTile tile: updater){
            if(!tile.isDisposable()){
                if(tile.isGameOver()){gameOver = true;}
                tile.update(dt);
            }
            else {
              for(Actor actor:stage.getActors()){
                if(actor.remove()) stage.getActors().removeValue(actor,true);
              }
            }
        }
        //to GameOverScreen
        if(gameOver){
            if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
            manager.getAssetManager().clear();
            game.setScreen(new GameOverScreen(game));
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
        game.getBatch().end();
        stage.draw();
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
        stage.getViewport().update(width,height);
    }

    @Override
    public void dispose() {
        background2.dispose();
        background.dispose();
        if(settings.MUSIC&&Gdx.app.getType()!= Application.ApplicationType.Desktop)music.dispose();
        super.dispose();
    }
}

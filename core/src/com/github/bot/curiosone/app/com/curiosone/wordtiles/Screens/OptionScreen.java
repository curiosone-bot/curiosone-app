package com.github.bot.curiosone.app.com.curiosone.wordtiles.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.curiosone.wordtiles.Settings.Settings;
import com.curiosone.wordtiles.WordTiles;

public class OptionScreen extends ScreenAdapter{

    final WordTiles game;
    private OrthographicCamera camera;

    public OptionScreen(WordTiles game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        camera.setToOrtho(true,480,800);
        camera.position.set(480 / 2, 800 / 2, 0);
    }

    private void update() {
        Settings.SPEED = 200;
    }

    private void draw() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

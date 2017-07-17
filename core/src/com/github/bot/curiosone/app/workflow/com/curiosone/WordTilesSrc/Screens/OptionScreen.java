package com.github.bot.curiosone.app.workflow.com.curiosone.WordTilesSrc.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.bot.curiosone.app.GameCenter;
import com.github.bot.curiosone.app.com.curiosone.wordtiles.Settings.Settings;

public class OptionScreen extends ScreenAdapter{

    private GameCenter game;
    private OrthographicCamera camera;

    public OptionScreen(GameCenter game) {
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

package com.arkanoid;

import com.arkanoid.screens.MainMenuScreen;
import com.arkanoid.utils.Resources;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * Game main class
 * @author Simone Sanfratello
 *
 */
public class Arkanoid extends Game {

    @Override
    public void create() {
        Resources.getInstance().loadMenuAssets();
        Resources.getInstance().loadGameAssets();
        setScreen(MainMenuScreen.getInstance(this));
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render() {
        screen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        screen.resize(width, height);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        screen.dispose();
        Resources.getInstance().dispose();
    }
}
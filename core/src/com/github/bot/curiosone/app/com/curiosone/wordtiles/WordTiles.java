package com.github.bot.curiosone.app.com.curiosone.wordtiles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.com.curiosone.wordtiles.Screens.MainMenuScreen;

public class WordTiles extends Game {

    public SpriteBatch batch;

	@Override
	public void create () {
        batch = new SpriteBatch();
        setScreen(new MainMenuScreen(this));
	}

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width,int height)
    {
        super.resize(width,height);
    }
}

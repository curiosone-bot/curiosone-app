package com.github.bot.curiosone.app.games.wordtiles.gameobjects;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * @author Alessandro Roic
 * This class represent the tile
 */
abstract public class AbstractTile extends Actor {

    abstract public void update(float dt);

    abstract public boolean isDisposable();

    abstract public boolean isGameOver();

    @Override
    abstract public void draw(Batch batch, float parentAlpha);

    abstract public ImageButton getActor();
}

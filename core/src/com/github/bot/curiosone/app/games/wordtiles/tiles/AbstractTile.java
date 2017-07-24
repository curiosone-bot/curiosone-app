package com.github.bot.curiosone.app.games.wordtiles.tiles;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * @author Alessandro Roic
 * This class represent the tile
 */
abstract public class AbstractTile {

    abstract public void update(Vector3 touch,float dt);

    abstract public void draw(SpriteBatch spriteBatch);

    abstract public TextButton getTile();

    abstract public boolean isDisposable();

    abstract public boolean isGameOver();
}

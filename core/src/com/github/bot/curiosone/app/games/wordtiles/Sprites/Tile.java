package com.github.bot.curiosone.app.games.wordtiles.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.games.wordtiles.Settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.Spawner.TileSpawner;


public class Tile extends AbstractTile
{
    private TextButton tile;
    private Rectangle area;
    private boolean disposable = false;
    private boolean touched = false;
    private boolean gameOver = false;
    private Sound hit;

  public Tile(int x,String text)
    {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(TileSpawner.style);
        tile = new TextButton(text, style);
        tile.setPosition(x, 1000);
        tile.setSize(118,200);
        area = new Rectangle(x,800,118,200);
        hit = Gdx.audio.newSound(Gdx.files.internal("WordTiles/Sound Effects/Hit.wav"));
    }

    /*Tile Logic*/

    public void update(Vector3 touch,float dt) {

        if (Gdx.input.isTouched()) {
            if (area.contains(touch.x, touch.y)) {
                tile.getStyle().up = tile.getStyle().down;
                tile.setText("");
                hit.play(0.5f);
                Gdx.app.log("Tile-" + this.hashCode() + "", "Toccato");
                touched= true;
                hit.dispose();
            }
        }
        if (tile.getY() > -200) {
            tile.setPosition(tile.getX(), tile.getY() - Settings.SPEED * dt);
            area.setPosition(tile.getX(), tile.getY());
        }
        if (tile.getY() < -200) {
            //If the Tile has been touched, then dispose it
            if(touched){
                disposable = true;
            }
            //If the Tile hasn't been touched and it's out of bounds, then stop the game
            else {
                gameOver = true;
                Gdx.app.log("GameOverBy","Tile Out Of Bounds");
            }
        }

    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        tile.draw(spriteBatch,1);
    }

    @Override
    public TextButton getTile() {
        return tile;
    }

    @Override
    public boolean isDisposable() {
        return disposable;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    public boolean equals(Tile object){
        return this.hashCode() == object.hashCode();
    }
}


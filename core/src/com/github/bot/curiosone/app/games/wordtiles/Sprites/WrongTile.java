package com.github.bot.curiosone.app.games.wordtiles.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.bot.curiosone.app.games.wordtiles.Spawner.TileSpawner;

public class WrongTile extends AbstractTile {

    private TextButton tile;
    private Rectangle area;
    private boolean disposable = false;
    private boolean notTouched = true;
    private boolean gameOver = false;

    public WrongTile(int x, String text)  {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(TileSpawner.style2);
        tile = new TextButton(text,style);
        tile.setPosition(x, 1000);
        tile.setSize(118,200);
        area = new Rectangle(x,800,118,200);
    }


    public void update(Vector3 touch,float dt) {
        if (Gdx.input.isTouched()) {
            if (area.contains(touch.x, touch.y)) {
                tile.getStyle().up = tile.getStyle().down;
                tile.setText("");
                Gdx.app.log("WrongTile-" + this.hashCode() + "", "Toccato");
                notTouched = false;
            }
        }
        if (tile.getY() > -200) { //sostituisci il -200 con un valore decente
            this.tile.setPosition(tile.getX(), tile.getY() - com.github.bot.curiosone.app.games.wordtiles.Settings.Settings.SPEED * dt);
            this.area.setPosition(tile.getX(), tile.getY());
        }
        if (tile.getY() < -200) {
            //If the WrongTile hasn't been touched, then dispose it
            if(notTouched){
                disposable = true;
            }
        }
        //If the WrongTile has been touched then stop the game
        if(!notTouched){
            gameOver = true;
            Gdx.app.log("GameOverBy","WrongTile Touched");
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

    public boolean equals(WrongTile object){
        return this.hashCode() == object.hashCode();
    }

}

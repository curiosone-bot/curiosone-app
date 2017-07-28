package com.github.bot.curiosone.app.games.wordtiles.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;

/**
 * @author Alessandro Roic
 * This class represent the tile the player don't have to touch
 */
public class BombTile extends AbstractTile {

    private ImageButton tile;
    private boolean disposable = false;
    private boolean notTouched = true;
    private boolean gameOver = false;
    private Settings settings;

    public BombTile(int x)  {
        settings = Settings.getIstance();
        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        tile = new ImageButton(style);
        tile.setPosition(x, 1000);
        tile.setSize(118,200);
    }


    public void update(float dt) {

        if (tile.getY() > -200) { //sostituisci il -200 con un valore decente
            this.tile.setPosition(tile.getX(), tile.getY() - settings.SPEED * dt);
        }

        if (tile.getY() < -200) {
            //If the BombTile hasn't been touched, then dispose it
            if(notTouched){
                disposable = true;
            }
        }
    }


    @Override
    public boolean isDisposable() {
        return disposable;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
      update(Gdx.graphics.getDeltaTime());
      tile.draw(batch,parentAlpha);
    }

    @Override
    public ImageButton getActor() {
    return tile;
  }

    public boolean equals(BombTile object){
        return this.hashCode() == object.hashCode();
    }

}

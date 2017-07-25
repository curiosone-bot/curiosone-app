package com.github.bot.curiosone.app.games.wordtiles.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.games.wordtiles.spawner.TileSpawner;

/**
 * @author Alessandro Roic
 * This class represent the tile the player has
 * to touch
 */
public class Tile extends AbstractTile
{
    private TextButton tile;
    private Rectangle area;
    private boolean disposable = false;
    private boolean touched = false;
    private boolean gameOver = false;
    private Sound hit;
    private Settings settings;

  public Tile(int x,String text) {
      settings = Settings.getIstance();
      TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(TileSpawner.style);
      tile = new TextButton(text, style);
      tile.setPosition(x, 1000);
      tile.setSize(118,200);
      area = new Rectangle(x,800,118,200);
      hit = Gdx.audio.newSound(Gdx.files.internal("WordTiles/Sound Effects/Hit.wav"));
    }

  /**
   * @param touch
   * @param dt
   * Updates the single tile logic, has to be called into the main screen
   * update method.
   */
  public void update(Vector3 touch,float dt) {
    //If the Tile has been touched, then change it to touched
    if (Gdx.input.isTouched()) {
      if (area.contains(touch.x, touch.y)) {
        tile.getStyle().up = tile.getStyle().down;
        tile.setText("");
        if(settings.SFX)hit.play(0.8f);
        Gdx.app.log("Tile-" + this.hashCode() + "", "Toccato");
        touched= true;
      }
    }
    //While the tile is still in the screen, move it
    if (tile.getY() > -200) {
      tile.setPosition(tile.getX(), tile.getY() - settings.SPEED * dt);
      area.setPosition(tile.getX(), tile.getY());
    }
    if (tile.getY() < -200) {
      //If the Tile has been touched, then dispose it
      if(touched){
        disposable = true;
        if(settings.SFX)hit.dispose();
      }
      //If the Tile hasn't been touched and it's out of bounds, then stop the game
      else {
        gameOver = true;
        Gdx.app.log("GameOverBy","Tile Out Of Bounds");
      }
    }

  }

  /**
   * Draws the tile into the batch
   * @param spriteBatch
   */
  @Override
    public void draw(SpriteBatch spriteBatch) {
        tile.draw(spriteBatch,1);
    }

    @Override
    public TextButton getTile() {
        return tile;
    }

  /**
   * When the tile is out of bounds and it has been touched, the tile is disposable.
   * @return true if the tile is disposable
   */
  @Override
    public boolean isDisposable() {
        return disposable;
    }

  /**
   * The gameOver occurs when the tile is out of bounds,but it hasn't been touched.
   * @return true if gameOver has been detected
   */
  @Override
    public boolean isGameOver() {
        return gameOver;
    }

  /**
   * Used in the playscreen to remove a tile from the drawer, once is disposable.
   * @param object
   * @return
   */
    public boolean equals(Tile object){
        return this.hashCode() == object.hashCode();
    }
}


package com.github.bot.curiosone.app.games.wordtiles.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Assets;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Manager;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;

/**
 * @author Alessandro Roic
 * This class represent the tile the player has
 * to touch
 */
public class Tile extends AbstractTile
{
  private ImageButton tile;
  private boolean disposable = false;
  private boolean gameOver = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;
  public Tile(int x) {
      settings = Settings.getIstance();
      manager = Manager.getIstance();
      tile = new ImageButton(manager.getTile(),null,manager.getTileTouched());
      tile.setPosition(x, 800);
      tile.setSize(118,200);
      tile.addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          tile.setTouchable(Touchable.disabled);
        }
      });
      hit = manager.getAssetManager().get(Assets.hit.getPath());
    }

  /**
   * @param dt
   * Updates the single tile logic, has to be called into the main screen
   * update method.
   */
  public void update(float dt) {
    //While the tile is still in the screen, move it
    if (tile.getY() > -200) {
      tile.setPosition(tile.getX(), tile.getY() - settings.SPEED * dt);
    }
    if (tile.getY() < -200) {
      //If the Tile has been touched, then dispose it
      if(tile.isChecked()){
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

  @Override
  public void draw(Batch batch, float parentAlpha) {
    tile.draw(batch,parentAlpha);
  }

  @Override
  public boolean remove() {
    if(disposable) return true;
    return false;
  }

  /**
   * Used in the playscreen to remove a tile from the drawer, once is disposable.
   * @param object
   * @return
   */
    public boolean equals(Tile object){
        return this.hashCode() == object.hashCode();
    }
    public ImageButton getActor(){return tile;}
}


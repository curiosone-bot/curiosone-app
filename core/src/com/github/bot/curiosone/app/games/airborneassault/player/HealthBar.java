package com.github.bot.curiosone.app.games.airborneassault.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;

/**
 * This class creates the healthbar
 */
public class HealthBar {
  private Texture baseBar,healthBar;
  private float progessBarWidth;
  private final int barWidth = 154;

  public HealthBar(){
     Manager manager = Manager.getIstance();
     baseBar = manager.getAssetManager().get(Assets.baseBar.getPath(),Texture.class);
     healthBar = manager.getAssetManager().get(Assets.healthBar.getPath(),Texture.class);
     progessBarWidth = Player.getPlayerHealth()/Player.getMaxHealth()*barWidth;
  }

  /**
   * Updates the bar width according to the player's health
   */
  public void update(){
      if(Player.isChanged()){Gdx.app.log("Player Health",Player.getPlayerHealth()+"");}
      progessBarWidth = ((float) Player.getPlayerHealth()/(float)Player.getMaxHealth())*barWidth;
  }

  /**
   * Draws in the
   * @param batch the textures
   */
  public void draw(SpriteBatch batch){
      batch.draw(baseBar,10,10,170,55);
      batch.draw(healthBar,18,23,progessBarWidth,29);
  }
  public void dispose(){
      baseBar.dispose();
      healthBar.dispose();
  }
}

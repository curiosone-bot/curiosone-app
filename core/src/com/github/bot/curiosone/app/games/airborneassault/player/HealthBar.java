package com.github.bot.curiosone.app.games.airborneassault.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;

public class HealthBar {
  private Texture baseBar,healthBar;
  private Manager manager;
  private Settings settings;
  private float progessBarWidth;
  private final int barWidth = 134;
  private static HealthBar instance;

  private HealthBar(){
     manager = Manager.getIstance();
     settings = Settings.getIstance();
     baseBar = manager.getAssetManager().get(Assets.baseBar.getPath(),Texture.class);
     healthBar = manager.getAssetManager().get(Assets.healthBar.getPath(),Texture.class);
     progessBarWidth = Player.getPlayerHealth()/Player.getMaxHealth()*barWidth;
  }
  public static HealthBar getInstance(){
    if(instance==null){instance = new HealthBar();}
    return instance;
  }
  public void update(){
      if(Player.isChanged()){Gdx.app.log("Player Health",Player.getPlayerHealth()+"");}
      progessBarWidth = ((float) Player.getPlayerHealth()/(float)Player.getMaxHealth())*barWidth;
  }

  public void draw(SpriteBatch batch){
    //make it fit
      batch.draw(baseBar,10,10,150,55);
      batch.draw(healthBar,18,23,progessBarWidth,29);
  }
  public void dispose(){

  }
}

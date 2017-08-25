package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;

/**
 * This class creates the background for the PlayScreen
 */
public class ScrollingBackground {
  private Texture background,background2,background3;
  private float y1,y2;

  public ScrollingBackground(){
    Manager manager = Manager.getIstance();
    background2 = manager.getAssetManager().get(Assets.playBackground2.getPath());
    background3 = manager.getAssetManager().get(Assets.playBackground3.getPath());
    background = manager.getAssetManager().get(Assets.playBackground.getPath());
    y1=0;
    y2=800;
  }
  public void update(float deltaTime){
    float speed = Gdx.graphics.getDeltaTime()*(Speed.BACKGROUND.getSpeed());
    y1-= speed;
    y2-= speed;
    if(y1+800<=0){
      y1=y2 + 800;
    }
    if(y2+800<=0){
      y2=y1+800;
    }
  }

  public void draw(SpriteBatch batch){
    batch.draw(background,0,0,480,800);
    batch.draw(background2,0,y1,480,800);
    batch.draw(background3,0,y2,480,800);
  }

  public void dispose(){
    background.dispose();
    background2.dispose();
    background3.dispose();
  }
}

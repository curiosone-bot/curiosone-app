package com.github.bot.curiosone.app.games.wordtiles.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Assets;
import com.github.bot.curiosone.app.games.wordtiles.assets_manager.Manager;
import com.github.bot.curiosone.app.games.wordtiles.settings.Settings;
import com.github.bot.curiosone.app.workflow.Chat;

public class ScrollingBackground {

  private Texture background,background2;
  private int y1,y2;
  private Manager manager;
  private Settings settings;

  public ScrollingBackground() {
    manager = Manager.getIstance();
    settings = Settings.getIstance();
    background = manager.getAssetManager().get(Assets.playBackground.getPath());
    background2 = manager.getAssetManager().get(Assets.playBackground.getPath());
    y1=0;
    y2=800;
  }

  public void update(float speed){
    y1-= speed;
    y2-= speed;
    if(y1+800<=0){
      y1=y2 + 800;
    }
    if(y2+800<=0){
      y2=y1+800;
    }
  }

  public void draw(Chat game){
    game.getBatch().draw(background,0,y1, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    game.getBatch().draw(background2,0,y2,480,800);
  }

  public void dispose(){
    background.dispose();
    background2.dispose();
  }
}


package com.github.bot.curiosone.app.games.airborneassault.assets_manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Manager {
  private static Manager instance;
  private AssetManager manager;

  private Manager(){
    manager = new AssetManager();
  }

  public static Manager getIstance(){
    if(instance==null){instance = new Manager();}
    return instance;
  }

  public AssetManager getAssetManager(){
    return manager;
  }

  public void loadMainMenuScreen(){
    manager.load(Assets.menubackground.getPath(), Texture.class);
    manager.load(Assets.buttonOn.getPath(),Texture.class);
    manager.load(Assets.buttonOff.getPath(),Texture.class);
    manager.finishLoading();
  }

  public void loadPlayScreen(){
    manager.load(Assets.playBackground.getPath(),Texture.class);
    manager.load(Assets.healthBar.getPath(),Texture.class);
    manager.load(Assets.baseBar.getPath(),Texture.class);
    manager.finishLoading();
  }

  public void loadGameOverScreen(){
    manager.load(Assets.gameOverBackground.getPath(),Texture.class);
    manager.finishLoading();
  }

  public void loadPlaneSpawer(){
    manager.load(Assets.planeUp.getPath(),Texture.class);
    manager.load(Assets.planeDown.getPath(),Texture.class);
    manager.load(Assets.fastPlane.getPath(),Texture.class);
    manager.load(Assets.fastPlaneDown.getPath(),TextureAtlas.class);
//    manager.load(Assets.allied.getPath(),Texture.class);
//    manager.load(Assets.alliedDown.getPath(),Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane0.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane1.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane2.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane3.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane4.png",Texture.class);
    manager.finishLoading();
  }
  public void loadOptionScreen(){
    manager.finishLoading();
  }

}

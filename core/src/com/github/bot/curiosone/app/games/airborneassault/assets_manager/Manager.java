package com.github.bot.curiosone.app.games.airborneassault.assets_manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * @author Alessandro Roic
 * This class manages the assets by using a AssetManager
 */
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

  /**
   * Loads all the assets
   */
  public void loadAll(){
    manager.load(Assets.menubackground.getPath(), Texture.class);
    manager.load(Assets.playOn.getPath(),Texture.class);
    manager.load(Assets.playOff.getPath(),Texture.class);
    manager.load(Assets.optionOff.getPath(),Texture.class);
    manager.load(Assets.optionOn.getPath(),Texture.class);
    manager.load(Assets.exitOn.getPath(),Texture.class);
    manager.load(Assets.exitOff.getPath(),Texture.class);
    manager.load(Assets.menuMusic.getPath(),Music.class);
    manager.load(Assets.click.getPath(),Sound.class);
    manager.load(Assets.playBackground.getPath(),Texture.class);
    manager.load(Assets.playBackground2.getPath(),Texture.class);
    manager.load(Assets.playBackground3.getPath(),Texture.class);
    manager.load(Assets.healthBar.getPath(),Texture.class);
    manager.load(Assets.baseBar.getPath(),Texture.class);
    manager.load(Assets.playMusic.getPath(),Music.class);
    manager.load(Assets.backButton.getPath(),Texture.class);
    manager.load(Assets.backToButton.getPath(),Texture.class);
    manager.load(Assets.backToButtonPressed.getPath(), Texture.class);
    manager.load(Assets.continueButton.getPath(),Texture.class);
    manager.load(Assets.continueButtonPressed.getPath(),Texture.class);
    manager.load(Assets.gameOverBackground.getPath(),Texture.class);
    manager.load(Assets.font.getPath(),BitmapFont.class);
    manager.load(Assets.planeUp.getPath(),TextureAtlas.class);
    manager.load(Assets.planeDown.getPath(),TextureAtlas.class);
    manager.load(Assets.fastPlane.getPath(),Texture.class);
    manager.load(Assets.fastPlaneDown.getPath(),TextureAtlas.class);
    manager.load(Assets.healthPack.getPath(),Texture.class);
    manager.load(Assets.kamikaze.getPath(),TextureAtlas.class);
    manager.load(Assets.kamikazeDown.getPath(),TextureAtlas.class);
    manager.load(Assets.kamikazeSelfExplosion.getPath(),TextureAtlas.class);
    manager.load(Assets.allied.getPath(),Texture.class);
    manager.load(Assets.alliedDown.getPath(),TextureAtlas.class);
    manager.load(Assets.stealth.getPath(),Texture.class);
    manager.load(Assets.stealthDown.getPath(),TextureAtlas.class);
    manager.load(Assets.stealthInvisible.getPath(),TextureAtlas.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane0.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane1.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane2.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane3.png",Texture.class);
    manager.load("airborneassaultassets/planetextures/tankplanetextures/tankplane4.png",Texture.class);
    manager.load(Assets.tankDown.getPath(),TextureAtlas.class);
    manager.load(Assets.optionBackground.getPath(),Texture.class);
    manager.load(Assets.checkbox.getPath(),Texture.class);
    manager.load(Assets.checkbox2.getPath(),Texture.class);
    manager.load(Assets.font.getPath(), BitmapFont.class);
    manager.load(Assets.hit.getPath(),Sound.class);
    manager.load(Assets.hit2.getPath(),Sound.class);
    manager.load(Assets.hit3.getPath(),Sound.class);
    manager.load(Assets.shot.getPath(),Sound.class);
    manager.load(Assets.gameOverMusic.getPath(),Music.class);
    manager.finishLoading();
  }
}

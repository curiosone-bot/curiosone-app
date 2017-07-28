package com.github.bot.curiosone.app.games.wordtiles.assets_manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * @author Alessandro Roic
 * This class loads the assets
 */
public class Manager {

  private static Manager instance;
  private AssetManager manager;
  private static TextButton.TextButtonStyle textButtonStyle;
  private static TextureRegionDrawable tile;
  private static TextureRegionDrawable tileTouched;
  private static TextureRegionDrawable bombTileTouched;

  public  TextureRegionDrawable getTile() {
    return tile;
  }

  public  TextureRegionDrawable getTileTouched() {
    return tileTouched;
  }

  public  TextureRegionDrawable getBombTileTouched() {
    return bombTileTouched;
  }

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
    manager.load(Assets.background.getPath(), Texture.class);
    manager.load(Assets.buttonOn.getPath(),Texture.class);
    manager.load(Assets.buttonOff.getPath(),Texture.class);
    manager.load(Assets.font.getPath(), BitmapFont.class);
    manager.load(Assets.click.getPath(),Sound.class);
    manager.load(Assets.musicMenu.getPath(),Music.class);
    manager.finishLoading();
    TextureRegionDrawable up = new TextureRegionDrawable(new TextureRegion(manager.get(Assets.buttonOn.getPath(),Texture.class)));
    TextureRegionDrawable down = new TextureRegionDrawable(new TextureRegion(manager.get(Assets.buttonOff.getPath(),Texture.class)));
    textButtonStyle = new TextButton.TextButtonStyle(up,down,null,manager.get(Assets.font.getPath(),BitmapFont.class));
  }

  public static TextButton.TextButtonStyle getImageButtonStyle() {
    return textButtonStyle;
  }

  public void loadPlayScreen(){
    manager.load(Assets.playBackground.getPath(),Texture.class);
    manager.load(Assets.playMusic.getPath(),Music.class);
    manager.finishLoading();
  }

  public void loadGameOverScreen(){
    manager.load(Assets.gameOver.getPath(),Texture.class);
    manager.load(Assets.gameOverMusic.getPath(),Music.class);
    manager.finishLoading();
  }

  public void loadTileSpawer(){
    manager.load(Assets.tile.getPath(), Texture.class);
    manager.load(Assets.tileTouched.getPath(),Texture.class);
    manager.load(Assets.bombTileTouched.getPath(),Texture.class);
    manager.load(Assets.font.getPath(), BitmapFont.class);
    manager.load(Assets.click.getPath(),Sound.class);
    manager.load(Assets.hit.getPath(),Sound.class);
    manager.finishLoading();

    tile = new TextureRegionDrawable(new TextureRegion(manager.get(Assets.tile.getPath(),Texture.class)));
    tileTouched = new TextureRegionDrawable(new TextureRegion(manager.get(Assets.tileTouched.getPath(),Texture.class)));
    bombTileTouched = new TextureRegionDrawable(new TextureRegion(manager.get(Assets.bombTileTouched.getPath(),Texture.class)));
  }
  public void loadOptionScreen(){
    manager.load(Assets.cleanBackground.getPath(), Texture.class);
    manager.load(Assets.checkbox.getPath(),Texture.class);
    manager.load(Assets.checkbox2.getPath(),Texture.class);
    manager.load(Assets.font.getPath(),BitmapFont.class);
    manager.finishLoading();
  }

}

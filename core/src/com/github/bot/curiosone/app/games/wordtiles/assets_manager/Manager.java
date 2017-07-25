package com.github.bot.curiosone.app.games.wordtiles.assets_manager;

import com.badlogic.gdx.assets.AssetManager;

/**
 * @author Alessandro Roic
 * This class loads the assets
 */
public class Manager {

  private static Manager instance;
  private static AssetManager manager;

  private Manager(){
    manager = new AssetManager();
  }

  public static Manager getIstance(){
    if(instance==null){instance = new Manager();}
    return instance;
  }

  private AssetManager getManager(){
    return manager;
  }

  public void loadMainMenuScreen(){

  }

  public void loadPlayScreen(){

  }

  public void loadGameOverScreen(){

  }

  public void loadWinScreen(){

  }

  public void loadTile(){

  }

  public void loadWrongTile(){

  }

}

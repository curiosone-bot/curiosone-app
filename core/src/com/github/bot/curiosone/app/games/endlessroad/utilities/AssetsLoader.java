package com.github.bot.curiosone.app.games.endlessroad.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Manages the game assets loading process
 * @author Paolo Pierantozzi
 */
public class AssetsLoader
{
	private static AssetsLoader instance;
    private AssetManager manager;

    private AssetsLoader() {manager = new AssetManager();}
    
    /**
     * @returns the unique instance of AssetLoader
     */
    public static AssetsLoader getInstance()
    {
    	if (instance == null) instance = new AssetsLoader();
    	return instance;
    }
    
    /**
     * @returns an AssetsManager with all game assets loaded yet
     */
    public AssetManager getManager() {return manager;}

    /**
     * Loads all the assets needed for the main menu screen
     */
    public void loadMainMenuAssets()
    {
        manager.load(AssetsPaths.MENUS_BG.getPath(),Texture.class);
        manager.load(AssetsPaths.ENDLESS_ROAD.getPath(),Texture.class);
        manager.load(AssetsPaths.PLAY_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.RECORDS_BUTTON.getPath(),Texture.class);        
        manager.load(AssetsPaths.CREDITS_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.QUIT_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.MUSIC_GREEN.getPath(),Texture.class);
        manager.load(AssetsPaths.SPEAKER.getPath(),Texture.class);
        manager.load(AssetsPaths.MUSIC_RED.getPath(),Texture.class);
        manager.load(AssetsPaths.MUTE.getPath(),Texture.class);
        manager.load(AssetsPaths.MENU_LOOP.getPath(),Music.class);
        manager.load(AssetsPaths.ENGINE_ON.getPath(),Sound.class);
        manager.finishLoading();
    }
    
    /**
     * Loads all the assets needed for the gameplay screen
     */
    public void loadGameplayAssets()
    {
    	manager.load(AssetsPaths.IDLE.getPath(),Sound.class);
        manager.load(AssetsPaths.MUSIC_LOOP.getPath(),Music.class);
    	manager.load(AssetsPaths.AGENCY_FB.getPath(),BitmapFont.class);
        manager.load(AssetsPaths.ROAD.getPath(),Texture.class);
        manager.load(AssetsPaths.PLAYER.getPath(),Texture.class);
        manager.load(AssetsPaths.ACCELERATOR_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.BRAKE_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.LEFT_ARROW.getPath(),Texture.class);
        manager.load(AssetsPaths.RIGHT_ARROW.getPath(),Texture.class);
        for (int i=1;i<GameInfos.CARS_SPRITES_AMOUNT;i++) manager.load(AssetsPaths.valueOf("CAR"+i).getPath(),Texture.class);
        manager.load(AssetsPaths.BRAKE.getPath(),Sound.class);
        manager.load(AssetsPaths.CRASH.getPath(),Sound.class);
        manager.finishLoading();
    }

    /**
     * Loads all the assets needed for the game over screen
     */
    public void loadGameOverAssets()
    {
        manager.load(AssetsPaths.MENUS_BG.getPath(),Texture.class);
        manager.load(AssetsPaths.GAME_OVER.getPath(),Texture.class);
        manager.load(AssetsPaths.REPLAY_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.MENU_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.AGENCY_FB.getPath(),BitmapFont.class);
        manager.load(AssetsPaths.CROWN.getPath(),Texture.class);
        manager.load(AssetsPaths.GAMEOVER_LOOP.getPath(),Music.class);       
        manager.finishLoading();
    }
    
    /**
     * Loads all the assets needed for the credits screen
     */
    public void loadCreditsAssets()
	{
		manager.load(AssetsPaths.MENUS_BG.getPath(),Texture.class);
		manager.load(AssetsPaths.CREDITS.getPath(),Texture.class);
		manager.load(AssetsPaths.BACK_BUTTON.getPath(),Texture.class);
		manager.load(AssetsPaths.AGENCY_FB.getPath(),BitmapFont.class);
		manager.finishLoading();
	}
    
    /**
     * Loads all the assets needed for the records screen
     */
    public void loadRecordsAssets()
    {
    	manager.load(AssetsPaths.RECORDS.getPath(),Texture.class);
        manager.load(AssetsPaths.MENUS_BG.getPath(),Texture.class);
        manager.load(AssetsPaths.GAME_OVER.getPath(),Texture.class);
        manager.load(AssetsPaths.REPLAY_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.BACK_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.AGENCY_FB.getPath(),BitmapFont.class);
        manager.load(AssetsPaths.CROWN.getPath(),Texture.class);
        manager.finishLoading();
    }

    /**
     * Clears all the assets stored inside the manager
     */
    public void dispose()
    {
    	manager.clear();
    }





}

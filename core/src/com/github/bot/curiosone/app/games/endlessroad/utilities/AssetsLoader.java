package com.github.bot.curiosone.app.games.endlessroad.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Manages the game assets loading process
 * @author Paolo Pierantozzi
 */
public class AssetsLoader
{
    private AssetManager manager;

    public AssetsLoader() {manager = new AssetManager();}

    /**
     * Returns an AssetsManager with all game assets loaded yet
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
        manager.load(AssetsPaths.CREDITS_BUTTON.getPath(),Texture.class);
        manager.load(AssetsPaths.QUIT_BUTTON.getPath(),Texture.class);
        manager.finishLoading();
    }
    
    /**
     * Loads all the assets needed for the gameplay screen
     */
    public void loadGameplayAssets()
    {
        manager.load(AssetsPaths.ROAD.getPath(),Texture.class);
        manager.load(AssetsPaths.PLAYER.getPath(),Texture.class);

        for (int i=1;i<GameConstants.CARS_SPRITES_AMOUNT;i++) manager.load(AssetsPaths.valueOf("CAR"+i).getPath(),Texture.class);
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







}

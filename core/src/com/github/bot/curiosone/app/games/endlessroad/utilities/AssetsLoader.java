package com.github.bot.curiosone.app.games.endlessroad.utilities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Manages the game assets loading process
 * @author Paolo Pierantozzi
 */
public class AssetsLoader
{
    private AssetManager manager;

    public AssetsLoader() {manager = new AssetManager();}

    /**
     * Returns an AssetsManager with all game assets loaded
     */
    public AssetManager getManager() {return manager;}

    /**
     * Loads all the main menu assets
     */
    public void loadMainMenuAssets()
    {
        manager.load("EndlessRoad/Backgrounds/menus-bg.png",Texture.class);
        manager.load("EndlessRoad/Logos/endlessroad.png",Texture.class);
        manager.load("EndlessRoad/Buttons/playbutton.png",Texture.class);
        manager.load("EndlessRoad/Buttons/creditsbutton.png",Texture.class);
        manager.load("EndlessRoad/Buttons/quitbutton.png",Texture.class);
        manager.finishLoading();
    }
    /**
     * Loads all the gameplay assets
     */
    public void loadGameplayAssets()
    {
        manager.load("EndlessRoad/Backgrounds/road.png",Texture.class);
        manager.load("EndlessRoad/Cars/player.png",Texture.class);

        for (int i=1;i<9;i++) manager.load("EndlessRoad/Cars/car" + i + ".png",Texture.class);
        manager.finishLoading();
    }

    /**
     * Loads all the gameover assets
     */
    public void loadGameOverAssets()
    {
        manager.load("EndlessRoad/Backgrounds/menus-bg.png",Texture.class);
        manager.load("EndlessRoad/Logos/gameover.png",Texture.class);
        manager.load("EndlessRoad/Buttons/replaybutton.png",Texture.class);
        manager.load("EndlessRoad/Buttons/quitbutton.png",Texture.class);
        manager.finishLoading();
    }







}

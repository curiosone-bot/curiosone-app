package com.curiosone.wordcrushse.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.curiosone.wordcrushse.WordCrushSE;
import com.curiosone.wordcrushse.loaders.Assets;

/** Class that contain all the general fields and methods of games objects
 * Created by giuseppe on 16/08/17.
 */

public abstract class AbstractGameScreen extends ScreenAdapter
{
    protected WordCrushSE wc;
    boolean paused;

    public AbstractGameScreen(WordCrushSE wc)
    {
        this.wc = wc;
    }

    @Override
    public abstract void render (float deltaTime) ;

    @Override
    public abstract void resize (int width, int height);

    @Override
    public void pause () { paused = true; };

    @Override
    public abstract void show ();


    /**
     * Method that re-initialize the assets
     */
    public void resume () { Assets.instance.init(new AssetManager()); }

    /**
     * Method that dispose all assets
     */
    public void dispose () { Assets.instance.dispose();}

}

package com.github.bot.curiosone.app.games.wordcrush.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.wordcrush.*;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;
import com.github.bot.curiosone.app.games.wordcrush.screen.*;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldController;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldRenderer;

/** Class dedicated to game's scene
 * Created by giuseppe on 25/07/17.
 */

public class WordsCheckGameScreen extends AbstractGameScreen
{

    private WorldController worldController;
    private WorldRenderer worldRenderer;


    private Stage stage;
    private boolean paused;


    public WordsCheckGameScreen(WordCrushSE wc, WorldController worldController, final Chat game)
    {
        super(wc, game);
        this.worldController = worldController;
        this.worldRenderer = new WorldRenderer(worldController);
        Assets.instance.init(new AssetManager());
        worldController.initGame();
        worldController.setWordsCheckGameScreen(this);
    }

    @Override
    public void render (float delta)
    {
        stage.act(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldController.updateGame(delta);
        worldRenderer.renderGame();
    }



    @Override
    public void dispose ()
    {
        stage.dispose();
        super.dispose();
        Assets.instance.dispose();
    }

    public void resize (int width,int height)
    {
        worldRenderer.getCameraHelper().getViewport().update(width,height,true);
    }

    @Override
    public void pause()
    {
        paused = true;
    }

    @Override
    public void show()
    {
        Gdx.input.setCatchBackKey(true);

         /*Stage*/
        stage = new Stage(worldRenderer.getCameraHelper().getViewport());
        Gdx.input.setInputProcessor(stage);

        /* Add Actors */
        stage.addActor(worldController.getTileTable());
        stage.addActor(worldController.getTextBox());
        stage.addActor(worldController.getSkip());
        stage.addActor(worldController.getStringToPrint());
        stage.addActor(worldController.getMultiplicator());
        stage.addActor(worldController.getNumberOfMultiplicator());

        /* Set WorldRenderer Stage */
        worldRenderer.initRender(stage);
    }

    @Override
    public void resume ()
    {
        super.resume();
        // Only called on Android
        paused = false;
    }


}

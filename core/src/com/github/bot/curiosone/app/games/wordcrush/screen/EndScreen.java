package com.github.bot.curiosone.app.games.wordcrush.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.wordcrush.WordCrushSE;
import com.github.bot.curiosone.app.games.wordcrush.screen.AbstractGameScreen;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldController;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldRenderer;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;

/** Class that close the game
 * Created by giuseppe on 20/08/17.
 */

public class EndScreen extends AbstractGameScreen
{
    WorldController worldController;
    WorldRenderer worldRenderer;
    Stage stage;
    boolean paused;

    public EndScreen(WordCrushSE wc, WorldController worldController, final Chat game)
    {
        super(wc, game);
        Assets.instance.init(new AssetManager());
        this.worldController = worldController;
        worldRenderer = new WorldRenderer(worldController);
        worldController.initEnd();
        worldRenderer.initRender(stage);
    }


    @Override
    public void render(float deltaTime)
    {
        stage.act(deltaTime);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldController.updateEndScreen(deltaTime);
        worldRenderer.renderEnd();
    }

    @Override
    public void resize(int width, int height)
    {
        worldRenderer.getCameraHelper().getViewport().update(width,height,true);
    }

    @Override
    public void show()
    {
        Gdx.input.setCatchBackKey(true);

         /*Stage*/
        stage = new Stage(worldRenderer.getCameraHelper().getViewport());
        Gdx.input.setInputProcessor(stage);

        /* Add Actors */
        stage.addActor(worldController.getAgain());
        stage.addActor(worldController.getExit());
        for(Image c : worldController.getClouds())
            stage.addActor(c);
        /* Set WorldRenderer Stage */
        worldRenderer.initRender(stage);
    }

    @Override
    public void resume()
    {
        super.resume();
        paused = false;
    }

    @Override
    public void dispose ()
    {
        stage.dispose();
        super.dispose();
        Assets.instance.dispose();
    }
}

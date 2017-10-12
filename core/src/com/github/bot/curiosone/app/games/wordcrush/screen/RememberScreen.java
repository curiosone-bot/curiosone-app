package com.github.bot.curiosone.app.games.wordcrush.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.wordcrush.*;
import com.github.bot.curiosone.app.games.wordcrush.screen.AbstractGameScreen;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldController;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldRenderer;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

/**
 * Created by Giuseppe on 07/08/2017.
 */

public class RememberScreen extends AbstractGameScreen
{

    private WorldController worldController;
    private WorldRenderer worldRenderer;
    private Stage stage;

    public RememberScreen (WordCrushSE wc, final Chat game)
    {
        super (wc, game);
        worldController = new WorldController(wc, game);
        worldRenderer = new WorldRenderer(worldController);
        worldController.initRemember();
        worldController.setRememberScreen(this);
    }

    public RememberScreen (WordCrushSE wc, WorldController worldController, final Chat game)
    {
        super (wc, game);
        this.worldController = worldController;
        worldRenderer = new WorldRenderer(worldController);
        worldController.initRemember();
        worldController.setRememberScreen(this);
    }


    @Override
    public void render(float delta)
    {
        stage.act(delta);
        worldController.updateRemember(delta);
        worldRenderer.renderRemember();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {
        paused = true;
    }

    @Override
    public void show()
    {
        /* Stage */
        stage = new Stage(worldRenderer.getCameraHelper().getViewport());
        Gdx.input.setInputProcessor(stage);

        /* Add Actors to Stage */
        stage.addActor(worldController.getRememberLogo());
        stage.addActor(worldController.getBucket());
        stage.addActor(worldController.getWord());
        stage.getRoot().getColor().a = 0;
        stage.getRoot().addAction(fadeIn(0.1f));
        worldRenderer.initRender(stage);
    }

    @Override
    public void dispose()
    {
        super.dispose();
        stage.dispose();
    }


}

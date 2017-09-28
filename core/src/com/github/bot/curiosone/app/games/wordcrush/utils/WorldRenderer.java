package com.github.bot.curiosone.app.games.wordcrush.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Disposable;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;
import com.github.bot.curiosone.app.games.wordcrush.utils.*;
import com.github.bot.curiosone.app.games.wordcrush.utils.CameraHelper;

/** Class that draw the scene 2d on device screen
 * Created by giuseppe on 16/08/17.
 */

public class WorldRenderer implements Disposable
{
    private final String scoreString = "Score: ";
    private final String timerString = "Timer: ";
    private WorldController worldController;
    private CameraHelper cameraHelper;
    private SpriteBatch batch;
    private Stage stage;

    public WorldRenderer(WorldController worldController)
    {
        this.worldController = worldController;
        cameraHelper = new CameraHelper();
    }

    /**
     * Initialize the renderer
     * @param stage
     */
    public void initRender (Stage stage)
    {
        batch = new SpriteBatch();
        this.stage = stage;
    }


    /**
     * Method that render the game scene
     */
    public void renderGame ()
    {
        cameraHelper.getCamera().update();
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(worldController.getGUIFont(),scoreString + "");
        float scoreWidth = glyphLayout.width;
        glyphLayout.setText(worldController.getGUIFont(), String.format("%05d",worldController.getScore() ));
        float numberScoreWidth = glyphLayout.width;
        glyphLayout.setText(worldController.getGUIFont(), timerString);
        float timerWidth = glyphLayout.width;
        glyphLayout.setText(worldController.getGUIFont(), (int) worldController.getGameTimer()+"");
        float numberTimerWidth = glyphLayout.width;
        batch.setProjectionMatrix(cameraHelper.getCamera().combined);
        batch.begin();
        batch.disableBlending();
        batch.draw(Assets.instance.assetLevel.backgroundLevel,0,0);
        batch.enableBlending();
        worldController.getGUIFont().draw(batch, scoreString, (GameConstants.WIDTH - scoreWidth)/ 2 + 130,750);
        worldController.getGUIFont().draw(batch, String.format("%05d",worldController.getScore() ),(GameConstants.WIDTH - numberScoreWidth)/ 2 + 130,710);
        worldController.getGUIFont().draw(batch, timerString,(GameConstants.WIDTH - timerWidth)/2 -100,750);
        worldController.getGUIFont().draw(batch,  "" + (int) worldController.getGameTimer() ,(GameConstants.WIDTH - numberTimerWidth)/2 - 100 ,710);
        batch.end();
        stage.draw();
    }

    /**
     * Method that render the Remember Screen
     */
    public void renderRemember ()
    {
        cameraHelper.getCamera().update();
        batch.setProjectionMatrix(cameraHelper.getCamera().combined);
        batch.begin();
        batch.disableBlending();
        batch.draw(Assets.instance.assetsRememberDecoration.lowBlack,0,0);
        batch.enableBlending();
        worldController.getStarsEffect().draw(batch, Gdx.graphics.getDeltaTime());
        batch.end();
        stage.draw();
    }

    /**
     * Method that render the End Screen
     */
    public void renderEnd ()
    {
        cameraHelper.getCamera().update();
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(worldController.getDarkBlueFont(), worldController.getScore() + "");
        float w1 = glyphLayout.width;
        glyphLayout.setText( worldController.getYelowFont(), "Final Score: ");
        float w2 = glyphLayout.width;

        batch.setProjectionMatrix(cameraHelper.getCamera().combined);
        batch.begin();
        batch.disableBlending();
        batch.draw(Assets.instance.assetsEndDecoration.backgroundBlueNight,0,0);
        batch.enableBlending();
        worldController.getScoreBox().draw(batch,1);
        worldController.getYelowFont().draw(batch,"Final Score: ",(GameConstants.WIDTH - w2) /2,620);
        worldController.getDarkBlueFont().draw(batch,worldController.getScore() + "",
                (GameConstants.WIDTH - w1) /2 ,540);
        batch.end();
        stage.draw();
    }



    @Override
    public void dispose()
    {
        Assets.instance.dispose();
    }

    /**
     * Method that return the Camera Helper
     * @return CameraHelper
     */
    public CameraHelper getCameraHelper () { return cameraHelper;}
}

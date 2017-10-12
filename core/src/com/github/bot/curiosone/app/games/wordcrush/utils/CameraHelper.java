package com.github.bot.curiosone.app.games.wordcrush.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.bot.curiosone.app.games.wordcrush.utils.GameConstants;

/** Class that manage the camera
 * Created by giuseppe on 16/08/17.
 */

public class CameraHelper
{
    private OrthographicCamera camera;
    private FillViewport viewport;

    public CameraHelper()
    {
        /* Camera Settings */
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GameConstants.WIDTH, GameConstants.HEIGHT);
        camera.position.set(GameConstants.WIDTH / 2, GameConstants.HEIGHT / 2, 0);
        viewport = new FillViewport(GameConstants.WIDTH, GameConstants.HEIGHT, camera);
    }

    /**
     * Method that return the viewport
     * @return Viewport
     */
    public Viewport getViewport () { return viewport; }

    /**
     * Method that return the camera
     * @return
     */
    public OrthographicCamera getCamera () { return camera; }

}

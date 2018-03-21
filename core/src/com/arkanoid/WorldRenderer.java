package com.arkanoid;

import com.arkanoid.utils.Constants;
import com.arkanoid.utils.Resources;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;

/**
 * Class for scene 2D rendering, it's a singleton.
 * @author Simone
 *
 */
public class WorldRenderer implements Disposable {

    private static WorldRenderer instance;

    private float w, h;

    private Batch batch;
    private ShapeRenderer renderer;
    private Texture background;
    private BitmapFont font;
    private WorldController world;

    private WorldRenderer(WorldController world) {
        this.world = world;
        renderer = new ShapeRenderer();
        this.w = Gdx.graphics.getWidth();
        this.h = Gdx.graphics.getHeight();
        init();
    }

    public static WorldRenderer getInstance(WorldController world) {
        if (instance == null)
            instance = new WorldRenderer(world);
        return instance.setWorld(world);
    }

    private WorldRenderer setWorld(WorldController world) {
        this.world = world;
        init();
        return this;
    }

    private void init() {
        batch = world.getStage().getBatch();
        background = Resources.getInstance().getGameBackground();
        font = Resources.getInstance().getFont1();
    }

    public void render(float deltaTime) {
        batch.begin();
        batch.draw(background, 0, 0, w, h);
        for (int i = 0; i < world.getLives()-1; i++) {
            batch.draw(world.getPaddle().getSprite(),
                    (520-40*(i+1))*w/Constants.WIDTH,
                    10*h/ Constants.HEIGHT,
                    32*w/Constants.WIDTH,
                    8*h/Constants.HEIGHT);
        }
        world.getPaddle().draw(batch);
        font.draw(batch, "Score:" + world.getScore(), 10*w / Constants.WIDTH, 20*h / Constants.HEIGHT);
        batch.end();

        world.getLevel().render(renderer);
        world.getBall().render(renderer);
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}

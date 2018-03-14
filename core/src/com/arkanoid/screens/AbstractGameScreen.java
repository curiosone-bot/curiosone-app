package com.arkanoid.screens;

import com.arkanoid.utils.Resources;
import com.arkanoid.utils.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


/**
 *
 * Created by Simone on 28/02/2018.
 */

public abstract class AbstractGameScreen extends ScreenAdapter{

    protected Game game;

    protected Stage stage;
    protected Batch batch;
    protected Texture background;

    protected BitmapFont font1;
    protected BitmapFont font2;
    protected TextButton.TextButtonStyle style1;
    protected TextButton.TextButtonStyle style2;

    protected Table table;

    protected Music music;

    protected float w;
    protected float h;

    /**
     * Constructor for menu screens
     * @param game the main game instance
     */
    public AbstractGameScreen(Game game) {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        this.game = game;
        stage = new Stage();
        batch = stage.getBatch();
        background = Resources.getInstance().getMenuBackground();
        font1 = Resources.getInstance().getFont1();
        font1.getData().setScale(w / Constants.WIDTH, h / Constants.HEIGHT);
        font2 = Resources.getInstance().getFont2();
        font2.getData().setScale(w / Constants.WIDTH, h / Constants.HEIGHT);
        style1 = new TextButton.TextButtonStyle(null, null, null, font1);
        style2 = new TextButton.TextButtonStyle(null, null, null, font2);
        table = new Table();
        //table.debug();
        table.setFillParent(true);
        music = Resources.getInstance().getMenuMusic();
        music.setLooping(true);
    }

    /**
     * Constructor for the game screen
     * @param game the main game instance
     * @param startLevel the level to start from
     */
    public AbstractGameScreen(Game game, int startLevel) {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        this.game = game;
        stage = new Stage();
        background = Resources.getInstance().getGameBackground();
        font1 = Resources.getInstance().getFont1();
        font1.getData().setScale(w / Constants.WIDTH, h / Constants.HEIGHT);
        font2 = Resources.getInstance().getFont2();
        font2.getData().setScale(w / Constants.WIDTH, h / Constants.HEIGHT);
        style1 = new TextButton.TextButtonStyle(null, null, null, font1);
        style2 = new TextButton.TextButtonStyle(null, null, null, font2);
        table = new Table();
        table.setFillParent(true);
        music = Resources.getInstance().getGameMusic();
        music.setLooping(true);
    }

    protected void scaleButtons() {
        for (Actor actor : stage.getActors()) {
            if (actor instanceof TextButton) {
                TextButton button = (TextButton)actor;
                button.setTransform(true);
                button.setPosition(w / Constants.WIDTH * button.getX(), h / Constants.HEIGHT * button.getY());
            }
        }
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, w / Constants.WIDTH*background.getWidth(), h / Constants.HEIGHT*background.getHeight());
        batch.end();
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void show() {
        music.play();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        Resources.getInstance().dispose();
    }

}

package com.arkanoid.screens;

import com.arkanoid.utils.Resources;
import com.arkanoid.utils.Constants;
import com.arkanoid.utils.IllegalFormatException;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

/**
 * Class part of the menu screens.
 * This is the screen for selection mode, which allows to choose the start level,
 * by using the left and right arrow {@link TextButton}.
 * a miniature displays the preview of the level selected.
 * Created by Simone on 28/02/2018.
 */
public class SelectionModeScreen extends AbstractGameScreen {

    private static SelectionModeScreen instance;

    private TextButton backButton, leftArrow, rightArrow, startButton;

    private Texture miniature;
    private int selectedLevel;

    private SelectionModeScreen(final Game game) {
        super(game);

        backButton = new TextButton("Back", style1);
        backButton.setPosition(265, 160, Align.center);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backButton.setStyle(style1);
                selectedLevel = 1;
                miniature = Resources.getInstance().getMiniature(1);
                game.setScreen(GameModeScreen.getInstance(game));
            }
        });

        leftArrow = new TextButton("<", style1);
        leftArrow.setPosition(205, 280, Align.left);
        leftArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftArrow.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftArrow.setStyle(style1);
                if (selectedLevel == 1) {
                    selectedLevel = 10;
                } else selectedLevel--;
                miniature = Resources.getInstance().getMiniature(selectedLevel);
            }
        });

        rightArrow = new TextButton(">", style1);
        rightArrow.setPosition(325, 280, Align.right);
        rightArrow.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightArrow.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightArrow.setStyle(style1);
                if (selectedLevel == 10) {
                    selectedLevel = 1;
                } else selectedLevel++;
                miniature = Resources.getInstance().getMiniature(selectedLevel);
            }
        });

        startButton = new TextButton("Start", style1);
        startButton.setPosition(265, 240, Align.center);
        startButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                startButton.setStyle(style1);
                try {
                    game.setScreen(new GameScreen(game, selectedLevel));
                } catch (IllegalFormatException e) {
                    e.printStackTrace();
                }
                selectedLevel = 1;
                miniature = Resources.getInstance().getMiniature(selectedLevel);
                music.stop();
            }
        });

        selectedLevel = 1;
        miniature = Resources.getInstance().getMiniature(selectedLevel);
        stage.addActor(backButton);
        stage.addActor(leftArrow);
        stage.addActor(rightArrow);
        stage.addActor(startButton);
        scaleButtons();

    }

    public static SelectionModeScreen getInstance(Game game) {
        if (instance == null) {
            instance = new SelectionModeScreen(game);
        }
        return instance;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, w/Constants.WIDTH*background.getWidth(), h/Constants.HEIGHT*background.getHeight());
        batch.draw(miniature, w/Constants.WIDTH*165, h/Constants.HEIGHT*330,
                w/Constants.WIDTH*miniature.getWidth(), h/Constants.HEIGHT*miniature.getHeight());
        batch.end();
        stage.draw();
        stage.act(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}

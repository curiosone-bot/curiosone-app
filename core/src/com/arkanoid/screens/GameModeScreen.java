package com.arkanoid.screens;

import com.arkanoid.utils.IllegalFormatException;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

/**
 * Class part of the menu screens.
 * This allows to choose between two game modes, classic mode and selection mode.
 * Classic mode {@link TextButton} starts a new game from the first level, switches to {@link GameScreen}
 * Selection mode {@link TextButton} allows to choose the level to start from, opening the {@link SelectionModeScreen}
 * Created by Simone on 28/02/2018.
 */
public class GameModeScreen extends AbstractGameScreen {

    private static GameModeScreen instance;

    private TextButton classicModeButton, selectionModeButton, backButton;

    public static GameModeScreen getInstance(Game game) {
        if (instance == null) {
            instance = new GameModeScreen(game);
        }
        return instance;
    }

    private GameModeScreen(final Game game) {
        super(game);

        classicModeButton = new TextButton("Classic Mode", style1);
        classicModeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                classicModeButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                classicModeButton.setStyle(style1);
                music.stop();
                try {
                    game.setScreen(new GameScreen(game, 1));
                } catch (IllegalFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        selectionModeButton = new TextButton("Selection Mode", style1);
        selectionModeButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                selectionModeButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                selectionModeButton.setStyle(style1);
                game.setScreen(SelectionModeScreen.getInstance(game));
            }
        });

        backButton = new TextButton("Back", style1);
        backButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backButton.setStyle(style1);
                game.setScreen(MainMenuScreen.getInstance(game));
            }
        });

        table.add(classicModeButton).center().expandY();
        table.row();
        table.add(selectionModeButton).center().uniformY();
        table.row();
        table.add(backButton).expandY();
        stage.addActor(table);

    }

}

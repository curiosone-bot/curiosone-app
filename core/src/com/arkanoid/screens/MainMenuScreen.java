package com.arkanoid.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;


/**
 * Class for the main menu screen, implemented as a singleton.
 * Contains {@link TextButton} implemented with anonymous class.
 * Exit button closes the application {@link Gdx#app#exit()}
 * Play button change the screen to {@link GameModeScreen}
 *
 * @author Simone Sanfratello
 */
public class MainMenuScreen extends AbstractGameScreen {

    private static MainMenuScreen instance;

    private TextButton playButton, exitButton, creditsButton;

    public static MainMenuScreen getInstance(Game game) {
        if (instance == null)
            instance = new MainMenuScreen(game);
        return instance;
    }

    private MainMenuScreen(final Game game) {
        super(game);

        playButton = new TextButton("Play", style1);
        playButton.setPosition(265, 500, Align.center);
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playButton.setStyle(style2);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playButton.setStyle(style1);
                game.setScreen(GameModeScreen.getInstance(game));
            }
        });

        creditsButton = new TextButton("Credits", style1);
        creditsButton.setPosition(265, 350, Align.center);
        creditsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                creditsButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                creditsButton.setStyle(style1);
                game.setScreen(CreditsScreen.getInstance(game));
            }
        });


        exitButton = new TextButton("Exit", style1);
        exitButton.setPosition(265, 200, Align.center);
        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                exitButton.setStyle(style2);
                return true;
            }
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                exitButton.setStyle(style1);
                dispose();
                Gdx.app.exit();
            }
        });

        stage.addActor(playButton);
        stage.addActor(exitButton);
        stage.addActor(creditsButton);
        scaleButtons();
    }

}
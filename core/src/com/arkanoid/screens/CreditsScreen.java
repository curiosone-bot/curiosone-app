package com.arkanoid.screens;

import com.arkanoid.utils.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

/**
 * Class for the credits screen
 * Created by Simone on 09/03/2018.
 */

public class CreditsScreen extends AbstractGameScreen {

    private static CreditsScreen instance;

    private TextButton backButton;

    public static CreditsScreen getInstance(Game game) {
        if (instance == null)
            instance = new CreditsScreen(game);
        return instance;
    }

    private CreditsScreen(final Game game) {
        super(game);

        backButton = new TextButton("Back", style1);
        backButton.setPosition(265, 200, Align.center);
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

        stage.addActor(backButton);
        scaleButtons();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        font1.draw(batch, "Created by", 160 * w / Constants.WIDTH, 500 * h / Constants.HEIGHT);
        font1.draw(batch, "Simone Sanfratello", 100 * w / Constants.WIDTH, 460 * h / Constants.HEIGHT);
        batch.end();
    }

}

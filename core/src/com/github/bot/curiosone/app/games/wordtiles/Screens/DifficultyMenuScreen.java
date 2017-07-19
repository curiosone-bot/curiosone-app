package com.github.bot.curiosone.app.games.wordtiles.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.workflow.Chat;

public class DifficultyMenuScreen extends ScreenAdapter {

    private Chat game;
    private TextButton easy,normal,hard,extreme;

    public DifficultyMenuScreen(Chat game) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    private void draw() {
        //disegna i vari bottoni
    }

    private void update() {

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

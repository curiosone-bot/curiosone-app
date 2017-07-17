package com.github.bot.curiosone.app.workflow.com.curiosone.wordtiles.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.github.bot.curiosone.app.GameCenter;

public class DifficultyMenuScreen extends ScreenAdapter {

    private GameCenter game;
    private TextButton easy,normal,hard,extreme;

    public DifficultyMenuScreen(GameCenter game) {
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

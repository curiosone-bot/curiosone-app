package com.github.bot.curiosone.app.com.curiosone.wordtiles.Screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.curiosone.wordtiles.WordTiles;

public class DifficultyMenuScreen extends ScreenAdapter {

    private WordTiles game;
    private TextButton easy,normal,hard,extreme;

    public DifficultyMenuScreen(WordTiles game) {
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

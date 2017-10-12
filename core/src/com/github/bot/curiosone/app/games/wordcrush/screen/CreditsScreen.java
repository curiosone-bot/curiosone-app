package com.github.bot.curiosone.app.games.wordcrush.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.games.wordcrush.screen.*;
import com.github.bot.curiosone.app.games.wordcrush.utils.GameConstants;
import com.github.bot.curiosone.app.games.wordcrush.WordCrushSE;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;
import com.github.bot.curiosone.app.games.wordcrush.loaders.AudioManager;

/** Class dedicated to copyright and credits
 * Created by giuseppe on 24/08/17.
 */

public class CreditsScreen extends AbstractGameScreen
{

    public CreditsScreen (WordCrushSE wc, final Chat game)
    {
        super(wc, game);
    }
    @Override
    public void render(float deltaTime)
    {
        /* Create text and set align */
        String created = "Created by:";
        String giuseppeRinaldi = "Giuseppe Rinaldi";
        String copyright = " Copyright @2017";
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(Assets.instance.getBitmapFontBlack(),created);
        float one = glyphLayout.width;
        glyphLayout.setText(Assets.instance.getBitmapFontBlack(),giuseppeRinaldi);
        float two = glyphLayout.width;
        glyphLayout.setText(Assets.instance.getBitmapFontBlack(), copyright);
        float three = glyphLayout.width;

        /* Batch Manage */
        wc.getBatch().begin();
        wc.getBatch().disableBlending();
        wc.getBatch().draw(Assets.instance.assetMenu.background,0,0);
        wc.getBatch().enableBlending();

        Assets.instance.getBitmapFontBlack().draw(wc.getBatch(),created, (GameConstants.WIDTH - one)/2, GameConstants.HEIGHT/2+50);
        Assets.instance.getBitmapFontBlack().draw(wc.getBatch(),giuseppeRinaldi, (GameConstants.WIDTH - two)/2, GameConstants.HEIGHT/2 - 50);
        Assets.instance.getBitmapFontBlack().draw(wc.getBatch(),copyright, (GameConstants.WIDTH - three)/2, GameConstants.HEIGHT/2 - 100);

        wc.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {  }

    @Override
    public void show()
    {
        InputProcessor backProcessor = new InputAdapter()
        {
            @Override
            public boolean keyDown(int keycode)
            {

                if ((keycode == Input.Keys.ESCAPE) || (keycode == Input.Keys.BACK) )
                {
                    // Maybe perform other operations before exiting
                    AudioManager.instance.play(Assets.instance.assetSound.clickMenuButton);
                    game.setScreen(new MenuScreen(CreditsScreen.this.wc, game));
                }
                return false;
            }
        };
        Gdx.input.setInputProcessor(backProcessor);
    }
}

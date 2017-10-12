package com.github.bot.curiosone.app.games.wordcrush.game_object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;
import com.github.bot.curiosone.app.games.wordcrush.loaders.AudioManager;
import com.github.bot.curiosone.app.games.wordcrush.utils.GameConstants;

/**
 * Created by giuseppe on 24/08/17.
 */

public class Word extends Label
{
    ParticleEffect particleEffect;
    boolean isUsed;

    public Word(CharSequence text, Skin skin, String string, Color color, ParticleEffect particleEffect)
    {
        super(text, skin,string, color);
        this.particleEffect = particleEffect;
        this.particleEffect.setPosition(GameConstants.WIDTH/2, GameConstants.HEIGHT/2);

    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        super.draw(batch,parentAlpha);
        if (!isUsed)
        {
            this.particleEffect.start();
            AudioManager.instance.stopMusic();
            AudioManager.instance.play(Assets.instance.assetSound.tumCrash);
            isUsed = true;
        }
        particleEffect.draw(batch,0);
    }

    public void reset()
    {
        isUsed = false;
    }


}

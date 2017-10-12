package com.github.bot.curiosone.app.games.wordcrush;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.games.wordcrush.utils.*;
import com.github.bot.curiosone.app.games.wordcrush.loaders.Assets;
import com.github.bot.curiosone.app.games.wordcrush.loaders.AudioManager;
import com.github.bot.curiosone.app.games.wordcrush.screen.MenuScreen;
import com.github.bot.curiosone.app.games.wordcrush.utils.WorldRenderer;

/**
 * Main class, it initialize all the game
 * @author Giuseppe Rinaldi
 */
public class WordCrushSE extends ScreenAdapter {

	private SpriteBatch batch;


	public WordCrushSE ()
	{
		Assets.instance.init(new AssetManager());
		GamePreferences.instance.load();
		AudioManager.instance.play(Assets.instance.assetMusic.hawaii);
		batch = new SpriteBatch();

	}

	@Override
	public void dispose ()
	{
		batch.dispose();
		super.dispose();
		Assets.instance.dispose();
	}

	/**
	 * Method used to return the batch
	 * @return
	 */
	public SpriteBatch getBatch() { return batch;}
}

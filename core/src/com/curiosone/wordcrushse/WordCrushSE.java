package com.curiosone.wordcrushse;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.curiosone.wordcrushse.utils.*;
import com.curiosone.wordcrushse.loaders.Assets;
import com.curiosone.wordcrushse.loaders.AudioManager;
import com.curiosone.wordcrushse.screen.MenuScreen;
import com.curiosone.wordcrushse.utils.WorldRenderer;

/**
 * Main class, it initialize all the game
 * @author Giuseppe Rinaldi
 */
public class WordCrushSE extends Game {

	private SpriteBatch batch;
	private int w,h; // Width & Height of camera

	@Override
	public void create ()
	{
		Assets.instance.init(new AssetManager());
		GamePreferences.instance.load();
		AudioManager.instance.play(Assets.instance.assetMusic.hawaii);
		batch = new SpriteBatch();
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		setScreen( new MenuScreen(this,w,h));
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

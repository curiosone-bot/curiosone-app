package com.curiosone.wordcrushse.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.curiosone.wordcrushse.utils.GameConstants;
import com.curiosone.wordcrushse.WordCrushSE;

public class DesktopLauncher {
	public static void main (String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConstants.WIDTH;
		config.height = GameConstants.HEIGHT;
		new LwjglApplication(new WordCrushSE(), config);
	}
}

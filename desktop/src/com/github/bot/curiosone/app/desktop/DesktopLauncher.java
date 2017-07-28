package com.github.bot.curiosone.app.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.bot.curiosone.app.chat.Chat;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = 272;
    config.height = 408;
    config.title = "Curiosone";
		new LwjglApplication(new Chat(), config);
	}
}

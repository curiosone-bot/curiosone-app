package com.github.bot.curiosone.app;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.helpers.AndroidView;
import com.github.bot.curiosone.app.chat.helpers.View;

public class AndroidLauncher extends AndroidApplication {

  private View rootView;
  private AndroidView androidView;
  private int width, height;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new Chat(), config);
	}
}

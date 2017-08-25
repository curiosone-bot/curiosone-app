package com.github.bot.curiosone.app;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.helpers.AndroidView;
import com.github.bot.curiosone.app.chat.helpers.ApplicationBundle;


public class AndroidLauncher extends AndroidApplication {

  private View rootView;
  private AndroidView androidView;
  private int width, height;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    rootView = this.getWindow().getDecorView().getRootView();
    Rect rect = new Rect();
    rootView.getWindowVisibleDisplayFrame(rect);
    width = rect.width();
    height = rect.height();
    androidView = new AndroidView(width, height);
    rootView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        Rect rect =new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        if(!(width == rect.width() && height == rect.height())) {
          width = rect.width();
          height = rect.height();
          androidView.onSizeChange(width, height);
        }
      }
    });
    //initialize(new Chat(), config);

    initialize(new Chat(new ApplicationBundle(androidView)), config);


  }

  public AndroidView getAndroidView(){return androidView;}
}

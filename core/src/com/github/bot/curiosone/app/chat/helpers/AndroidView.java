package com.github.bot.curiosone.app.chat.helpers;

import java.util.ArrayList;

/**
 * Created by federico-pc on 21/08/2017.
 */

public class AndroidView implements View {

  private ArrayList<SizeChangeListener> listeners = new ArrayList<SizeChangeListener>();
  private float width, height;
  public AndroidView(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public void addListener(SizeChangeListener listener) {
    listeners.add(listener);
  }

  public void onSizeChange(float width, float height) {
    this.width = width;
    this.height = height;
    for(SizeChangeListener listener : listeners)
      listener.onSizeChange(width, height);
  }

  public float getWidth() {
    return width;
  }

  public float getHeight() {
    return height;
  }
}

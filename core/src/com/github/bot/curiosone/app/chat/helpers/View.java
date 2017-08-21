package com.github.bot.curiosone.app.chat.helpers;


public interface View {
  public void onSizeChange(float width, float height);
  public void addListener(SizeChangeListener sizeChangeListener);
  public float getWidth();
  public float getHeight();
}

package com.github.bot.curiosone.app.chat.helpers;


 public interface View {
   void onSizeChange(float width, float height);
   void addListener(SizeChangeListener sizeChangeListener);
   float getWidth();
   float getHeight();
}

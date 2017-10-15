package com.github.bot.curiosone.app.chat.helpers;


/*
 * this interface is used for calculate height of keyboard with another class/interface
 * */
 public interface View {
   void onSizeChange(float width, float height);
   void addListener(SizeChangeListener sizeChangeListener);
   float getWidth();
   float getHeight();
}

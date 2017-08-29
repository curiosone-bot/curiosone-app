package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {

  public static Skin skin;
  public static Texture send;

  public static void load() {
    skin = new Skin();
  }

  public static void dispose() {
//    send.dispose();
  }

}

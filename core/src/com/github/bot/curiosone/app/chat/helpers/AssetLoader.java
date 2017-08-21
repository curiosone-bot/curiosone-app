package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {

  public static Skin skin;
  public static Texture send;

  public static void load() {
    skin = new Skin();
    send = new Texture(Gdx.files.internal("chat-asset/send.png"));
    skin.add("send", send);
  }

  public static void dispose() {
    send.dispose();
  }

}

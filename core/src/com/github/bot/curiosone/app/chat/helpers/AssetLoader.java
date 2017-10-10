package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum;

import static com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum.*;

public class  AssetLoader {

  public static Skin skin;
  public static Texture bg;
  public static Skin defaultSkin;

  public static void load(StyleEnum style) {
    switch (style) {
      case FUTURISTIC:
        dispose();
        skin = new Skin(Gdx.files.internal("chat-asset/Skin.json"));
        bg = new Texture("chat-asset/bg.png");
        ChatElementFactory.setStyle(FUTURISTIC);
        break;
      case MODERN:
        dispose();
        skin = new Skin(Gdx.files.internal("chat-asset/ModernSkin.json"));
        bg = new Texture("chat-asset/ChatBg.png");
        ChatElementFactory.setStyle(MODERN);
        break;
    }
    defaultSkin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));
  }

  public static void dispose() {
    if(skin != null) {
      skin.dispose();
      bg.dispose();
      defaultSkin.dispose();
    }
  }

}

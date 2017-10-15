package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum;

import static com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum.*;

public class  AssetLoader {

  public static Skin skin;
  public static Image bg, menuBg;
  public static Skin defaultSkin;
  public static Sound click = Gdx.audio.newSound(Gdx.files.internal("chat-asset/click.mp3"));
  public static Sound blop = Gdx.audio.newSound(Gdx.files.internal("chat-asset/Blop.mp3"));
  /*
 * this class is used for load all assets in all program
 * */
  public static void load(StyleEnum style) {
    switch (style) {
      case FUTURISTIC:
        dispose();
        bg = new Image(new TextureAtlas(Gdx.files.internal("chat-asset/Skin.atlas")).findRegion("chat_background"));
        menuBg = new Image(new TextureAtlas(Gdx.files.internal("chat-asset/Skin.atlas")).findRegion("menu_background"));
        skin = new Skin(Gdx.files.internal("chat-asset/Skin.json"));
        ChatElementFactory.setStyle(FUTURISTIC);
        break;
      case MODERN:
        dispose();
        bg = new Image(new TextureAtlas(Gdx.files.internal("chat-asset/DarkBlue.atlas")).findRegion("chat_background"));
        menuBg = new Image(new TextureAtlas(Gdx.files.internal("chat-asset/DarkBlue.atlas")).findRegion("menu_background"));
        skin = new Skin(Gdx.files.internal("chat-asset/DarkBlue.json"));
        ChatElementFactory.setStyle(MODERN);
        break;
    }
    defaultSkin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));
  }

  public static void dispose() {
    if(skin != null) {
      skin.dispose();
      defaultSkin.dispose();
    }
  }

}

package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class  AssetLoader {

  public static Skin skin;
  public static Texture bg;
  public static Skin defaultSkin;

  public static void load() {
    skin = new Skin(Gdx.files.internal("chat-asset/Skin.json"));
    bg = new Texture("chat-asset/bg.png");
    defaultSkin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));
  }

  public static void dispose() {
    skin.dispose();
    bg.dispose();
  }

}

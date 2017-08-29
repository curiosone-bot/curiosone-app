package com.github.bot.curiosone.app.chat.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class  AssetLoader {

  public static Skin skin;
  public static Texture bg = new Texture("chat-asset/bg.png");

  public static void load() {
    skin = new Skin(Gdx.files.internal("chat-asset/Skin.json"));
  }

  public static void dispose() {
    skin.dispose();
    bg.dispose();
  }

}

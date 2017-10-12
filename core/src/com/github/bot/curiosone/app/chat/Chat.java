package com.github.bot.curiosone.app.chat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.bot.curiosone.app.chat.helpers.ApplicationBundle;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory;
import com.github.bot.curiosone.app.chat.helpers.ScreenEnum;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.helpers.SizeChangeListener;
import com.github.bot.curiosone.app.chat.helpers.View;
import com.github.bot.curiosone.app.chat.world.ChatWorld;
import static com.github.bot.curiosone.app.chat.helpers.ChatElementFactory.StyleEnum.*;

import java.io.IOException;



public class Chat extends Game {

  private View view;
  private Stage stage;
  private boolean fromAndroid = false;
  private Skin skin;
  public static float keyboardHeight;
  public static boolean keyboard;

  public Chat(ApplicationBundle applicationBundle) {
    view = applicationBundle.getView();
    fromAndroid = true;
  }

  public Chat() {}

  @Override
  public void create() {
    final Preferences prefs = Gdx.app.getPreferences("Preferences");
    if(fromAndroid){
      skin = new Skin(Gdx.files.internal("chat-asset/Skin.json"));
      stage = new Stage();
      final TextField tf1 = new TextField("", skin);
      final TextField tf2 = new TextField("", skin);
      tf1.setWidth((float)view.getWidth() * 0.6f);
      tf2.setWidth((float)view.getWidth() * 0.6f);
      tf1.setHeight((float)view.getHeight() * 0.05f);
      tf2.setHeight((float)view.getHeight() * 0.05f);
      view.addListener(new SizeChangeListener() {
        @Override
        public void onSizeChange(float width, float height) {
          keyboardHeight = getKeyboardHeight();
          keyboard = keyboardHeight!=0;
          tf1.addAction(Actions.moveTo(width / 2 - tf1.getWidth() / 2.0f, keyboardHeight + (6 * (height / 8)), 1, Interpolation.sineOut));
          tf2.addAction(Actions.moveTo(width / 2 - tf2.getWidth() / 2.0f, keyboardHeight + (7 * (height / 8)), 1, Interpolation.sineOut));
        }
      });
    }
    AssetLoader.load(getType(prefs.getInteger("style")));
    ScreenManager.getInstance().initialize(this);
    try {
      ScreenManager.getInstance().showScreen(ScreenEnum.MENU);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void dispose() {
    super.dispose();
    AssetLoader.dispose();
    ChatWorld.resetScrollpane();
  }

  private float getKeyboardHeight() {
    return stage.getHeight() - view.getHeight();
  }
}

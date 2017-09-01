package com.github.bot.curiosone.app.chat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.bot.curiosone.app.chat.helpers.ApplicationBundle;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.SizeChangeListener;
import com.github.bot.curiosone.app.chat.helpers.View;
import com.github.bot.curiosone.app.chat.screens.ChatScreen;



public class Chat extends Game {

  private SpriteBatch batch ;
  private View view;
  private Stage stage;
  private boolean fromAndroid = false;
  private Skin skin;
  public static float keyboardHeight;
  public static boolean keyboard;

  public SpriteBatch getBatch() {
    return batch;
  }

  public Chat(ApplicationBundle applicationBundle) {
    view = applicationBundle.getView();
    fromAndroid = true;
  }

  public Chat() {}

  @Override
  public void create() {
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
    batch = new SpriteBatch();
    AssetLoader.load();
    setScreen(new ChatScreen());
  }

  @Override
  public void dispose() {
    super.dispose();
    batch.dispose();
    AssetLoader.dispose();
  }

  private float getKeyboardHeight() {
    return stage.getHeight() - view.getHeight();
  }
}

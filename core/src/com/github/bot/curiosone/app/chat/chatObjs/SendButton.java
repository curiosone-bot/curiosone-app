package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.TalkRequestResponse;
import com.github.bot.curiosone.app.chat.world.AITemporanea;
import com.github.bot.curiosone.app.chat.world.ChatWorld;
import com.github.bot.curiosone.app.chat.world.ServerConnection;

import java.io.IOException;
import java.util.StringTokenizer;



public class SendButton extends ImageButton {

  private ChatWorld world;

  private AITemporanea ai = new AITemporanea();
  private ServerConnection sc = new ServerConnection();

  public SendButton(float width, float height, float x, float y) throws IOException {
    super(AssetLoader.skin);
    this.setPosition(x, y);
    this.setSize(width, height);
    this.addListener(this.click());
  }

  private void onClick() throws IOException {
    Inserimento inserimento = world.getInserimento();
    if(!inserimento.getText().isEmpty()) {
      world.addMessage(inserimento.getText(), "User");
      new Thread() {
        public void run() {
          world.getInserimento().setDisabled(true);
          try {
            world.addMessage(sc.getAnswer(new TalkRequestResponse(world.getInserimento().getText(), world.lastBotMessage.getScope())), "Bot");
          } catch (IOException e) {
            e.printStackTrace();
          }
          world.getInserimento().setDisabled(false);
        }
      }.start();

      System.out.println(Thread.getAllStackTraces().keySet().size());
      inserimento.setText("");
    }
    this.addListener(this.click());
  }

  private ClickListener click() {
    return new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        try {
          onClick();
        } catch (IOException e1) {
          e1.printStackTrace();
        }

      }
    };
  }

  public void setWorld(ChatWorld world) {
    this.world = world;
  }

}

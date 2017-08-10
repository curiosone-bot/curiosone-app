package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.world.AITemporanea;
import com.github.bot.curiosone.app.chat.world.ChatWorld;


public class SendButton extends TextButton {

  private ChatWorld world;
  private Skin skin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));  // Da mettere in AssetLoader
  private AITemporanea ai = new AITemporanea();

  public SendButton(float width, float height, float x, float y) {
    super("send", new Skin(Gdx.files.internal("chat-asset/uiskin.json")));
    this.setPosition(x, y);
    this.setSize(width, height);
    this.addListener(this.click());
  }

  private void onClick() {
    Table table = world.getScrollTable();
    Inserimento inserimento = world.getInserimento();
    ScrollPane scrollPane = world.getScrollpane();
    table.row();
    table.add(new Label(inserimento.getText(), skin)).expandX().right();
    table.row();
    table.add(new Label(ai.getRisposta(inserimento.getText()), skin)).expandX().left();
    scrollPane.scrollTo(0, 0, scrollPane.getWidth(), scrollPane.getHeight());
    table.bottom();

    inserimento.setText("");
    this.addListener(this.click());
  }

  private ClickListener click() {
    return new ClickListener() {
      @Override
      public void touchUp(InputEvent e, float x, float y, int point, int button) {
        onClick();
      }
    };
  }

  public void setWorld(ChatWorld world) {
    this.world = world;
  }
}

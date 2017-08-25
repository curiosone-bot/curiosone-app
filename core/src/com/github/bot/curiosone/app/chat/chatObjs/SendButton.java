package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.bot.curiosone.app.chat.world.AITemporanea;
import com.github.bot.curiosone.app.chat.world.ChatWorld;

import java.util.StringTokenizer;



public class SendButton extends TextButton {

  private ChatWorld world;
  private Skin skin = new Skin(Gdx.files.internal("chat-asset/uiskin.json"));  // Da mettere in AssetLoader
  private Skin skinLabelUtente = new Skin(Gdx.files.internal("chat-asset/labelVerde.json"));  // Da mettere in AssetLoader
  private Skin skinLabelBot = new Skin(Gdx.files.internal("chat-asset/labelBianca.json"));  // Da mettere in AssetLoader

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
    if(!inserimento.getText().isEmpty()) {
      table.row();
      table.add(new Label(modifyPhrase(inserimento.getText()), skinLabelUtente)).expandX().right();
      table.row();
      table.add(new Label(modifyPhrase(ai.getRisposta(inserimento.getText())), skinLabelBot)).expandX().left();
      table.bottom();
      scrollPane.layout();
      scrollPane.scrollTo(0, 0, scrollPane.getWidth(), scrollPane.getHeight());
      inserimento.setText("");
    }
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

  private static String modifyPhrase(String phrase) { //taglio a 24 caratteri
    StringTokenizer st=new StringTokenizer(phrase);
    String newPhrase="";
    final int TAGLIO_FRASE=12; //originale 24
    String stringaTemporanea;
    int dimensioneFraseTemporanea=0; //per vedere se si e' arrivati al limite della frase
    while(st.hasMoreTokens()) {
      stringaTemporanea = st.nextToken();
      if(stringaTemporanea.length() + dimensioneFraseTemporanea > TAGLIO_FRASE) {
        newPhrase+="\n";
        dimensioneFraseTemporanea=0;
      }
      dimensioneFraseTemporanea+=stringaTemporanea.length();
      newPhrase+=stringaTemporanea+" ";
    }
      return newPhrase;
  }

}

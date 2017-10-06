package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.bot.curiosone.app.chat.Chat;
import com.github.bot.curiosone.app.chat.chatObjs.Inserimento;
import com.github.bot.curiosone.app.chat.chatObjs.SendButton;
import com.github.bot.curiosone.app.chat.helpers.AssetLoader;
import com.github.bot.curiosone.app.chat.helpers.ScreenManager;
import com.github.bot.curiosone.app.chat.helpers.TalkRequestResponse;
import com.github.bot.curiosone.app.chat.helpers.ChatElementFactory;

import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import static com.badlogic.gdx.math.MathUtils.random;


public class ChatWorld {

  private SendButton send;
  private Inserimento inserimento;
  private ChatRender render;
  private static ScrollPane scrollPane;
  public  static Table scrollTable;
  public Image bg = new Image(AssetLoader.bg);
  private ImageButton gameButton;
  private Button menuButton;
  private int cliccato = 2;
  public static TalkRequestResponse lastBotMessage;

  public ChatWorld() throws IOException {
    gameButton = ChatElementFactory.getGameButton();

    menuButton = ChatElementFactory.getMenuButton();

    this.inserimento = ChatElementFactory.getInserimento();
    this.send = ChatElementFactory.getSendButton();
    this.lastBotMessage = new TalkRequestResponse();

    if(scrollPane == null || scrollTable == null) {
      scrollTable = new Table();
      scrollPane = ChatElementFactory.getScrollPane(scrollTable, inserimento);
    }
  }

  public void update(float delta) {
    //Gdx.app.log("ChatWorld", "update");


    render.getStage().act(delta);
    inserimento.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight() + getIncremento(40));
    scrollPane.setY(inserimento.getY()+ inserimento.getHeight() + 20);
    scrollPane.setHeight(700 - inserimento.getY() - inserimento.getHeight() - 20);
    send.setY(Chat.keyboardHeight * 800 / Gdx.graphics.getHeight() + getIncremento(52));

    if(Chat.keyboard) {
      if (cliccato > 0) {
        scrollPane.scrollTo(0, 0, 0, 0);
        cliccato--;
      }
    }
    else {
      cliccato = 2;
    }
  }

  public void addMessage (String message, String user) {
    scrollTable.row();
    Cell cell = scrollTable.add(createLabel(modifyPhrase(playTheGames(message,user)), AssetLoader.skin.get(user, Label.LabelStyle.class))).expandX();
    if(user.equals("User")) cell.right();
    else cell.left();
    inserimento.setDisabled(false);
    scrollTable.bottom();
    scrollPane.layout();
    scrollPane.scrollTo(0, 0, scrollPane.getWidth(), scrollPane.getHeight());
  }

  public void setRender(ChatRender render) {
    this.render = render;
  }

  public SendButton getSendButton() {
    return send;
  }

  public Inserimento getInserimento() {
    return inserimento;
  }

  private Label createLabel(String text, Label.LabelStyle style)
  {
    Label l = new Label(text, style);
    l.setFontScale(0.9f);
    return l;
  }

  public ScrollPane getScrollPane() {return scrollPane;}

  public Table getScrollTable() {
    return scrollTable;
  }

  public Image getBg() {
    return bg;
  }

  public ImageButton getGameButton() {
    return gameButton;
  }

  public Button getMenuButton() {
    return menuButton;
  }

  public int getIncremento(int n) { return Chat.keyboardHeight != 0 ? 17 : n; }

  private static String modifyPhrase(String phrase) { //taglio a 24 caratteri
    StringTokenizer st = new StringTokenizer(phrase);
    String newPhrase = "";
    final int TAGLIO_FRASE = 20; //originale 24
    String stringaTemporanea;
    int dimensioneFraseTemporanea=0; //per vedere se si e' arrivati al limite della frase
    while(st.hasMoreTokens()) {
      stringaTemporanea = st.nextToken();
      if(stringaTemporanea.length() + dimensioneFraseTemporanea > TAGLIO_FRASE) {
        newPhrase += "\n";
        dimensioneFraseTemporanea = 0;
      }
      dimensioneFraseTemporanea += stringaTemporanea.length();
      newPhrase+=stringaTemporanea + " ";
    }
    return newPhrase;
  }

  private String playTheGames(String message, String user )
  {
    if(!user.equals("User") /*&& message.equals("Please let's talk about something different than me.")*/)
    {
        inserimento.setDisabled(true);
        PlayGame pg = new PlayGame("play time",AssetLoader.skin);
        pg.show(render.getStage());
        return pg.getAnswer();

    }
    return message;
  }

  public static class PlayGame extends Dialog
  {
    private String result;
    private String[] games= {"Arkanoid","WordCrush"};

    public PlayGame(String title, Skin skin) {
      super(title, skin);
    }

    public PlayGame(String title, Skin skin, String windowStyleName) {
      super(title, skin, windowStyleName);
    }

    public PlayGame(String title, WindowStyle windowStyle) {
      super(title, windowStyle);
    }

    {
      int i= random.nextInt(1);
      text("do you want play "+games[i]);
      button("yes","ok, let's play "+games[i]);
      button("no","ok, maybe later");
    }

    @Override
    protected void result(Object object) {
      super.result(object);
      result = (String) object;
      if(!object.equals("ok, maybe later"))
      {
        String game = ((String) object).replace("ok, let's play ","");

      }
    }


    public String getAnswer()
    {
      return result;
    }
  }



  public static void resetScrollpane() {
    scrollPane = null;
    scrollTable = null;
  }
}

package com.github.bot.curiosone.app.chat.chatObjs;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.bot.curiosone.app.chat.world.ChatWorld;

public class Inserimento extends TextField {
  private boolean play = false;
  private ChatWorld world;

  public Inserimento(int width, int height, int x, int y, Skin skin) {
    super("", skin);
    this.setSize(width, height);
    this.setPosition(x,y);
    this.getStyle().background.setLeftWidth(this.getStyle().background.getLeftWidth()+20);
    this.getStyle().background.setRightWidth(this.getStyle().background.getRightWidth()+20);
  }

  @Override
  public String getText(){
    if (play) {
      if (super.getText().toLowerCase().startsWith("yes") || super.getText().toLowerCase().startsWith("yea")){
        world.openDialog();
      }
      play = false;
    }
    return super.getText();
  }

  public void setWorld(ChatWorld world) {
    this.world = world;
  }

  public void setPlay(boolean play) {
    System.out.print(play);
    this.play = play;
  }
}

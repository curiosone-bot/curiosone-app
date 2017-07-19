package com.github.bot.curiosone.app.chat.chatscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.bot.curiosone.app.chat.Chat;

public class ChatScreen extends ScreenAdapter {
  private static int WIDTH=480;
  private static int HEIGHT=800;
  private Chat chat;
  private SpriteBatch sb;
  private Texture space;
  private Texture goku;
  private OrthographicCamera cam;


  public ChatScreen (Chat chat) {
    this.chat = chat;
    this.sb = chat.getBatch();
    WIDTH=Gdx.graphics.getWidth();
    HEIGHT=Gdx.graphics.getHeight();
    cam = new OrthographicCamera();
    cam.setToOrtho(false,WIDTH,HEIGHT);
    cam.position.set(WIDTH/2,HEIGHT/2,0);
    space = new Texture(Gdx.files.internal("chat-asset/space.png"));
    goku = new Texture(Gdx.files.internal("chat-asset/gokuBlue.png"));

  }

  @Override
  public void render(float delta) {
    super.render(delta);
  //  sb.draw(space,0,0,WIDTH,HEIGHT);
    sb.begin();
    sb.draw(space,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    sb.draw(goku,WIDTH/4,HEIGHT/4,WIDTH/2,HEIGHT/2);
    cam.update();
    sb.end();
  }

  @Override
  public void resize(int width, int height) {
    super.resize(width, height);
  }

  @Override
  public void dispose() {
    super.dispose();
    goku.dispose();
    space.dispose();
  }
}

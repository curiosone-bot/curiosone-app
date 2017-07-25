package com.github.bot.curiosone.app.chat.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ChatRender {

  private OrthographicCamera cam;
  private ChatWorld world;

  public ChatRender(ChatWorld world) {
    this.world = world;
    this.cam = new OrthographicCamera();
    cam.setToOrtho(false, 136, 204);
  }

  public void render() {
    Gdx.app.log("ChatRender", "render");
    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    world.getStage().draw();
  }
}

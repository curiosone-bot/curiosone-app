package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;

public class AlliedPlane extends Actor {
  private Sprite alliedTexture, alliedDown;
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;

  public AlliedPlane(int x) {
    settings = Settings.getIstance();
    manager = Manager.getIstance();
    alliedTexture = new Sprite(manager.getAssetManager().get(Assets.allied.getPath(),Texture.class));
    alliedDown = new Sprite(manager.getAssetManager().get(Assets.alliedDown.getPath(),Texture.class));
    alliedTexture.setBounds(x,800,90,180);
    this.setBounds(x,800,90,180);
    addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        //animare e mettere fade
        alliedDown.setPosition(alliedTexture.getX(),alliedTexture.getY());
        alliedTexture = alliedDown;
        setTouchable(Touchable.disabled);
        Player.damage(Amount.ALLIED.getAmount());
        touched = true;
        disposable = true;
      }
    });
//      hit = manager.getAssetManager().get(Assets.hit.getPath());

  }

  /**
   * @param dt
   * Updates the single plane logic, has to be called into the main screen
   * update method.
   */
  @Override
  public void act(float dt) {
    //While the plane is still in the screen, move it
    if (alliedTexture.getY() > -this.getHeight()) {
      alliedTexture.setPosition(alliedTexture.getX(), alliedTexture.getY() - (Speed.PLANE.getSpeed()+settings.ACCELERATION)*dt);
      setPosition(alliedTexture.getX(), alliedTexture.getY());
    }
    if (alliedTexture.getY() < -this.getHeight()) {
      //If the plane is out of bound, dispose it
      disposable = true;
      Gdx.app.log("AlliedPlane"+hashCode(),"Touched = "+touched);
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch,parentAlpha);
    alliedTexture.draw(batch,parentAlpha);
  }

  @Override
  public boolean remove() {
    return disposable;
  }
}

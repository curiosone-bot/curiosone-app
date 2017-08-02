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

/**
 * @author Alessandro Roic
 * This class represent the tile the player don't have to touch
 */
public class KamikazePlane extends Actor {
  private Sprite kamikazeTexture, kamikazeDown;
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;

  public KamikazePlane(int x) {
    settings = Settings.getIstance();
    manager = Manager.getIstance();
    kamikazeTexture = new Sprite(manager.getAssetManager().get(Assets.kamikaze.getPath(),Texture.class));
    kamikazeDown = new Sprite(manager.getAssetManager().get(Assets.kamikazeDown.getPath(),Texture.class));
    kamikazeTexture.setBounds(x,800,100,180);
    kamikazeDown.setBounds(x,800,100,180);
    this.setBounds(x,800,100,180);
    addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        //inserire animazione di distruzione e fade
        kamikazeDown.setPosition(kamikazeTexture.getX(),kamikazeTexture.getY());
        kamikazeTexture = kamikazeDown;
        setTouchable(Touchable.disabled);
        disposable = true;
        touched = true;
        settings.ACCELERATION++;
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
    if (kamikazeTexture.getY() > -this.getHeight()) {
      kamikazeTexture.setPosition(kamikazeTexture.getX(), kamikazeTexture.getY() - (Speed.PLANE.getSpeed()+settings.ACCELERATION)*dt);
      setPosition(kamikazeTexture.getX(), kamikazeTexture.getY());
    }
    if (kamikazeTexture.getY() < -this.getHeight()) {
      //If the plane is out of bound, dispose it
      disposable = true;
      Gdx.app.log("Plane"+hashCode(),"Touched = "+touched);
      if(!touched){
        Player.damage(Amount.KAMIKAZE.getAmount());
      }
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch,parentAlpha);
    kamikazeTexture.draw(batch,parentAlpha);
  }

  @Override
  public boolean remove() {
    return disposable;
  }
}

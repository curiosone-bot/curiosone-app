package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;

/**
 * @author Alessandro Roic
 * This class represent the plane the player has
 * to touch
 */
public class Plane extends Actor
{
  private Sprite planeTexture,planeDown;
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;

  public Plane(int x) {
      settings = Settings.getIstance();
      manager = Manager.getIstance();
      planeTexture= new Sprite(manager.getAssetManager().get(Assets.planeUp.getPath(),Texture.class));
      planeDown = new Sprite(manager.getAssetManager().get(Assets.planeDown.getPath(),Texture.class));
      planeTexture.setBounds(x,800,118,200);
      this.setBounds(x,800,118,200);
      addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            planeTexture = planeDown;
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
    if (planeTexture.getY() > -this.getHeight()) {
      planeTexture.setPosition(planeTexture.getX(), planeTexture.getY() - (Speed.PLANE.getSpeed()+settings.ACCELERATION)*dt);
      setPosition(planeTexture.getX(),planeTexture.getY());
    }
    if (planeTexture.getY() < -this.getHeight()) {
      //If the plane is out of bound, dispose it
        disposable = true;
        Gdx.app.log("Plane"+hashCode(),"Touched = "+touched);
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch,parentAlpha);
    planeTexture.draw(batch,parentAlpha);
  }

  @Override
  public boolean remove() {
    return disposable;
  }

}


package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Points;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;

import java.util.Random;

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
  private Animation<TextureAtlas> base;
  private Animation<TextureAtlas> damaged;

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
            planeDown.setBounds(planeTexture.getX(),planeTexture.getY(),planeTexture.getWidth(),planeTexture.getHeight());
            planeTexture = planeDown;
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            int random = new Random().nextInt(40);
            int random2 = new Random().nextInt(60);
            int random3 = new Random().nextInt(100);
            if(random==10){Plane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
            if(random2==20){Plane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
            if(random3==30){Plane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
            setTouchable(Touchable.disabled);
            touched = true;
            Settings.addScore(Points.PLANE);
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


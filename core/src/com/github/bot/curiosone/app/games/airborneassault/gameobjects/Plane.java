package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
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
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;
  private Animation<TextureRegion> base;
  private Animation<TextureRegion> explosion;
  private TextureAtlas planeUp,planeDown;
  private float elapsedTime;

  public Plane(int x) {
      settings = Settings.getIstance();
      manager = Manager.getIstance();
      this.setBounds(x,800,118,200);
      planeUp = manager.getAssetManager().get(Assets.planeUp.getPath());
      planeDown = manager.getAssetManager().get(Assets.planeDown.getPath());
      base = new Animation<TextureRegion>(0.2f,planeUp.getRegions(), Animation.PlayMode.LOOP);
      explosion = new Animation<TextureRegion>(0.12f,planeDown.getRegions());
      addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
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
            settings.addScore(Points.PLANE);
            elapsedTime = 0;
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
    elapsedTime+= dt;
    if (this.getY() > -this.getHeight()) {
      this.setPosition(this.getX(), this.getY() - (Speed.PLANE.getSpeed()+settings.getAccelleration())*dt);
      setPosition(this.getX(),this.getY());
    }
    if (this.getY() < -this.getHeight()) {
      //If the plane is out of bound, dispose it
        if(!touched){
          Player.damage(Amount.PLANE.getAmount());
        }
        disposable = true;
        Gdx.app.log("Plane"+hashCode(),"Touched = "+touched);

    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch,parentAlpha);
    if(!touched) {
      batch.draw(base.getKeyFrame(elapsedTime),this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
    else {
      batch.draw(explosion.getKeyFrame(elapsedTime),this.getX(),this.getY(),this.getWidth(),this.getHeight());
      if(explosion.isAnimationFinished(elapsedTime)){
        disposable = true;
      }
    }
  }

  @Override
  public boolean remove() {
    return disposable;
  }

}


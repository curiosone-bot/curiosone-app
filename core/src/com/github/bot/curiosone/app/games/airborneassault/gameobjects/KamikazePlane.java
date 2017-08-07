package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Points;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;

import java.util.Random;

/**
 * @author Alessandro Roic
 * This class represent the tile the player don't have to touch
 */
public class KamikazePlane extends Actor {
  private Sprite kamikazeTexture;
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private int bombTimer = 0;
  private Animation<TextureRegion> explosion;
  private float elapsedTime;

  public KamikazePlane(int x) {
    settings = Settings.getIstance();
    Manager manager = Manager.getIstance();
    kamikazeTexture = new Sprite(manager.getAssetManager().get(Assets.kamikaze.getPath(),Texture.class));
    TextureAtlas textureAtlas = manager.getAssetManager().get(Assets.kamikazeDown.getPath());
    explosion = new Animation<TextureRegion>(0.5f,textureAtlas.getRegions());
    kamikazeTexture.setBounds(x,800,100,180);
    this.setBounds(x,800,100,180);
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
        if(random==10){KamikazePlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
        if(random2==20){KamikazePlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
        if(random3==30){KamikazePlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
        //inserire animazione di distruzione e fade
        setTouchable(Touchable.disabled);
        settings.addScore(Points.KAMIKAZE);
        touched = true;
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
    if(touched){elapsedTime+= dt;}
    //While the plane is still in the screen, move it
    if (kamikazeTexture.getY() > -this.getHeight()) {
      kamikazeTexture.setPosition(kamikazeTexture.getX(), kamikazeTexture.getY() - (Speed.PLANE.getSpeed()+settings.getAccelleration())*dt);
      setPosition(kamikazeTexture.getX(), kamikazeTexture.getY());
      if(!touched){
        bombTimer+= 1;
        if(bombTimer==180) {
          touched = true;
          Player.damage(Amount.KAMIKAZE.getAmount());
        }
      }
    }

    if (kamikazeTexture.getY() < -this.getHeight()) {
      //If the plane is out of bound, dispose it
      disposable = true;
      Gdx.app.log("Plane"+hashCode(),"Touched = "+touched);
    }

  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch,parentAlpha);
    if(!touched){
      kamikazeTexture.draw(batch,parentAlpha);
    }
    else {
      batch.draw(explosion.getKeyFrame(elapsedTime),getX(),getY(),getWidth(),getHeight());
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

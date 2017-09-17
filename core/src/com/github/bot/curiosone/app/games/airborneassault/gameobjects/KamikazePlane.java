package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Timer;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.*;

import java.util.Random;

/**
 * @author Alessandro Roic
 * This plane explodes if the player hasnt' touched it, dealing damage.
 */
public class KamikazePlane extends Actor {
    private boolean disposable = false,touched = false,explode = false,flag = true;
    private Sound hit;
    private Settings settings;
    private Timer bombTimer;
    private Animation<TextureRegion> base,explosion,selfExplosion;
    private float elapsedTime;

    public KamikazePlane(int x) {
        settings = Settings.getIstance();
        Manager manager = Manager.getIstance();
        bombTimer = new Timer();
        //Load the textures and animations
        hit = manager.getAssetManager().get(Assets.hit2.getPath());
        TextureAtlas baseTexture = manager.getAssetManager().get(Assets.kamikaze.getPath());
        TextureAtlas textureAtlas = manager.getAssetManager().get(Assets.kamikazeDown.getPath());
        TextureAtlas textureAtlas2 = manager.getAssetManager().get(Assets.kamikazeSelfExplosion.getPath());
        base = new Animation<TextureRegion>(0.12f,baseTexture.getRegions());
        base.setPlayMode(Animation.PlayMode.LOOP);
        explosion = new Animation<TextureRegion>(0.12f,textureAtlas.getRegions());
        selfExplosion = new Animation<TextureRegion>(0.12f,textureAtlas2.getRegions());
        //Sets the bounds of the plane and its properties
        this.setBounds(x,Constants.TOP,Dimensions.KAMIKAZE.getWidth(),Dimensions.KAMIKAZE.getHeight());
        addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if(settings.isSFX()){hit.play(0.35f);}
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            //Randomly spawns the healthpack
            int random = new Random().nextInt(40);
            int random2 = new Random().nextInt(60);
            int random3 = new Random().nextInt(100);
            if(random==10){KamikazePlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
            if(random2==20){KamikazePlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
            if(random3==30){KamikazePlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
            setTouchable(Touchable.disabled);
            settings.addScore(Points.KAMIKAZE);
            touched = true;
            elapsedTime = 0;
          }
        });
    }

    /**
     * @param dt
     * Updates the single plane logic, has to be called into the main screen
     * update method.
     */
    @Override
    public void act(float dt) {
      elapsedTime+= dt;
      //While the plane is still in the screen, move it
      if (this.getY() > -this.getHeight()) {
        this.setPosition(this.getX(), this.getY()- (Speed.KAMIKAZE.getSpeed()+settings.getAccelleration())*dt);
        if((!touched)&&(this.getY()<(Constants.TOP-Dimensions.KAMIKAZE.getHeight()))&&flag){
          //Starts the bomb timer
          bombTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
              if(settings.isSFX()){hit.play(0.35f);}
              setTouchable(Touchable.disabled);
              elapsedTime = 0;
              explode = true;
              Player.damage(Amount.KAMIKAZE.getAmount());
            }
          },1.5f - (((float)settings.getAccelleration()/(float)Speed.KAMIKAZE.getSpeed())/10f));
          bombTimer.start();
          //Prevents the timer to start again
          flag = false;
        }
      }

      if (this.getY() < -this.getHeight()) {
        //If the plane is out of bound, dispose it
        disposable = true;
        Gdx.app.log("Plane"+hashCode(),"Touched = "+touched);
      }

    }

  /**
   * Draws the plane into the provided batch
   * @param batch
   * @param parentAlpha
   */
    @Override
    public void draw(Batch batch, float parentAlpha) {
      super.draw(batch,parentAlpha);
      if(!touched){
        if(!explode){
          //Draws the base animation
          batch.draw(base.getKeyFrame(elapsedTime),getX(),getY(),getWidth(),getHeight());
        }
        else {
          //Draws the self explosion animation
          batch.draw(selfExplosion.getKeyFrame(elapsedTime),getX(),getY(),getWidth(),getHeight());
          if(selfExplosion.isAnimationFinished(elapsedTime)){
            disposable = true;
          }
        }
      }
      else {
        //Draws the explosion animation
        batch.draw(explosion.getKeyFrame(elapsedTime),getX(),getY(),getWidth(),getHeight());
        if(explosion.isAnimationFinished(elapsedTime)){
          disposable = true;
        }
      }
    }

  /**
   * @return true if the plane can be disposed.
   */
    @Override
    public boolean remove() {
      return disposable;
    }
}

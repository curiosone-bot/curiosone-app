package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.*;

import java.util.Random;

/**
 * @author Alessandro Roic
 * This class represent a plane with faster speed than the normal one.
 */
public class FastPlane extends Actor {
    private Sprite fastPlaneTexture;
    private TextureAtlas fastPlaneDown;
    private boolean disposable = false,touched = false;
    private Sound hit;
    private Settings settings;
    private Manager manager;
    private Animation<TextureRegion> explosion;
    private float elapsedTime;

    public FastPlane(int x) {
        settings = Settings.getIstance();
        manager = Manager.getIstance();
        hit = manager.getAssetManager().get(Assets.hit2.getPath());
        //Load the textures and animations
        fastPlaneTexture= new Sprite(manager.getAssetManager().get(Assets.fastPlane.getPath(),Texture.class));
        fastPlaneDown = manager.getAssetManager().get(Assets.fastPlaneDown.getPath());
        explosion = new Animation<TextureRegion>(0.085f,fastPlaneDown.getRegions());
        //Sets the bounds of the plane and its properties
        fastPlaneTexture.setBounds(x,Constants.TOP, Dimensions.FASTPLANE.getWidth(),Dimensions.FASTPLANE.getHeight());
        this.setBounds(x,Constants.TOP,Dimensions.FASTPLANE.getWidth(),Dimensions.FASTPLANE.getHeight());
        addListener(new InputListener(){
          @Override
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if(settings.isSFX()){hit.play(0.35f);}
            return true;
          }

          @Override
          public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            //Randomly spawn the healthpack
            int random = new Random().nextInt(40);
            int random2 = new Random().nextInt(60);
            int random3 = new Random().nextInt(100);
            if(random==10){FastPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
            if(random2==20){FastPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
            if(random3==30){FastPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
            setTouchable(Touchable.disabled);
            touched = true;
            settings.addScore(Points.FASTPLANE);
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
        //While the plane is still in the screen, move it
        if(touched){elapsedTime += dt;}
        if (fastPlaneTexture.getY() > -this.getHeight()) {
          fastPlaneTexture.setPosition(fastPlaneTexture.getX(), fastPlaneTexture.getY() - (Speed.FASTPLANE.getSpeed()+settings.getAccelleration())*dt);
          setPosition(fastPlaneTexture.getX(),fastPlaneTexture.getY());
        }
        if (fastPlaneTexture.getY() < -this.getHeight()) {
          //If the Plane is out of bounds, then dispose it
          if(!touched){
            Player.damage(Amount.FASTPLANE.getAmount());
          }
          disposable = true;
          Gdx.app.log("FastPlane"+hashCode(),"Touched = "+touched);
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
        //Draws the base texture
        fastPlaneTexture.draw(batch,parentAlpha);
      }
      else {
        //Draws the explosion animation
        batch.draw(explosion.getKeyFrame(elapsedTime),fastPlaneTexture.getX(),fastPlaneTexture.getY(),fastPlaneTexture.getWidth(),fastPlaneTexture.getHeight());
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

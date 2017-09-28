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

/**
 * @author Alessandro Roic
 * This plane must not be touched, otherwise the player will be damaged.
 */
public class AlliedPlane extends Actor {
    private Sprite alliedTexture;
    private boolean disposable = false,touched = false;
    private Sound hit;
    private Settings settings;
    private Manager manager;
    private Animation<TextureRegion> explosion;
    private float elapsedTime;

    public AlliedPlane(int x) {
      settings = Settings.getIstance();
      manager = Manager.getIstance();
      hit = manager.getAssetManager().get(Assets.hit2.getPath());
      //Load the textures and animations
      alliedTexture = new Sprite(manager.getAssetManager().get(Assets.allied.getPath(),Texture.class));
      TextureAtlas textureAtlas = manager.getAssetManager().get(Assets.alliedDown.getPath());
      explosion = new Animation<TextureRegion>(0.12f,textureAtlas.getRegions());
      //Sets the bounds of the plane and its properties
      alliedTexture.setBounds(x, Constants.TOP,Dimensions.ALLIED.getWidth(),Dimensions.ALLIED.getHeight());
      this.setBounds(x,Constants.TOP, Dimensions.ALLIED.getWidth(),Dimensions.ALLIED.getHeight());
      addListener(new InputListener(){
        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          if(settings.isSFX()){hit.play(0.35f);}
          return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          setTouchable(Touchable.disabled);
          Player.damage(Amount.ALLIED.getAmount());
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
      //While the plane is still in the screen, move it
      elapsedTime += dt;
      if (alliedTexture.getY() > -this.getHeight()) {
        alliedTexture.setPosition(alliedTexture.getX(), alliedTexture.getY() - (Speed.PLANE.getSpeed()+settings.getAccelleration())*dt);
        setPosition(alliedTexture.getX(), alliedTexture.getY());
      }
      if (alliedTexture.getY() < -this.getHeight()) {
        //If the plane is out of bound, dispose it
        disposable = true;
        Gdx.app.log("AlliedPlane"+hashCode(),"Touched = "+touched);
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
        //Draws the base animation
        alliedTexture.draw(batch,parentAlpha);
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

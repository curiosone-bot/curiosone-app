package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.*;

import java.util.Random;

/**
 * @author Alessandro Roic
 * This plane has to be touched 5 times in order to destroy it
 */
public class TankPlane extends Actor {

    private Array<Sprite> textures;
    private Animation<TextureRegion> explosion;
    private boolean disposable = false,touched = false;
    private Sound hit,shot;
    private Settings settings;
    private Manager manager;
    private Sprite current;
    private int count = 0,speedLimit = 400;
    private float elapsedTime;

    public TankPlane(int x) {
        settings = Settings.getIstance();
        manager = Manager.getIstance();
        hit = manager.getAssetManager().get(Assets.hit3.getPath());
        shot = manager.getAssetManager().get(Assets.shot.getPath());
        //Load the textures and animations
        TextureAtlas textureAtlas = manager.getAssetManager().get(Assets.tankDown.getPath());
        explosion = new Animation<TextureRegion>(0.12f,textureAtlas.getRegions());
        textures = new Array<Sprite>();
        for(int i=0;i<5;i++){
            String path= "airborneassaultassets/planetextures/tankplanetextures/tankplaneX.png";
            path = path.substring(0,63)+i+path.substring(64);
            textures.add(new Sprite(manager.getAssetManager().get(path,Texture.class)));
        }
        current = textures.get(0);
        //Sets the bounds of the plane and its properties
        current.setBounds(x,Constants.TOP, Dimensions.TANK.getWidth(),Dimensions.TANK.getHeight());
        this.setBounds(x,Constants.TOP,Dimensions.TANK.getWidth(),Dimensions.TANK.getHeight());
        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
              if(count<5){
                if(settings.isSFX()){shot.play(0.35f);}
                Sprite next = textures.get(count);
                next.setBounds(current.getX(),current.getY(),current.getWidth(),current.getHeight());
                current = next;
              }
              return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            count++;
            //If it has been touched 5 times, destroy it.
            if(count==5) {
              if(settings.isSFX()){hit.play(0.35f);}
              //randomly spawns the healthpack
              int random = new Random().nextInt(40);
              int random2 = new Random().nextInt(60);
              int random3 = new Random().nextInt(100);
              if(random==10){TankPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
              if(random2==20){TankPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
              if(random3==30){TankPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
              setTouchable(Touchable.disabled);
              settings.addScore(Points.TANK);
              touched = true;
              elapsedTime = 0;
            }
        }
      });
    }

  /**
   * Draws the plane into the provided batch
   * @param batch
   * @param parentAlpha
   */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(!touched){
          current.draw(batch,parentAlpha);
        }
        else {
          batch.draw(explosion.getKeyFrame(elapsedTime),getX(),getY(),getWidth(),getHeight());
          if(explosion.isAnimationFinished(elapsedTime)){
            disposable = true;
          }
        }
    }
  /**
   * @param delta
   * Updates the single plane logic, has to be called into the main screen
   * update method.
   */
    @Override
    public void act(float delta) {
        super.act(delta);
        //While the plane is still in the screen, move it
        elapsedTime += delta;
        if (current.getY() > -this.getHeight()) {
          if(Speed.TANK.getSpeed()+settings.getAccelleration()<speedLimit){
            current.setPosition(current.getX(), current.getY() - (Speed.TANK.getSpeed()+settings.getAccelleration())*delta);
          }
          else{
            current.setPosition(current.getX(),current.getY()-speedLimit);
          }
          setPosition(current.getX(),current.getY());
        }
        if (current.getY() < -this.getHeight()) {
          //If the Plane has been touched, then dispose it
          if(!touched) {
            Player.damage(Amount.TANK.getAmount());
            Gdx.app.log("Player Damaged By", "Tank-" + this.hashCode());
          }
          disposable = true;
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

package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
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
 * This plane has the ability to hide himself and change position.
 */
public class StealthPlane extends Actor{
    private Sprite stealthTexture;
    private boolean disposable = false,touched = false, flag = false;
    private Sound hit;
    private Settings settings;
    private int lastMoved = 0;
    private float elapsedTime;
    private Timer.Task timer;
    private Animation<TextureRegion> invisible,explosion;

    public StealthPlane(int x) {
        settings = Settings.getIstance();
        Manager manager = Manager.getIstance();
        hit = manager.getAssetManager().get(Assets.hit3.getPath());
        stealthTexture = new Sprite(manager.getAssetManager().get(Assets.stealth.getPath(),Texture.class));
        stealthTexture.setBounds(x,Constants.TOP, Dimensions.STEALTH.getWidth(),Dimensions.STEALTH.getHeight());
        this.setBounds(x,Constants.TOP,Dimensions.STEALTH.getWidth(),Dimensions.STEALTH.getHeight());
        TextureAtlas invisibleAtlas = manager.getAssetManager().get(Assets.stealthInvisible.getPath());
        TextureAtlas explosionAtlas = manager.getAssetManager().get(Assets.stealthDown.getPath());
        invisible = new Animation<TextureRegion>(0.08f, invisibleAtlas.getRegions());
        explosion = new Animation<TextureRegion>(0.15f, explosionAtlas.getRegions());
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
            if(random==10){StealthPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
            if(random2==20){StealthPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
            if(random3==30){StealthPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
            setTouchable(Touchable.disabled);
            settings.addScore(Points.STEALTH);
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
        elapsedTime += dt;
        lastMoved += 1;
        if(lastMoved == 100) {
          flag = false;
          lastMoved = 0;
        }
        //While the plane is still in the screen, move it
        if (stealthTexture.getY() > -this.getHeight()) {
          stealthTexture.setPosition(stealthTexture.getX(), stealthTexture.getY() - (Speed.STEALTH.getSpeed() + settings.getAccelleration()) * dt);
          setPosition(stealthTexture.getX(), stealthTexture.getY());
        }
        if (stealthTexture.getY() < -this.getHeight()) {
          //If the plane is out of bound, dispose it
          if(!touched){
            Player.damage(Amount.STEALTH.getAmount());
          }
          disposable = true;
          Gdx.app.log("StealthPlane"+hashCode(),"Touched = "+touched);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        if(!touched){
          if(!flag){
            stealthTexture.draw(batch,parentAlpha);
          }
          if(lastMoved >= 30&&lastMoved<=64){
            if(!flag){elapsedTime = 0;}
            flag = true;
            batch.draw(invisible.getKeyFrame(elapsedTime),getX(),getY(),getWidth(),getHeight());
            if(invisible.isAnimationFinished(elapsedTime)){
              stealthTexture.setPosition(new Random().nextInt(Bounds.STEALTH.getBound()), stealthTexture.getY());
              setPosition(stealthTexture.getX(), stealthTexture.getY());
            }
          }
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

package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Points;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;

import java.util.Random;

public class StealthPlane extends Actor{
  private Sprite stealthTexture, stealthDown;
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;
  private float lastMoved = 0;
  private Timer.Task timer;
  private Animation<TextureAtlas> invisible,explosion;

  public StealthPlane(int x) {
    settings = Settings.getIstance();
    manager = Manager.getIstance();
    stealthTexture = new Sprite(manager.getAssetManager().get(Assets.stealth.getPath(),Texture.class));
    stealthDown = new Sprite(manager.getAssetManager().get(Assets.stealthDown.getPath(),Texture.class));
    stealthTexture.setBounds(x,800,118,200);
    this.setBounds(x,800,118,200);
    timer = Timer.schedule(new Timer.Task() {
      @Override
      public void run() {
        stealthTexture.setPosition(new Random().nextInt(362), stealthTexture.getY()-2);
        setPosition(stealthTexture.getX(), stealthTexture.getY());
      }
    }, 0, 1.5f, 1);
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
        if(random==10){StealthPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
        if(random2==20){StealthPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
        if(random3==30){StealthPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
        //inserire animazione di distruzione e fade
        stealthDown.setPosition(stealthTexture.getX(),stealthTexture.getY());
        stealthTexture = stealthDown;
        setTouchable(Touchable.disabled);
        Settings.addScore(Points.STEALTH);
        disposable = true;
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
    //While the plane is still in the screen, move it
    if (stealthTexture.getY() > -this.getHeight()) {
      stealthTexture.setPosition(stealthTexture.getX(), stealthTexture.getY() - (Speed.PLANE.getSpeed() + settings.ACCELERATION) * dt);
      setPosition(stealthTexture.getX(), stealthTexture.getY());
      if (!touched) {
        if(TimeUtils.nanoTime()-lastMoved>1500000000){
            timer.run();
            lastMoved = TimeUtils.nanoTime();
        }
      }
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
    stealthTexture.draw(batch,parentAlpha);
  }

  @Override
  public boolean remove() {
    return disposable;
  }
}

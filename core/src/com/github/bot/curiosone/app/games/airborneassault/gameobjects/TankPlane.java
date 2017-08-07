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
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Points;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;

import java.util.Random;

public class TankPlane extends Actor {

  private Array<Sprite> textures;
  private Animation<TextureRegion> explosion;
  private boolean disposable = false,touched = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;
  private Sprite current;
  private int count;
  private float elapsedTime;

  public TankPlane(int x) {
    settings = Settings.getIstance();
    manager = Manager.getIstance();
    TextureAtlas textureAtlas = manager.getAssetManager().get(Assets.tankDown.getPath());
    explosion = new Animation<TextureRegion>(0.5f,textureAtlas.getRegions());
    textures = new Array<Sprite>();
    for(int i=0;i<5;i++){
      //TO DO --> inserire file
        String path= "airborneassaultassets/planetextures/tankplanetextures/tankplaneX.png";
        path = path.substring(0,63)+i+path.substring(64);
        textures.add(new Sprite(manager.getAssetManager().get(path,Texture.class)));
    }
    count = 0;
    current = textures.get(0); //CONTROLLARE
    current.setBounds(x,800,130,220);
    this.setBounds(x,800,130,220);
    addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(count<5){
          Sprite next = textures.get(count);
          next.setBounds(current.getX(),current.getY(),current.getWidth(),current.getHeight());
          current = next;
        } // CONTROLLARE
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        count++;
        if(count==5) {
          int random = new Random().nextInt(40);
          int random2 = new Random().nextInt(60);
          int random3 = new Random().nextInt(100);
          if(random==10){TankPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK1));}
          if(random2==20){TankPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK2));}
          if(random3==30){TankPlane.super.getStage().addActor(new HealthPack(Amount.HEALTHPACK3));}
          setTouchable(Touchable.disabled);
          settings.addScore(Points.TANK);
          touched = true;
        }
      }
    });
  }

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

  @Override
  public void act(float delta) {
    super.act(delta);
    //While the plane is still in the screen, move it
    if(touched){elapsedTime += delta;}
    if (current.getY() > -this.getHeight()) {
      current.setPosition(current.getX(), current.getY() - (Speed.TANK.getSpeed()+settings.getAccelleration())*delta);
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

  @Override
  public boolean remove() {
    return disposable;
  }
}

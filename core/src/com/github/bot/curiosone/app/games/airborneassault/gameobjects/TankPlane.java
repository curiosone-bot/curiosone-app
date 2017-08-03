package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Points;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;

import java.util.Random;

public class TankPlane extends Actor {

  private Array<Sprite> textures;
  private boolean disposable = false,destroyed = false;
  private Sound hit;
  private Settings settings;
  private Manager manager;
  private Sprite current;
  private int count;

  public TankPlane(int x) {
    settings = Settings.getIstance();
    manager = Manager.getIstance();
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
          Settings.addScore(Points.TANK);
          disposable = true;
          destroyed = true;
        }
      }
    });
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    current.draw(batch,parentAlpha);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    //While the plane is still in the screen, move it
    if (current.getY() > -this.getHeight()) {
      current.setPosition(current.getX(), current.getY() - (Speed.TANK.getSpeed()+settings.ACCELERATION)*delta);
      setPosition(current.getX(),current.getY());
    }
    if (current.getY() < -this.getHeight()) {
      //If the Plane has been touched, then dispose it
      disposable = true;
      if(!destroyed) {
        Player.damage(Amount.TANK.getAmount());
        Gdx.app.log("Player Damaged By", "Tank-" + this.hashCode());
      }
    }
  }

  @Override
  public boolean remove() {
    return disposable;
  }
}

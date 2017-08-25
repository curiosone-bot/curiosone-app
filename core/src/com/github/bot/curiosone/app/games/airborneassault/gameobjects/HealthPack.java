package com.github.bot.curiosone.app.games.airborneassault.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Assets;
import com.github.bot.curiosone.app.games.airborneassault.assets_manager.Manager;
import com.github.bot.curiosone.app.games.airborneassault.player.Player;
import com.github.bot.curiosone.app.games.airborneassault.settings.Amount;
import com.github.bot.curiosone.app.games.airborneassault.settings.Dimensions;
import com.github.bot.curiosone.app.games.airborneassault.settings.Settings;
import com.github.bot.curiosone.app.games.airborneassault.settings.Speed;

import java.util.Random;

/**
 * Heals the player once touched.
 */
public class HealthPack extends Actor{

  private int amount;
  private Sprite texture;
  private Settings settings;
  private boolean disposable = false;

  public HealthPack(Amount amount) {
    Manager manager = Manager.getIstance();
      settings = Settings.getIstance();
      this.amount = amount.getAmount();
      texture = new Sprite(manager.getAssetManager().get(Assets.healthPack.getPath(),Texture.class));
      int x = new Random().nextInt(420);
      int y = new Random().nextInt(540)+220;
      texture.setBounds(x,y, Dimensions.HEALTHPACK.getWidth(),Dimensions.HEALTHPACK.getHeight());
      this.setBounds(x,y,Dimensions.HEALTHPACK.getWidth(),Dimensions.HEALTHPACK.getHeight());
      addListener(new InputListener(){

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
          return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
          heal();
          disposable = true;
        }
      });

  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    texture.draw(batch);
  }

  @Override
  public void act(float delta) {
    super.act(delta);
    if(texture.getY()> -this.getHeight()){
      texture.setPosition(texture.getX(),texture.getY()-(Speed.HEALTHPACK.getSpeed()+settings.getAccelleration())*delta/2);
      setPosition(texture.getX(),texture.getY());
    }
    com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
      @Override
      public void run() {
        disposable = true;
      }
    },2f);
  }

  @Override
  public boolean remove() {
    return disposable;
  }

  private void heal(){
    Player.heal(amount);
  }
}

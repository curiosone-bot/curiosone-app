package com.github.bot.curiosone.app.games.endlessroad.entities;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;




/**
 * This class represents all the cars in the game
 * @author Paolo Pierantozzi
 */
public abstract class AbstractCar extends Image
{
    private Sprite sprite;
    private Rectangle rectangle;
    protected float speed = 15f;

    public AbstractCar(Stage stage,String name,float x,float y)
    {
        sprite = new Sprite(AssetsLoader.getInstance().getManager().get("EndlessRoad/Cars/" + name + ".png",Texture.class));
        setBounds(sprite.getX(),sprite.getY(),sprite.getWidth(),sprite.getHeight());
        rectangle = new Rectangle(sprite.getX()+10f,sprite.getY(),sprite.getWidth()-20f,sprite.getHeight());
        setPosition(x,y);
        stage.addActor(this);
    }


    /**
     * @return the car's sprite
     */
    public Sprite getSprite() {return sprite;}

    @Override
    protected void positionChanged()
    {
        super.positionChanged();
        sprite.setPosition(getX(),getY());
        rectangle.setPosition(sprite.getX()+sprite.getWidth()/4f,sprite.getY());
    }

    /**
     * @return the car's speed
     */
    public float getSpeed() {return speed;}

    /**
     * Sets the car's speed
     * @param speed The new car's speed
     */
    public void setSpeed(float speed) {this.speed = speed;}

    /**
     * @return the bounding rectangle of the car
     */
    public Rectangle getBounds() {return rectangle;}
    
    /**
     * Adds the car to the stage as an Actor
     * @param stage The stage in which to add the car
     */
    public void addToStage(Stage stage)
    {
    	stage.addActor(this);
    }
}


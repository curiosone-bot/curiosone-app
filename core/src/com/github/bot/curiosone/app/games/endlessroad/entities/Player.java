package com.github.bot.curiosone.app.games.endlessroad.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsLoader;
import com.github.bot.curiosone.app.games.endlessroad.utilities.AssetsPaths;
import com.github.bot.curiosone.app.games.endlessroad.utilities.GameInfos;



/**
 * This class represents the player's car
 * @author Paolo Pierantozzi
 */
public class Player extends AbstractCar
{
	private static Player instance;
    private MoveByAction accelerate;

    /**
     * @return The unique instance of the Player class
     */
    public static Player getInstance()
    {
    	if (instance == null || instance.getActions().size == 0) instance = new Player();
    	return instance;
    }
    
    
    public Player()
    {
        super(new Stage(),"player",0,0);
        setBounds(getX(),getY(),getWidth(),getHeight());
        setOrigin(getWidth()/2f,getHeight()/2f);

        accelerate = new MoveByAction();
        this.addAction(Actions.forever(accelerate));
        setSpeed(GameInfos.MIN_PLAYER_SPEED);
        
        switch (Gdx.app.getType())
        {
//        	case Android:
//        		addListener(new ActorGestureListener()
//                {
//                    @Override
//                    public void fling(InputEvent event,float velocityX,float velocityY, int button)
//                    {
//                    	if(Math.abs(velocityX)>Math.abs(velocityY))
//                    	{
//                    		if (velocityX<0) turn("left");
//                    		else if(velocityX>0) turn("right");
//                            
//                       
//                    	}
//                    }
//                }); break;
                
        	case Desktop:
        		addListener(new InputListener()
                {
        			Sound brakeSound = AssetsLoader.getInstance().getManager().get(AssetsPaths.BRAKE.getPath(),Sound.class);
                    @Override
                    public boolean keyDown(InputEvent event,int keycode)
                    {
                    	
                        switch (keycode)
                        {
                            case Input.Keys.LEFT: turn("left"); break;
                            case Input.Keys.RIGHT: turn("right"); break;
                            case Input.Keys.DOWN: 
                            	if ((int)getSpeed() > GameInfos.MIN_PLAYER_SPEED) 
                            		brakeSound.play(0.1f,1.3f,0);
                            break;
                        }
                        return true;
                    }
                    
                    @Override
                    public boolean keyUp(InputEvent event, int keycode)
                    {
                    	switch(keycode)
                    	{
                    		case Input.Keys.DOWN: brakeSound.stop(); break;
                    	}
                    	return true;
                    }
                });
        }
        
        
        

			
    }

    @Override
    public void setSpeed(float speed)
    {
    	if (!(speed < GameInfos.MIN_PLAYER_SPEED || speed > GameInfos.MAX_PLAYER_SPEED))
    	{
    		super.setSpeed(speed);
            accelerate.setAmount(0,speed);
    	}
        

    }
    
    /**
     * Makes the player's car turn
     * @param direction towards which the car'll turn
     */
    public void turn(String direction)
    {
    	RotateToAction getBackToZero = new RotateToAction();
        getBackToZero.setRotation(0);
        getBackToZero.setDuration(0.07f);
        
    	if (direction.equals("left") && Player.this.getX() > GameInfos.Lanes.LEFT.getX())
    	{
    		RotateToAction rotateToLeft = new RotateToAction();
            rotateToLeft.setRotation(10f);
            rotateToLeft.setDuration(0.02f);

            MoveByAction moveLeft = new MoveByAction();
            moveLeft.setAmount(-100f, 0);
            moveLeft.setDuration(0.07f);

            ParallelAction pa = new ParallelAction(rotateToLeft,moveLeft);
            SequenceAction sequence = new SequenceAction(pa,getBackToZero);
            Player.this.addAction(sequence);
    			
    	}
    	
    	else if (direction.equals("right") && Player.this.getX() < GameInfos.Lanes.CENTRE_RIGHT.getX()+50f)
    	{
    		RotateToAction rotateToRight = new RotateToAction();
            rotateToRight.setRotation(-10f);
            rotateToRight.setDuration(0.02f);

            MoveByAction moveRight = new MoveByAction();
            moveRight.setAmount(100f,0);
            moveRight.setDuration(0.07f);

            ParallelAction pa = new ParallelAction(rotateToRight,moveRight);
            SequenceAction sequence = new SequenceAction(pa,getBackToZero);
            Player.this.addAction(sequence);      
    	}
    	
    	
    	
    }


}


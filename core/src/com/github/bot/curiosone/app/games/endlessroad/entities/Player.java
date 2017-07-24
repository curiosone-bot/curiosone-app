package com.github.bot.curiosone.app.games.endlessroad.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

import com.github.bot.curiosone.app.games.endlessroad.utilities.GameConstants;

/**
 * This class represents the player's car
 * @author Paolo Pierantozzi
 */
public class Player extends AbstractCar
{

    private MoveByAction accelerate;

    public Player(AssetManager manager,Stage stage,float x,float y)
    {
        super(manager,stage,"player",x,y);
        setBounds(getX(),getY(),getWidth(),getHeight());
        setOrigin(getWidth()/2f,getHeight()/2f);

        accelerate = new MoveByAction();
        this.addAction(Actions.forever(accelerate));
        setSpeed(GameConstants.MIN_PLAYER_SPEED);

        addListener(new InputListener()
        {
            @Override
            public boolean keyDown(InputEvent event,int keycode)
            {
                RotateToAction getBackToZero = new RotateToAction();
                getBackToZero.setRotation(0);
                getBackToZero.setDuration(0.07f);

                switch (keycode)
                {
                    case Input.Keys.LEFT:
                    {

                        if (Player.this.getX() > GameConstants.Lanes.LEFT.getX())
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
                    }; break;

                    case Input.Keys.RIGHT:
                    {
                        if (Player.this.getX() < GameConstants.Lanes.CENTRE_RIGHT.getX()+50f)
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
                return true;
            }
        });


    }

    @Override
    public void setSpeed(float speed)
    {
        super.setSpeed(speed);
        accelerate.setAmount(0,speed);

    }


}


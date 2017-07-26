package com.github.bot.curiosone.app.games.endlessroad.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.github.bot.curiosone.app.games.endlessroad.entities.Car;
import com.github.bot.curiosone.app.games.endlessroad.entities.Player;

/**
 * Handles the spawning of the cars in the game
 * @author Paolo Pierantozzi
 */
public class CarsSpawner
{
    private AssetManager manager;

    private Stage stage;

    private ArrayList<Car> cars;

    private float cameraY;


    public CarsSpawner(AssetManager manager, Stage stage)
    {
        this.manager = manager;
        this.stage = stage;
        cars = new ArrayList<Car>();
        createCars(GameConstants.MIN_PLAYER_SPEED);
        positionCars();
    }

    /**
     * Creates the cars but still doesn't position them
     */
    private void createCars(float speed)
    {
        Random random = new Random();
        int index = 0;

        for (int i = 0; i < 7; i++)
        {
            index = random.nextInt(8 - 1) + 1;
            Car car = new Car(manager, stage, "car" + index, 0, 0);
            float carSpeed = speed > 40 ? speed - 30f : speed - 5f;
            car.setSpeed(carSpeed);
            cars.add(car);
        }

        Collections.shuffle(cars);
    }


    /**
     * Positions all the cars created before
     */
    private void positionCars(Player player, boolean firstTimePositioning)
    {
        float tempY = 0;
        float tempX = 0;
        float distance = 0f;

        if (firstTimePositioning) tempY = GameConstants.HEIGHT * 2;
        else
        {
            if (player.getSpeed() < 35)
                tempY = player.getY() + GameConstants.HEIGHT * 2f;

            else if (player.getSpeed() >= 35 && player.getSpeed() <= 50)
                tempY = player.getY() + GameConstants.HEIGHT * 2.5f;

            else if (player.getSpeed() >= 51 && player.getSpeed() <= GameConstants.MAX_PLAYER_SPEED)
                tempY = player.getY() + GameConstants.HEIGHT * 1.4f;

        }

        for (Car c : cars)
        {
            if (c.getX() == 0 && c.getY() == 0) //The car has been created but still not positioned
            {
                tempX = randomLane();
                c.setPosition(tempX, tempY);
                Random random = new Random();
                distance = random.nextInt(250) + 500;
                tempY += distance;

            }
        }

    }
    
    private void positionCars() {positionCars(null,true);}
    

    /**
     * Makes all the cars visible on the screen
     */
    public void drawCars(Batch batch)
    {
        for (Car c : cars)
        {
            batch.draw(c.getSprite().getTexture(), c.getX(), c.getY());
        }
    }


    /**
     * Rearranges the cars whenever they get out of the screen bounds
     */
    public void rearrangeCars(Player player)
    {

        for (int i = 0; i < cars.size(); i++)
        {
            if (cars.get(i).getY() + GameConstants.HEIGHT * 2f + cars.get(0).getHeight() < cameraY) //The car went out of the screen bounds
                cars.remove(i);

            if (cars.size() == 2)
            {
                createCars(player.getSpeed());
                positionCars(player, false);
            }
        }
    }

    /**
     * Setter method for cameraY
     *
     * @param cameraY the main camera Y
     */
    public void setCameraY(float cameraY)
    {
        this.cameraY = cameraY;
    }

    /**
     * Returns all the cars which are present in the game in an ArrayList
     */
    public ArrayList<Car> getCars()
    {
        return cars;
    }


    /**
     * Returns a valid random value for the x-axis positionment of the cars on the road
     */
    private float randomLane()
    {
        int size = GameConstants.Lanes.values().length;

        float tempX = GameConstants.Lanes.values()[(int) (Math.random() * size)].getX();

        return tempX;
    }

}
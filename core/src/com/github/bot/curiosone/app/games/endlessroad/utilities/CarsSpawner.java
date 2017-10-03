package com.github.bot.curiosone.app.games.endlessroad.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.bot.curiosone.app.games.endlessroad.entities.Car;
import com.github.bot.curiosone.app.games.endlessroad.entities.Player;



/**
 * Handles the spawning of the cars in the game
 * @author Paolo Pierantozzi
 *
 */
public class CarsSpawner
{
	private Stage stage;
	
	private ArrayList<Car> cars;
	
	private float cameraY;
	


	public CarsSpawner(Stage stage)
	{
		this.stage=stage;
		cars = new ArrayList<Car>();
		createCars(GameInfos.MIN_PLAYER_SPEED);
		positionCars(true);
	}
	
	/**
	 * Creates the cars but still doesn't position them
	 * @param speed The speed on which to adjust the spawned car's speed
	 */
	private void createCars(float speed)
	{
		Random random = new Random();
		int index = 0;
		
		for (int i=0;i<7;i++)
		{
			index = random.nextInt(8-1) + 1;
			Car car = new Car(stage,"car" + index ,0,0);
			float carSpeed = 0;
			if (speed < 29) carSpeed = speed-3f;
			else if (speed >= 29 && speed < 60) carSpeed = speed -20f;
			else if (speed >= 60) carSpeed = speed -30f;
			car.setSpeed(carSpeed);
			cars.add(car);
		}	
		
		Collections.shuffle(cars);
	}
	
	
	/**
	 * Positions the cars created before
	 * @param firstTimePositioning Has to be true only if the game has just started
	 */
	private void positionCars(boolean firstTimePositioning)
	{
		float tempY = 0;
		float tempX = 0;
		float distance = 0f;
		
		if (firstTimePositioning) tempY = GameInfos.HEIGHT*2;
		else 
		{
			if (Player.getInstance().getSpeed() < 35)	
				tempY = Player.getInstance().getY() + GameInfos.HEIGHT*2f;
			
			else if (Player.getInstance().getSpeed() >= 35 && Player.getInstance().getSpeed() <= 50)
				tempY = Player.getInstance().getY() + GameInfos.HEIGHT*2f;
			
			else if (Player.getInstance().getSpeed() >= 51 && Player.getInstance().getSpeed() <= GameInfos.MAX_PLAYER_SPEED)
				tempY = Player.getInstance().getY() + GameInfos.HEIGHT*1.4f;
			 
		}
		
		for(Car c: cars)
		{
			if (c.getX() == 0 && c.getY() == 0) //The car has been created but still not positioned
			{
				tempX = randomLane();
				c.setPosition(tempX,tempY);
				Random random = new Random();
				distance = random.nextInt(250)+500;
				tempY += distance;
				
			}
		}
			
	}
	
	/**
	 * Makes all the cars visible on the screen
	 * @param batch The batch on which to draw the cars
	 */
	public void drawCars(Batch batch)
	{
		for (Car c: cars)
		{
			batch.draw(c.getSprite().getTexture(),c.getX(),c.getY());
		}
	}
	
	
	/**
	 * Rearranges the cars whenever they get out of the screen bounds
	 */
	public void rearrangeCars()
	{
		
		for (int i = 0; i<cars.size();i++)
		{
			float trigger = cars.get(i).getY() + GameInfos.HEIGHT*2f + cars.get(0).getHeight();
			if (trigger< cameraY) //The car went out of the screen bounds
				cars.remove(i);

			if (cars.size() == 2)
			{
				createCars(Player.getInstance().getSpeed());
				positionCars(false);
			}
		}
	}
	
	/**
	 * Setter method for cameraY
	 * @param cameraY the main camera Y
	 */
	public void setCameraY(float cameraY) {this.cameraY = cameraY;}
	
	/**
	 * @return an ArrayList containing all the cars present on the road
	 */
	public ArrayList<Car> getCars() {return cars;}
	
	
	
	/**
	 * @return a valid random value for the x-axis positionment of the cars on the road
	 */
	private float randomLane()
	{
		int size = GameInfos.Lanes.values().length;
		
		float tempX = GameInfos.Lanes.values()[(int)(Math.random()*size)].getX();
		
		return tempX;
	}

	
	
}

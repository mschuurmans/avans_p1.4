package nl.avans.essperience.models;

import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import nl.avans.essperience.entities.simon.FruitPiece;
import nl.avans.essperience.main.Main;

public class FopsModel extends GameModel
{
	private World _myWorld;
	
	private int _amountOfFruit;
	private int _difficulty;
	private int _gravity;
	private int _timeRemaining;
	private int _amountOfBullets;
	private ArrayList<FruitPiece> _fruits = new ArrayList<FruitPiece>();
	
	public FopsModel()
	{
		init();
	}
	
	public void init()
	{
		_difficulty = Main.GAME.getDifficulty();
		_maxTime = 10000 + (2000/(int)Math.sqrt(Main.GAME.getDifficulty())); // TODO check fruit falltime
		_timeRemaining = _maxTime;
		_amountOfFruit = (_difficulty / 4) + 1;
		_gravity = 30 + ((int)Math.sqrt(Main.GAME.getDifficulty()) * 10);
		for (int i = 0; i < _amountOfFruit; i++)
		{
			FruitPiece fruit = new FruitPiece();
		//	fruit.getBodyFops();
			_fruits.add(fruit);
		}
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 3, new QuadSpaceStrategy(20,5));
		
		_myWorld.clear();
		_myWorld.setGravity(0, _gravity);
	}	
	
	public void update()
	{
		_myWorld.step();
	}
	
	public ArrayList<FruitPiece> getFruits()
	{
		return _fruits;
	}
	
	public int getBullets() 
	{
		return _amountOfBullets;
	}
	
	public void bulletFired() 
	{
		_amountOfBullets--;
	}
	
	public void fruitHit(int index)
	{
		// TODO do something when hit
		_fruits.remove(index);
	}
}

package nl.avans.essperience.models;

import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
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
	private double _cursorX;
	private double _cursorY;
	private ArrayList<FruitPiece> _fruits = new ArrayList<FruitPiece>();
	private ArrayList<Body> _bodies = new ArrayList<Body>();
	
	public FopsModel()
	{
		init();
	}
	
	public void init()
	{
		_difficulty = Main.GAME.getDifficulty();
		_maxTime = 10000 + (2000/(int)Math.sqrt(Main.GAME.getDifficulty())); // TODO check fruit falltime
		_timeRemaining = _maxTime;
		_amountOfFruit = (_difficulty / 4) + 2;
		_amountOfBullets = (int) (_amountOfFruit * 1.5f);
		_gravity = 100 + ((int)Math.sqrt(Main.GAME.getDifficulty()) * 10);

		System.out.println("Diff is: " + _difficulty);
		System.out.println("amount of fruit is: " + _amountOfFruit);
		System.out.println("amount of bullets is: " + _amountOfBullets);
		for (int i = 0; i < _amountOfFruit; i++)
		{
			FruitPiece fruit = new FruitPiece();
		//	fruit.getBodyFops();
			_fruits.add(fruit);
		}
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 3, new QuadSpaceStrategy(20,5));

		_myWorld.clear();
		for (FruitPiece f : _fruits)
		{
			System.out.println("adding body to world");
			Body body = f.getBodyFops();
			_myWorld.add(body);
			_bodies.add(body);
		}
		_myWorld.setGravity(0, _gravity);
	}	
	
	public void update()
	{
		for (int i = 0; i < _bodies.size(); i ++)
		{
			_myWorld.step();
		}
		for (Body f : _bodies)
		{
			//_myWorld.add(f);
			//System.out.println("adding to world");
		}

		if (getTimeRemaining() == 0)
		{
			if(_modelToControllerListener != null)
				_modelToControllerListener.timesUpEvent();
		}
	}
	
	public ArrayList<FruitPiece> getFruits()
	{
		return _fruits;
	}
	
	public ArrayList<Body> getBodies()
	{
		return _bodies;
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
	
	public Vector2f getCursorPosition()
	{
		return new Vector2f((int)_cursorX, (int)_cursorY);
	}
	
	public void updateCursorPosition(double x, double y)
	{
		_cursorX = x;
		_cursorY = y;
	}
}

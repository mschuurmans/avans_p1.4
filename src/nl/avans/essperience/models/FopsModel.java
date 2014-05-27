package nl.avans.essperience.models;

import java.util.ArrayList;
import java.util.List;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import nl.avans.essperience.entities.fops.FruitOpsPiece;
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
	private ArrayList<FruitOpsPiece> _fruits = new ArrayList<FruitOpsPiece>();
	private ArrayList<Body> _bodies = new ArrayList<Body>();
	
	//debug data
	private boolean _debug = true;
	private List<String> _debugData = new ArrayList<String>();
	
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

		//debug data
		_debugData.add("Diff is: " + _difficulty);
		_debugData.add("amount of fruit is: " + _amountOfFruit);
		_debugData.add("amount of bullets is: " + _amountOfBullets);
		
		for (int i = 0; i < _amountOfFruit; i++)
		{
			FruitOpsPiece fruit = new FruitOpsPiece();
		//	fruit.getBodyFops();
			_fruits.add(fruit);
		}
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 3, new QuadSpaceStrategy(20,5));

		_myWorld.clear();
		for (FruitOpsPiece f : _fruits)
		{
			System.out.println("adding body to world");
			Body body = f.getBody();
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
	
	public ArrayList<FruitOpsPiece> getFruits()
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
	
	public boolean isDebugTrue()
	{
		return _debug;
	}
	
	public List<String> getDebugData()
	{
		return _debugData;
	}
	
	/**
	 * Method receives a body and returns 5 fractions, to be called when a piece of fruit gets shot.
	 * @param Body object from a fruitpiece
	 * @return an array that contains 5 bodies. These bodies resemble the fractions of the fruitpiece that has been passed in as a parameter
	 * @author jack
	 */
	public Body[] splitBody(Body body)
	{
		Body[] newBody = new Body[5];
		
		int x = (int) body.getPosition().getX();
		int y = (int) body.getPosition().getY();
		
		double angleOffset = Math.toRadians(72);
		
		int bodyWidth = (int) (body.getShape().getBounds().getWidth());
		int fractionWidth = (int) (bodyWidth * 0.35);
		int fractionMass = (int) (body.getMass() * 0.35);
		
		for (int i = 0; i < 5; i++)
		{
			newBody[i] = new Body(new Circle( fractionWidth ), fractionMass);
			
			int newX = (int)( ((Math.cos(angleOffset * i) / 2) * bodyWidth) + x);
			int newY = (int)( ((Math.sin(angleOffset * i) / 2) * bodyWidth) + y);
			newBody[i].setPosition(newX, newY);
		}
		
		return newBody;
	}
}

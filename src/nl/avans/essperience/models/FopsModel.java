package nl.avans.essperience.models;

import java.util.ArrayList;
import java.util.List;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import nl.avans.essperience.entities.fops.FruitOpsPiece;
import nl.avans.essperience.entities.fops.Splash;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.utils.AssetManager;

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
	private ArrayList<Body> _splitBodies = new ArrayList<Body>();
	private ArrayList<Splash> _splashes = new ArrayList<Splash>();
	private int _updates = 0;
	private int _currentBody = 0;

	private final int _XOFFSETALLOWED = 75;
	private final int _YOFFSETALLOWED = 75;

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
		_maxTime = 6000 + (2000/(int)Math.sqrt(Main.GAME.getDifficulty())); // TODO check fruit falltime
		_timeRemaining = _maxTime;
		_amountOfFruit = (_difficulty / 4) + 2;
		_amountOfBullets = (int) (_amountOfFruit) + (_maxTime/2000);
		_gravity = 170 + ((int)Math.sqrt(Main.GAME.getDifficulty()) * 10);

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
			_bodies.add(body);
		}
		_myWorld.setGravity(0, _gravity);
	}	

	public void update()
	{
		for (int i = 0; i < 5; i ++)
		{
			_updates++;
			_myWorld.step();
		}

		if (_updates > 30 && _currentBody < _bodies.size())
		{
			_updates = 0;
			_myWorld.add(_bodies.get(_currentBody));
			_currentBody++;
		}

		if (getTimeRemaining() == 0)
		{
			if(_modelToControllerListener != null)
				_modelToControllerListener.timesUpEvent();
		}
		if (_bodies.size() <= 0)
		{
			if(_modelToControllerListener != null)
				_modelToControllerListener.gameFinished(true);
		}
	}

	public void checkHits()
	{
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < _bodies.size(); i++)
		{
			float fruitX = _bodies.get(i).getPosition().getX();
			float fruitY = _bodies.get(i).getPosition().getY();
			if (_debug) 
			{
				System.out.println("fruit posX: " + fruitX + " fruit posY: " + fruitY);
				System.out.println("shot at posX: " + _cursorX + " posY: " + _cursorY);
			}
			if (	_cursorX > fruitX - _XOFFSETALLOWED &&
					_cursorX < fruitX + _YOFFSETALLOWED &&
					_cursorY > fruitY - _XOFFSETALLOWED &&
					_cursorY < fruitY + _YOFFSETALLOWED)
			{
				//System.out.println("adding splash at x: " + _cursorX + " y: " + _cursorY);
				_splashes.add(new Splash((int)_cursorX, (int)_cursorY, (String)_bodies.get(i).getUserData()));
				fruitHit(i);
				indexes.add(i);
			}
		}
		removeBodies(indexes);
	}

	public ArrayList<FruitOpsPiece> getFruits()
	{
		return _fruits;
	}

	public ArrayList<Body> getBodies()
	{
		ArrayList<Body> bodies = new ArrayList<Body>();
		bodies.addAll(_bodies);
		bodies.addAll(_splitBodies);
		return bodies;
	}

	private void removeBodies(ArrayList<Integer> indexes)
	{
		for (int i = indexes.size()-1; i >= 0; i--)
		{
			System.out.println("bodies.size(): " + _bodies.size());
			System.out.println("removing body with index: " + indexes.get(i) + " which is i: " + i);
			_bodies.remove(_bodies.get(indexes.get(i)));
			AssetManager.Instance().playSound("Fops/splash.wav");
			//_bodies.remove(indexes.get(i));
		}
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
		Body[] bodies = splitBody(_bodies.get(index));
		for (Body b : bodies)
		{
			_splitBodies.add(b);
			_myWorld.add(b);
		}
		//_fruits.remove(index);
	}

	public ArrayList<Splash> getSplashes()
	{
		return _splashes;
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
		String uData = (String)body.getUserData();
		switch(uData)
		{
		case "banana": uData = "bananapiece";
		break;
		case "orange": uData = "orangepiece";
		break;
		case "pear": uData = "pearpiece";
		break;
		case "apple": uData = "applepiece";
		break;
		default: uData = "bananapiece";
		break;
		}

		int x = (int) body.getPosition().getX();
		int y = (int) body.getPosition().getY();

		double angleOffset = Math.toRadians(72);

		int bodyWidth = (int) (body.getShape().getBounds().getWidth());
		int fractionWidth = (int) (bodyWidth * 0.35);
		int fractionMass = (int) (body.getMass() * 0.35);
		int _multiplier = 300;

		for (int i = 0; i < 5; i++)
		{
			newBody[i] = new Body(new Circle( fractionWidth ), fractionMass);

			int newX = (int)( ((Math.cos(angleOffset * i) / 2) * bodyWidth) + x);
			int newY = (int)( ((Math.sin(angleOffset * i) / 2) * bodyWidth) + y);
			newBody[i].setPosition(newX, newY);
			newBody[i].setUserData((String)uData + (i+1));
			newBody[i].adjustVelocity(new Vector2f((float) (Math.cos(angleOffset*i)*_multiplier),(float) (Math.sin(angleOffset*i))*_multiplier));
		}

		return newBody;
	}
}

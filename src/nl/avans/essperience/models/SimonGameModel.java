package nl.avans.essperience.models;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import nl.avans.essperience.entities.simon.FruitPiece;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.utils.AssetManager;

public class SimonGameModel extends GameModel
{
	public static final int BANANA = 0;
	public static final int ORANGE = 1;
	public static final int APPLE = 2;
	public static final int PEAR = 3;

	private boolean _debug = true;
	private boolean _easyMode = true;
	private char[] _charArray = {'u' , 'i', 'o', 'p'};
	
	private int _patternLength;
	private World _myWorld;
	private Body _floor;
	private List<Body> _fruitPieces = new ArrayList<Body>();
	private Iterator<Body> _fruitPiecesIterator;
	private List<Body> _bodyList = new ArrayList<Body>();
	private Image[] _fruitImages = new Image[4];
	
	private int _stepsPerUpdate;
	private int _updateCounter;
	private int _totalUpdatesNeeded;
	private int _updateCountOnLastPressed;
	
	private int _buttonsPressedCorrect;
	private boolean _guessedRight;
	
	private double _updateProgress;
	private double _actualProgress;
	
	public SimonGameModel()
	{		
		_fruitImages[BANANA] = AssetManager.Instance().getImage("Simon/banana.png");
		_fruitImages[ORANGE] = AssetManager.Instance().getImage("Simon/orange.png");
		_fruitImages[APPLE] = AssetManager.Instance().getImage("Simon/apple.png");
		_fruitImages[PEAR] = AssetManager.Instance().getImage("Simon/pear.png");
		
		init();
	}

	private void init()
	{
		int _difficulty = Main.GAME.getDifficulty();
		_difficulty *= 2;
		_patternLength = (_difficulty /4) +3; //max 426
		int stepsPerPiece = 100/ ((_difficulty/4) +5);
		_totalUpdatesNeeded = _patternLength * stepsPerPiece;
		_stepsPerUpdate = (_difficulty /2) +4;
		
		_guessedRight = false;
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
		
		_myWorld.clear();
		_myWorld.setGravity(0, 30);
		
		_floor = new StaticBody("Floor", new Box(Main.GAME.getWidth(), 1f));
		_floor.setPosition(Main.GAME.getWidth()/2, Main.GAME.getHeight() - 100);
		_floor.setRestitution(0.4f);
		_myWorld.add(_floor);
		
		
		for(int i = 0; i < _patternLength; i++)					//add pieces of fruit
		{
			FruitPiece fp = new FruitPiece();					//create new pieces of fruit
			while( fp.intersects(_fruitPieces))					//while the new fruitpiece intersects with one of the old ones
			{
				System.out.println("Intersecting fruitpiece discarded!");
				fp = new FruitPiece();							// if true. replace it with a new one until it gets false
			}
			_fruitPieces.add(fp.getBody());						//get the Bodies and store them
		}
		
		_fruitPiecesIterator = _fruitPieces.iterator();
		
		if(_debug)
			System.out.println("INIT SIMON GAME COMPLETE");
	}
	
	@Override
	public void update()
	{
		_updateCounter++;

		if(_debug)
			System.out.println("position: " + _fruitPieces.get(0).getPosition().toString());
		
		//update and step the world
		super.update();
		for(int i = 0; i < _stepsPerUpdate; i ++)
			_myWorld.step();
		
		// track progress
		_updateProgress = (double)_updateCounter/(double)_totalUpdatesNeeded;
		_actualProgress = (double)_bodyList.size() / (double)_fruitPieces.size();
		
		//add bodies when the actualProgress is behind on the updateProgress
		if(_updateProgress >= _actualProgress)
		{
			Body body = null;
			
			if(_fruitPiecesIterator.hasNext())
			{
				body = _fruitPiecesIterator.next();
				_bodyList.add(body);
				_myWorld.add(body);
			}
		}
		
	}
	
	public void draw(Graphics g1)
	{
		Graphics2D g = (Graphics2D) g1;;
		
		for(Body body : _bodyList)
		{			
			float rotation = body.getRotation();
			int x = (int)body.getPosition().getX();
			int y = (int)body.getPosition().getY();
			
			g.translate(x, y);
			g.rotate(rotation);
			
			int size = 90;
			
			g.drawImage(getFruitImage(body), 0 -size/2, 0 -size/2, size, size, null);	
			if(_easyMode)
				g.drawString("K: " + _charArray[matchNametoNumber((String)body.getUserData())], 0, +30);
			
			g.rotate(-rotation);
			g.translate(-x, -y);
		}
		

		if(_debug)
		{
			//display updateCounter
			int drawStringX = Main.GAME.getWidth() - 150;
			g.drawString("Update no.: " + _updateCounter, drawStringX, 100);
			
			//display _bodyList size
			g.drawString("_updateProgress: " + _updateProgress, drawStringX, 120);
			g.drawString("_actualProgress: " + _actualProgress, drawStringX, 140);
			g.drawString("_bodyList.Size: " + _bodyList.size(), drawStringX, 160);
			
			//debug display Floor
			int x = (int) _floor.getPosition().getX() - Main.GAME.getWidth()/2;
			int y = (int) _floor.getPosition().getY();
			int w = (int)_floor.getShape().getBounds().getWidth();
			int h = (int)_floor.getShape().getBounds().getHeight();
			g.drawRect(x, y, w, h);
			
			//debug display bodyBoundingBoxes and corresponding keys
			for(Body body : _fruitPieces)
			{
				int bw = (int) body.getShape().getBounds().getWidth();
				int bh = (int) body.getShape().getBounds().getHeight();
				int bx = (int) body.getPosition().getX() - bw/2;
				int by = (int) body.getPosition().getY() - bh/2;
				g.drawRect(bx, by, bw, bh);

			}
		}
	}
	
	public boolean getGuessedRight()
	{
		return _guessedRight;
	}
	
	public void resetGuessedRight()
	{
		_guessedRight = false;
	}
	
	private Image getFruitImage(Body body)
	{
		String name = (String)body.getUserData();
//		System.out.println(name);
		
		switch(name)
		{
		default: // case "banana"
			return _fruitImages[BANANA];
		case "orange":
			return _fruitImages[ORANGE];
		case "apple":
			return _fruitImages[APPLE];
		case "pear":
			return _fruitImages[PEAR];
		}
	}
	
	private int matchNametoNumber(String name)
	{
		switch(name)
		{
		default: // case "banana"
			return BANANA;
		case "orange":
			return ORANGE;
		case "apple":
			return APPLE;
		case "pear":
			return PEAR;
		}
	}
	
	public void setCurrentFruit(int pos)
	{
		// fixxed the issue of 2 keyevents on one keyrelease
		if(_updateCountOnLastPressed + 2 > _updateCounter)
			return;
		
		System.out.println("keyNumber pressed: " + pos);
		
		if(_buttonsPressedCorrect < _bodyList.size() )
		{
			int i = _buttonsPressedCorrect;
			System.out.println("KeyPressed: " + pos + " KeyExpected: " + matchNametoNumber( (String)_bodyList.get(i).getUserData() ));
			if(pos == matchNametoNumber( (String)_bodyList.get(i).getUserData() ))
			{
				_guessedRight = true;
				_buttonsPressedCorrect++;
				if(_modelToControllerListener != null && i == _bodyList.size()-1)
				{
					System.out.println("GameFinishedTrue Called!!!!!!!!!!!");
					_modelToControllerListener.gameFinished(true);
				}
			}
			else
			{
				if(_modelToControllerListener != null)
				{
					//System.out.println("GameFinishedFalse Called!!!!!!!!!!!");
					_modelToControllerListener.gameFinished(false);
				}
			}
		}
		_updateCountOnLastPressed = _updateCounter;
	}
}



package nl.avans.essperience.models;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Line;
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
	
	private int _patternLenght;
	private World _myWorld;
	private Body _floor;
	private ArrayList<Body> _fruitPieces = new ArrayList<Body>();
	
	private Image[] _fruitImages = new Image[4];
	
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
		_patternLenght = (_difficulty /2) +1;
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
		
		_myWorld.clear();
		_myWorld.setGravity(0, 30);
		
		int x1 = 0;
		int x2 = Main.GAME.getWidth();
		int y1 = Main.GAME.getHeight() -20;
		int y2 = y1;
		
		_floor = new StaticBody("Floor", new Line(x1, y1, x2, y2));
		
		_myWorld.add(_floor);
		
		for(int i = 0; i < _patternLenght; i++)					//add pieces of fruit
		{
			FruitPiece fp = new FruitPiece();					//create new pieces of fruit
			_fruitPieces.add(fp.getBody());						//get the Bodies and store them
		}
		
		for(Body body : _fruitPieces)
		{
			body.setPosition(Main.GAME.getWidth()/2, 0);		//position these bodies at the top center
			_myWorld.add(body);									// add fruitpieces to the world
		}
	}
	
	public void update()
	{
		_myWorld.step();
	}
	
	public void draw(Graphics g)
	{
		for(Body body : _fruitPieces)
		{
			g.drawImage(getFruitImage(body.toString()), (int)body.getPosition().getX(), (int)body.getPosition().getY(), null);
		}
	}
	
	private Image getFruitImage(String name)
	{
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
	
}



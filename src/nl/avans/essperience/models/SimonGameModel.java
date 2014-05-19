package nl.avans.essperience.models;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
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
	
	private int _patternLenght;
	private World _myWorld;
	private Body _floor;
	private List<Body> _fruitPieces = new ArrayList<Body>();
	private boolean _debug = true;
	private Image[] _fruitImages = new Image[4];
	
	private int _stepsPerUpdate;
	
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
		_patternLenght = (_difficulty /4) +3;
		_patternLenght = 40;
		_stepsPerUpdate = 5;
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
		
		_myWorld.clear();
		_myWorld.setGravity(0, 30);
		
		_floor = new StaticBody("Floor", new Box(Main.GAME.getWidth(), 1f));
		_floor.setPosition(Main.GAME.getWidth()/2, Main.GAME.getHeight() - 200);
		_myWorld.add(_floor);
		
		for(int i = 0; i < _patternLenght; i++)					//add pieces of fruit
		{
			FruitPiece fp = new FruitPiece();					//create new pieces of fruit
			_fruitPieces.add(fp.getBody());						//get the Bodies and store them
		}
		
		for(Body body : _fruitPieces)
		{
			_myWorld.add(body);									// add fruitpieces to the world
		}
		
		if(_debug)
			System.out.println("INIT SIMON GAME COMPLETE");
	}
	
	@Override
	public void update()
	{

		System.out.println("position: " + _fruitPieces.get(0).getPosition().toString());
		super.update();
		
		for(int i = 0; i < _stepsPerUpdate; i ++)
			_myWorld.step();
		
	}
	
	public void draw(Graphics g)
	{
		for(Body body : _fruitPieces)
		{			
			g.drawImage(getFruitImage(body), (int)body.getPosition().getX() -32, (int)body.getPosition().getY(), 64, 64, null);
		}
		

		if(_debug)
		{
			//debug display Floor
			int x = (int) _floor.getPosition().getX() - Main.GAME.getWidth()/2;
			int y = (int) _floor.getPosition().getY();
			int w = (int)_floor.getShape().getBounds().getWidth();
			int h = (int)_floor.getShape().getBounds().getHeight();
			g.drawRect(x, y, w, h);
			
			//debug display bodyBoundingBoxes
			for(Body body : _fruitPieces)
			{
				int bw = (int) body.getShape().getBounds().getWidth();
				int bh = (int) body.getShape().getBounds().getHeight();
				int bx = (int) body.getPosition().getX() - bw/2;
				int by = (int) body.getPosition().getY();
				g.drawRect(bx, by, bw, bh);
			}
		}
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
	
}



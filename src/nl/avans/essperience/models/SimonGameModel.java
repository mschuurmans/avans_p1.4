package nl.avans.essperience.models;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.StaticBody;
import net.phys2d.raw.World;
import net.phys2d.raw.shapes.Line;
import net.phys2d.raw.strategies.QuadSpaceStrategy;
import nl.avans.essperience.main.Main;

public class SimonGameModel extends GameModel
{
	private int _patternLenght;
	private World _myWorld;
	private Body _floor;
	private Body[] _fruitPieces;
	
	public SimonGameModel()
	{
		
		init();
		
	}

	private void init()
	{
		int _difficulty = Main.GAME.getDifficulty();
		_patternLenght = _difficulty /2;
		
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 10, new QuadSpaceStrategy(20,5));
		
		_myWorld.clear();
		_myWorld.setGravity(0, 30);
		
		int x1 = 0;
		int x2 = Main.GAME.getWidth();
		int y1 = Main.GAME.getHeight() -20;
		int y2 = y1;
		
		_floor = new StaticBody("Floor", new Line(x1, y1, x2, y2));
		
		_myWorld.add(_floor);
		
		for(int i = 0; i < _patternLenght; i++)
		{
//			_fruitPieces[i] = new fruitpiece
		}
	}
	
	public void update()
	{
		_myWorld.step();
	}
	
	
	
	
}



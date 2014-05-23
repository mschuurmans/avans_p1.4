package nl.avans.essperience.models;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.World;
import net.phys2d.raw.strategies.QuadSpaceStrategy;

public class FopsModel extends GameModel
{
	private World _myWorld;
	
	public FopsModel()
	{
		init();
	}
	
	public void init()
	{
		_myWorld = new World(new Vector2f(0.0f, 10.0f), 3, new QuadSpaceStrategy(20,5));
		
		_myWorld.clear();
		_myWorld.setGravity(0, 30);
	}	
	
	public void update()
	{
		_myWorld.step();
	}
}

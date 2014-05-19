package nl.avans.essperience.entities.simon;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import nl.avans.essperience.main.Main;

public class FruitPiece
{
	String _name;
	Box _shape;
	float _m;
	
	int _massModifier = 10000;
	
	/**
	 * creates a random FruitPiece for the Simon Loves Fruit game
	 * @author jack
	 */
	public FruitPiece()
	{
		int n = (int) ((Math.random() * 4 ));
		
		String name;
		
		switch(n)
		{
		default: //case 0
			name = "banana";
			break;
		case 1:
			name = "orange";
			break;
		case 2:
			name = "apple";
			break;
		case 3:
			name = "pear";
			break;
		}
		
		_name = name;
		
		switch(name)
		{
		case "banana":
			_shape = new Box(80f, 40f);
			_m = 126*_massModifier;
			break;
		case "orange":
			_shape = new Box(60f, 60f);
			_m = 54*_massModifier;
			break;
		case "apple":
			_shape = new Box(60f, 60f);
			_m = 70*_massModifier;
			break;
		case "pear":
			_shape = new Box(60f, 60f);
			_m = 165*_massModifier;
			break;
		}
		
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Box getBox()
	{
		return _shape;
	}
	
	public float getMass()
	{
		return _m;
	}
	
	public Body getBody()
	{
		Body body = new Body(_name, _shape, _m);
		body.setUserData(new String(getName()));
		
		int randX = (int)(Math.random() * Main.GAME.getWidth()/3 ) + (Main.GAME.getWidth()/3) +1;
		
		body.setPosition(randX, 0f);		//position these bodies at the top center
		body.setFriction(1f);
		body.setRestitution(0.1f);
		
		return body;
	}

}

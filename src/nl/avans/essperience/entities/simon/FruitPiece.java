package nl.avans.essperience.entities.simon;

import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;

public class FruitPiece
{
	String _name;
	Box _shape;
	float _m;
	
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
			_shape = new Box(20f, 10f);
			_m = 126/1000;
			break;
		case "orange":
			_shape = new Box(15f, 15f);
			_m = 54/1000;
			break;
		case "apple":
			_shape = new Box(15f, 15f);
			_m = 70/1000;
			break;
		case "pear":
			_shape = new Box(10f, 15f);
			_m = 165/1000;
			break;
		}
		
	}
	
	public FruitPiece(String name)
	{
		_name = name;
		
		switch(name)
		{
		case "banana":
			_shape = new Box(20f, 10f);
			_m = 126/1000;
			break;
		case "orange":
			_shape = new Box(15f, 15f);
			_m = 54/1000;
			break;
		case "apple":
			_shape = new Box(15f, 15f);
			_m = 70/1000;
			break;
		case "pear":
			_shape = new Box(10f, 15f);
			_m = 165/1000;
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
		return new Body(_name, _shape, _m);
	}

}

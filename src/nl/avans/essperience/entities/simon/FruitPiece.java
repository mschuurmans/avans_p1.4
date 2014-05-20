package nl.avans.essperience.entities.simon;

import java.awt.geom.Rectangle2D;
import java.util.List;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.DynamicShape;
import nl.avans.essperience.main.Main;

public class FruitPiece
{
	String _name;
	DynamicShape _shape;
	float _m;
	Vector2f _position;
	
	
	int _massModifier = 10000;
	
	/**
	 * creates a random FruitPiece for the Simon Loves Fruit game
	 * @author jack
	 */
	public FruitPiece()
	{
		_position = new Vector2f((float)(Math.random() * Main.GAME.getWidth()/3 ) + (Main.GAME.getWidth()/3) +1, -100f);
		
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
			_shape = new Circle(30f);
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
	
	public DynamicShape getBox()
	{
		return _shape;
	}
	
	public float getMass()
	{
		return _m;
	}
	
	public Vector2f getPosition()
	{
		return _position;
	}
	
	public Rectangle2D getBoundingBox()
	{
		double x = _position.getX();
		double y = _position.getY();
		double w = _shape.getBounds().getWidth();
		double h = _shape.getBounds().getHeight();
		return new Rectangle2D.Double(x, y, w, h);
	}
	
	public boolean intersects(List<Body> fpList)
	{
		boolean result = false;
		
		for(Body body : fpList)
		{
			if( body.getPosition().getX() == _position.x) 
				result = true;
		}
		
		return result;
	}
	
	public Body getBody()
	{
		Body body = new Body(_name, _shape, _m);
		body.setUserData(new String(getName()));
		
		body.setPosition(_position.x, _position.y);		//position these bodies at the top center
		body.setFriction(100f);
		body.setRotatable(true);
		body.setRestitution(0.7f);
		
		return body;
	}

}

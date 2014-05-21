package nl.avans.essperience.entities.simon;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Circle;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Polygon;
import nl.avans.essperience.main.Main;

public class FruitPiece
{
	String _name;
	DynamicShape _shape;
	float _m;
	Vector2f _position;
	
	private ROVector2f[] _bananaPolygon = new ROVector2f[17];
	
	int _massModifier = 10000;
	
	/**
	 * creates a random FruitPiece for the Simon Loves Fruit game
	 * @author jack
	 */
	public FruitPiece()
	{
		_position = new Vector2f((float)(Math.random() * Main.GAME.getWidth()/3 ) + (Main.GAME.getWidth()/3) +1, -100f);
		
		//init
		fillBananaPolygon();
		
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
			_shape = new Polygon(_bananaPolygon);
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
			if( (int)body.getPosition().getX() == (int)_position.x) 
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
	
	private void fillBananaPolygon()
	{
		ROVector2f[] bp = new ROVector2f[17];
		bp[0] = (new Vector2f(38f, 0f));
		bp[1] = (new Vector2f(42f, 10f));
		bp[2] = (new Vector2f(45f, 10f));
		bp[3] = (new Vector2f(49f, 20f));
		bp[4] = (new Vector2f(46f, 30f));
		bp[5] = (new Vector2f(39f, 40f));
		bp[6] = (new Vector2f(30f, 47f));
		bp[7] = (new Vector2f(15f, 50f));
		bp[8] = (new Vector2f(8f, 50f));
		bp[9] = (new Vector2f(1f, 46f));
		bp[10] = (new Vector2f(1f, 42f));
		bp[11] = (new Vector2f(20f, 33f));
		bp[12] = (new Vector2f(28f, 30f));
		bp[13] = (new Vector2f(36f, 20f));
		bp[14] = (new Vector2f(37f, 10f));
		bp[15] = (new Vector2f(34f, 4f));
		bp[16] = (new Vector2f(38f, 0f));
		
		for(int i = 0; i < bp.length; i++)
		{
			float magnifier = 1.6f;
			((Vector2f)bp[i]).scale(magnifier);
			((Vector2f)bp[i]).x += -40;
			((Vector2f)bp[i]).y += -40;
		}
		
		_bananaPolygon = bp;
	}

}

package nl.avans.essperience.entities.simon;

import java.awt.geom.Rectangle2D;
import java.util.List;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
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
	
	private ROVector2f[] _bananaPath = new ROVector2f[32];
	private ROVector2f[] _orangePath = new ROVector2f[24];
	private ROVector2f[] _applePath = new ROVector2f[37];
	private ROVector2f[] _pearPath = new ROVector2f[29];
	
	int _massModifier = 10000;
	
	/**
	 * creates a random FruitPiece for the Simon Loves Fruit game
	 * @author jack
	 */
	public FruitPiece()
	{
		_position = new Vector2f((float)(Math.random() * Main.GAME.getWidth()/3 ) + (Main.GAME.getWidth()/3) +1, -100f);
		
		//init
		init();
		
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
			_shape = new Polygon(_bananaPath);
			_m = 126*_massModifier;
			break;
		case "orange":
			_shape = new Polygon(_orangePath);
			_m = 54*_massModifier;
			break;
		case "apple":
			_shape = new Polygon(_applePath);
			_m = 70*_massModifier;
			break;
		case "pear":
			_shape = new Polygon(_pearPath);
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
	
	private void init()
	{
		float magnifier = 1.8f;
		
		//banana polygon
		ROVector2f[] bp = new ROVector2f[32];
		bp[0] = (new Vector2f(38f,0f));
		bp[1] = (new Vector2f(38f,2f));
		bp[2] = (new Vector2f(42f,9f));
		bp[3] = (new Vector2f(44f,9f));
		bp[4] = (new Vector2f(47f,12f));
		bp[5] = (new Vector2f(48f,17f));
		bp[6] = (new Vector2f(48f,24f));
		bp[7] = (new Vector2f(46f,30f));
		bp[8] = (new Vector2f(42f,36f));
		bp[9] = (new Vector2f(37f,42f));
		bp[10] = (new Vector2f(30f,46f));
		bp[11] = (new Vector2f(22f,48f));
		bp[12] = (new Vector2f(12f,50f));
		bp[13] = (new Vector2f(8f,50f));
		bp[14] = (new Vector2f(1f,46f));
		bp[15] = (new Vector2f(0f,44f));
		bp[16] = (new Vector2f(1f,42f));
		bp[17] = (new Vector2f(9f,37f));
		bp[18] = (new Vector2f(19f,33f));
		bp[19] = (new Vector2f(25f,30f));
		bp[20] = (new Vector2f(29f,28f));
		bp[21] = (new Vector2f(32f,25f));
		bp[22] = (new Vector2f(34f,23f));
		bp[23] = (new Vector2f(35f,20f));
		bp[24] = (new Vector2f(37f,16f));
		bp[25] = (new Vector2f(37f,11f));
		bp[26] = (new Vector2f(37f,8f));
		bp[27] = (new Vector2f(36f,6f));
		bp[28] = (new Vector2f(35f,5f));
		bp[29] = (new Vector2f(34f,4f));
		bp[30] = (new Vector2f(34f,2f));
		bp[31] = (new Vector2f(36f,1f));
		//scale and reposition the polygon
		for(int i = 0; i < bp.length; i++)
		{
			((Vector2f)bp[i]).scale(magnifier);
			((Vector2f)bp[i]).x += -45;
			((Vector2f)bp[i]).y += -45;
		}
		
		_bananaPath = bp;
		
		//Pear Polygon
		ROVector2f[] pp = new ROVector2f[29];
		pp[0] = (new Vector2f(24f,8f));
		pp[1] = (new Vector2f(25f,5f));
		pp[2] = (new Vector2f(27f,4f));
		pp[3] = (new Vector2f(28f,5f));
		pp[4] = (new Vector2f(28f,6f));
		pp[5] = (new Vector2f(26f,7f));
		pp[6] = (new Vector2f(29f,9f));
		pp[7] = (new Vector2f(31f,11f));
		pp[8] = (new Vector2f(32f,14f));
		pp[9] = (new Vector2f(32f,17f));
		pp[10] = (new Vector2f(33f,20f));
		pp[11] = (new Vector2f(35f,24f));
		pp[12] = (new Vector2f(37f,29f));
		pp[13] = (new Vector2f(38f,35f));
		pp[14] = (new Vector2f(37f,40f));
		pp[15] = (new Vector2f(34f,42f));
		pp[16] = (new Vector2f(29f,45f));
		pp[17] = (new Vector2f(25f,46f));
		pp[18] = (new Vector2f(21f,45f));
		pp[19] = (new Vector2f(16f,42f));
		pp[20] = (new Vector2f(14f,38f));
		pp[21] = (new Vector2f(13f,33f));
		pp[22] = (new Vector2f(14f,27f));
		pp[23] = (new Vector2f(16f,23f));
		pp[24] = (new Vector2f(17f,21f));
		pp[25] = (new Vector2f(17f,17f));
		pp[26] = (new Vector2f(18f,13f));
		pp[27] = (new Vector2f(19f,10f));
		pp[28] = (new Vector2f(21f,9f));
		//scale and reposition the polygon
		for(int i = 0; i < pp.length; i++)
		{
			((Vector2f)pp[i]).scale(magnifier);
			((Vector2f)pp[i]).x += -45;
			((Vector2f)pp[i]).y += -45;
		}
		
		_pearPath = pp;
		
		//apple polygon
		ROVector2f[] ap = new ROVector2f[37];
		ap[0] = (new Vector2f(25f,7f));
		ap[1] = (new Vector2f(26f,9f));
		ap[2] = (new Vector2f(26f,12f));
		ap[3] = (new Vector2f(26f,14f));
		ap[4] = (new Vector2f(29f,14f));
		ap[5] = (new Vector2f(32f,14f));
		ap[6] = (new Vector2f(34f,15f));
		ap[7] = (new Vector2f(36f,17f));
		ap[8] = (new Vector2f(38f,19f));
		ap[9] = (new Vector2f(40f,22f));
		ap[10] = (new Vector2f(40f,25f));
		ap[11] = (new Vector2f(40f,29f));
		ap[12] = (new Vector2f(39f,32f));
		ap[13] = (new Vector2f(37f,36f));
		ap[14] = (new Vector2f(34f,39f));
		ap[15] = (new Vector2f(31f,41f));
		ap[16] = (new Vector2f(28f,41f));
		ap[17] = (new Vector2f(25f,41f));
		ap[18] = (new Vector2f(21f,42f));
		ap[19] = (new Vector2f(17f,40f));
		ap[20] = (new Vector2f(14f,37f));
		ap[21] = (new Vector2f(12f,33f));
		ap[22] = (new Vector2f(10f,29f));
		ap[23] = (new Vector2f(11f,23f));
		ap[24] = (new Vector2f(12f,20f));
		ap[25] = (new Vector2f(14f,17f));
		ap[26] = (new Vector2f(16f,15f));
		ap[27] = (new Vector2f(19f,14f));
		ap[28] = (new Vector2f(16f,12f));
		ap[29] = (new Vector2f(14f,11f));
		ap[30] = (new Vector2f(13f,10f));
		ap[31] = (new Vector2f(15f,9f));
		ap[32] = (new Vector2f(17f,8f));
		ap[33] = (new Vector2f(20f,8f));
		ap[34] = (new Vector2f(22f,10f));
		ap[35] = (new Vector2f(24f,11f));
		ap[36] = (new Vector2f(24f,9f));

		//scale and reposition the polygon
		for(int i = 0; i < ap.length; i++)
		{
			((Vector2f)ap[i]).scale(magnifier);
			((Vector2f)ap[i]).x += -45;
			((Vector2f)ap[i]).y += -45;
		}
		
		_applePath = ap;
		
		//orangePolygon
		ROVector2f[] op = new ROVector2f[24];
		op[0] = (new Vector2f(28f,10f));
		op[1] = (new Vector2f(34f,12f));
		op[2] = (new Vector2f(37f,15f));
		op[3] = (new Vector2f(39f,18f));
		op[4] = (new Vector2f(41f,23f));
		op[5] = (new Vector2f(41f,28f));
		op[6] = (new Vector2f(40f,33f));
		op[7] = (new Vector2f(37f,36f));
		op[8] = (new Vector2f(33f,40f));
		op[9] = (new Vector2f(29f,41f));
		op[10] = (new Vector2f(25f,41f));
		op[11] = (new Vector2f(20f,40f));
		op[12] = (new Vector2f(15f,38f));
		op[13] = (new Vector2f(13f,36f));
		op[14] = (new Vector2f(11f,32f));
		op[15] = (new Vector2f(10f,29f));
		op[16] = (new Vector2f(10f,25f));
		op[17] = (new Vector2f(11f,22f));
		op[18] = (new Vector2f(12f,19f));
		op[19] = (new Vector2f(13f,17f));
		op[20] = (new Vector2f(16f,14f));
		op[21] = (new Vector2f(18f,12f));
		op[22] = (new Vector2f(21f,11f));
		op[23] = (new Vector2f(24f,10f));

		//scale and reposition the polygon
		for(int i = 0; i < op.length; i++)
		{
			((Vector2f)op[i]).scale(magnifier);
			((Vector2f)op[i]).x += -45;
			((Vector2f)op[i]).y += -45;
		}
		
		_orangePath = op;
	}

}

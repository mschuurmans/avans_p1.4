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
	private ROVector2f[] _pearPolygon = new ROVector2f[20];
	private ROVector2f[] _applePolygon = new ROVector2f[111];
	
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
			_shape = new Polygon(_bananaPolygon);
			_m = 126*_massModifier;
			break;
		case "orange":
			_shape = new Circle(30f);
			_m = 54*_massModifier;
			break;
		case "apple":
			_shape = new Polygon(_applePolygon);
			_m = 70*_massModifier;
			break;
		case "pear":
			_shape = new Polygon(_pearPolygon);
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
		float magnifier = 1.6f;
		
		//banana polygon
		ROVector2f[] bp = new ROVector2f[17];
		bp[0] = (new Vector2f(39f, 0f));
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
		//scale and reposition the polygon
		for(int i = 0; i < bp.length; i++)
		{
			float bananaMagnifier = magnifier;
			((Vector2f)bp[i]).scale(bananaMagnifier);
			((Vector2f)bp[i]).x += -40;
			((Vector2f)bp[i]).y += -40;
		}
		
		_bananaPolygon = bp;
		
		//Pear Polygon
		ROVector2f[] pp = new ROVector2f[20];
		pp[0] = (new Vector2f(25f, 5f));
		pp[1] = (new Vector2f(27f, 5f));
		pp[2] = (new Vector2f(27f, 8f));
		pp[3] = (new Vector2f(31f, 10f));
		pp[4] = (new Vector2f(32f, 15f));
		pp[5] = (new Vector2f(33f, 20f));
		pp[6] = (new Vector2f(36f, 25f));
		pp[7] = (new Vector2f(38f, 32f));
		pp[8] = (new Vector2f(38f, 38f));
		pp[9] = (new Vector2f(34f, 43f));
		pp[10] = (new Vector2f(26f, 46f));
		pp[11] = (new Vector2f(18f, 43f));
		pp[12] = (new Vector2f(14f, 38f));
		pp[13] = (new Vector2f(14f, 32f));
		pp[14] = (new Vector2f(16f, 25f));
		pp[15] = (new Vector2f(17f, 20f));
		pp[16] = (new Vector2f(18f, 15f));
		pp[17] = (new Vector2f(19f, 10f));
		pp[18] = (new Vector2f(24f, 8f));
		pp[19] = (new Vector2f(25f, 5f));
		//scale and reposition the polygon
		for(int i = 0; i < pp.length; i++)
		{
			float pearMagnifier = magnifier + 0.2f;
			((Vector2f)pp[i]).scale(pearMagnifier);
			((Vector2f)pp[i]).x += -45;
			((Vector2f)pp[i]).y += -45;
		}
		
		_pearPolygon = pp;
		
		//apple polygon
		ROVector2f[] ap = new ROVector2f[111];
		ap[0] = (new Vector2f(254f,78f));
		ap[1] = (new Vector2f(271f,97f));
		ap[2] = (new Vector2f(270f,97f));
		ap[3] = (new Vector2f(269f,97f));
		ap[4] = (new Vector2f(269f,124f));
		ap[5] = (new Vector2f(268f,142f));
		ap[6] = (new Vector2f(292f,140f));
		ap[7] = (new Vector2f(323f,145f));
		ap[8] = (new Vector2f(348f,158f));
		ap[9] = (new Vector2f(369f,175f));
		ap[10] = (new Vector2f(387f,194f));
		ap[11] = (new Vector2f(400f,225f));
		ap[12] = (new Vector2f(406f,258f));
		ap[13] = (new Vector2f(403f,291f));
		ap[14] = (new Vector2f(391f,325f));
		ap[15] = (new Vector2f(377f,362f));
		ap[16] = (new Vector2f(348f,398f));
		ap[17] = (new Vector2f(318f,415f));
		ap[18] = (new Vector2f(285f,419f));
		ap[19] = (new Vector2f(254f,418f));
		ap[20] = (new Vector2f(212f,421f));
		ap[21] = (new Vector2f(178f,404f));
		ap[22] = (new Vector2f(145f,370f));
		ap[23] = (new Vector2f(120f,331f));
		ap[24] = (new Vector2f(109f,290f));
		ap[25] = (new Vector2f(112f,238f));
		ap[26] = (new Vector2f(124f,202f));
		ap[27] = (new Vector2f(142f,178f));
		ap[28] = (new Vector2f(168f,156f));
		ap[29] = (new Vector2f(196f,144f));
		ap[30] = (new Vector2f(167f,125f));
		ap[31] = (new Vector2f(145f,114f));
		ap[32] = (new Vector2f(131f,109f));
		ap[33] = (new Vector2f(151f,94f));
		ap[34] = (new Vector2f(175f,86f));
		ap[35] = (new Vector2f(201f,89f));
		ap[36] = (new Vector2f(222f,102f));
		ap[37] = (new Vector2f(246f,118f));
		ap[38] = (new Vector2f(246f,99f));
		ap[39] = (new Vector2f(271f,126f));
		ap[40] = (new Vector2f(268f,142f));
		ap[41] = (new Vector2f(292f,140f));
		ap[42] = (new Vector2f(323f,145f));
		ap[43] = (new Vector2f(348f,158f));
		ap[44] = (new Vector2f(369f,175f));
		ap[45] = (new Vector2f(387f,194f));
		ap[46] = (new Vector2f(400f,225f));
		ap[47] = (new Vector2f(406f,258f));
		ap[48] = (new Vector2f(403f,291f));
		ap[49] = (new Vector2f(391f,325f));
		ap[50] = (new Vector2f(377f,362f));
		ap[51] = (new Vector2f(348f,398f));
		ap[52] = (new Vector2f(318f,415f));
		ap[53] = (new Vector2f(285f,419f));
		ap[54] = (new Vector2f(254f,418f));
		ap[55] = (new Vector2f(212f,421f));
		ap[56] = (new Vector2f(178f,404f));
		ap[57] = (new Vector2f(145f,370f));
		ap[58] = (new Vector2f(120f,331f));
		ap[59] = (new Vector2f(111f,290f));
		ap[60] = (new Vector2f(112f,238f));
		ap[61] = (new Vector2f(124f,202f));
		ap[62] = (new Vector2f(142f,178f));
		ap[63] = (new Vector2f(168f,156f));
		ap[64] = (new Vector2f(196f,144f));
		ap[65] = (new Vector2f(167f,127f));
		ap[66] = (new Vector2f(147f,114f));
		ap[67] = (new Vector2f(131f,109f));
		ap[68] = (new Vector2f(151f,92f));
		ap[69] = (new Vector2f(175f,86f));
		ap[70] = (new Vector2f(199f,89f));
		ap[71] = (new Vector2f(222f,102f));
		ap[72] = (new Vector2f(246f,118f));
		ap[73] = (new Vector2f(246f,99f));
		ap[74] = (new Vector2f(240f,91f));
		ap[75] = (new Vector2f(270f,125f));
		ap[76] = (new Vector2f(268f,142f));
		ap[77] = (new Vector2f(292f,140f));
		ap[78] = (new Vector2f(323f,145f));
		ap[79] = (new Vector2f(348f,158f));
		ap[80] = (new Vector2f(369f,175f));
		ap[81] = (new Vector2f(387f,194f));
		ap[82] = (new Vector2f(400f,225f));
		ap[83] = (new Vector2f(406f,258f));
		ap[84] = (new Vector2f(403f,291f));
		ap[85] = (new Vector2f(391f,325f));
		ap[86] = (new Vector2f(377f,362f));
		ap[87] = (new Vector2f(348f,398f));
		ap[88] = (new Vector2f(318f,415f));
		ap[89] = (new Vector2f(285f,419f));
		ap[90] = (new Vector2f(254f,418f));
		ap[91] = (new Vector2f(212f,421f));
		ap[92] = (new Vector2f(178f,404f));
		ap[93] = (new Vector2f(145f,370f));
		ap[94] = (new Vector2f(120f,331f));
		ap[95] = (new Vector2f(110f,290f));
		ap[96] = (new Vector2f(112f,238f));
		ap[97] = (new Vector2f(124f,202f));
		ap[98] = (new Vector2f(142f,178f));
		ap[99] = (new Vector2f(168f,156f));
		ap[100] = (new Vector2f(196f,144f));
		ap[101] = (new Vector2f(167f,126f));
		ap[102] = (new Vector2f(146f,114f));
		ap[103] = (new Vector2f(131f,109f));
		ap[104] = (new Vector2f(151f,93f));
		ap[105] = (new Vector2f(175f,86f));
		ap[106] = (new Vector2f(200f,89f));
		ap[107] = (new Vector2f(222f,102f));
		ap[108] = (new Vector2f(246f,118f));
		ap[109] = (new Vector2f(246f,99f));
		ap[110] = (new Vector2f(240f,91f));
		//scale and reposition the polygon
		for(int i = 0; i < ap.length; i++)
		{
			float appleMagnifier = (magnifier +0.1f) /10;
			((Vector2f)ap[i]).scale(appleMagnifier);
			((Vector2f)ap[i]).x += -45;
			((Vector2f)ap[i]).y += -45;
		}
		
		_applePolygon = ap;
	}

}

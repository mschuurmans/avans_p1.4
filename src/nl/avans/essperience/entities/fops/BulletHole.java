package nl.avans.essperience.entities.fops;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import net.phys2d.math.Vector2f;
import nl.avans.essperience.utils.AssetManager;

public class BulletHole implements Serializable
{
	private static final long serialVersionUID = -8495105465824920463L;

	private Vector2f _location;
	private double _rotation;
	private BufferedImage _image;
	
	public BulletHole(Vector2f _location)
	{
		super();
		this._location = _location;
		this._rotation = Math.toRadians(Math.random() * 360 + 1);
		
		int rand = (int) (Math.random() * 6);
		this._image = ((BufferedImage)AssetManager.Instance().getImage("Fops/bullethole.png")).getSubimage( 90 * rand, 0, 90, 90);
	}

	public Vector2f get_location() {
		return _location;
	}
	
	public void draw (Graphics2D g)
	{
		int imageSize = 50;
		
		g.translate(_location.getX(), _location.getY());
		g.rotate(_rotation);
		
		g.drawImage(_image, -imageSize/2, -imageSize/2, imageSize, imageSize, null);

		g.rotate(-_rotation);
		g.translate(-_location.getX(), -_location.getY());
	}	
}

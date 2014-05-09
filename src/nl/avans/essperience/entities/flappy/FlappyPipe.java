package nl.avans.essperience.entities.flappy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.DockLocations;

public class FlappyPipe 
{
	private String _imageKey = "Flappy/pipe1.png";
	private Image _image;
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	private int _speed;
	
	private DockLocations _docked = DockLocations.Top;
	
	public FlappyPipe(DockLocations loc, int speed)
	{
		this._docked = loc;
		this._speed = speed;
		
		this._image = AssetManager.Instance().getImage(_imageKey);
		
		if(_docked == DockLocations.Bottom)
		{
			//rotating the image 180 degree.
			int width = (int)_image.getWidth(null);
			int height = (int)_image.getHeight(null);
			AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
			tx.translate(-width, -height);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			_image = op.filter((BufferedImage)_image, null);
		}
	}
	public void moveLeft()
	{
		_x -= _speed;
	}
	public void setX(int value)
	{
		_x = value;
	}
	
	public void setY(int value)
	{
		_y = value;
	}
	
	public void setWidth(int value)
	{
		_width = value;
	}
	
	public void setHeight(int value)
	{
		_height = value;
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(_image, _x,_y,_width,_height, null);
	}
	
	public boolean collision(Shape shape)
	{
		Rectangle2D pipec = new Rectangle2D.Double(_x, _y, _width, _height);
		return pipec.intersects((Rectangle2D) shape);
	}
}

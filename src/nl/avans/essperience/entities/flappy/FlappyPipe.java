package nl.avans.essperience.entities.flappy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.DockLocations;

public class FlappyPipe 
{
	private String _imageKey = "Flappy/pipe1.png";
	private String _pipeImageKey = "Flappy/pipeCap.png";
	private Image _pipeImage;
	private Image _pipeCapImage;
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	private int _speed;
	private int _cappY;
	
	private DockLocations _docked = DockLocations.Top;
	
	public FlappyPipe(DockLocations loc, int speed)
	{
		this._docked = loc;
		this._speed = speed;
		
		this._pipeImage = AssetManager.Instance().getImage(_imageKey);
		this._pipeCapImage = AssetManager.Instance().getImage(_pipeImageKey);
		
		if(_docked == DockLocations.Bottom)
		{
			//rotating the image 180 degree.
			int width = (int)_pipeImage.getWidth(null);
			int height = (int)_pipeImage.getHeight(null);
			AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
			tx.translate(-width, -height);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			_pipeImage = op.filter((BufferedImage)_pipeImage, null);
			_pipeCapImage = op.filter((BufferedImage)_pipeCapImage, null);
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
		
		if(_docked == DockLocations.Bottom)
		{
			_cappY = Main.GAME.getHeight() - _height - 5;
		}
		else
		{
			_cappY = _height -36; //36 is the capp image height.
		}
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(_pipeImage, _x,_y,_width,_height, null);
		g.drawImage(_pipeCapImage, _x, _cappY, _width, 36, null);
	}
	
	public boolean collision(Shape shape)
	{
		Rectangle2D pipec = new Rectangle2D.Double(_x, _y, _width, _height);
		return pipec.intersects((Rectangle2D) shape);
	}
	
	public boolean isPast(FlappyPlayer player)
	{
		if(player.getX() > (_x + _width))
			return true;
		else
			return false;
	}
}

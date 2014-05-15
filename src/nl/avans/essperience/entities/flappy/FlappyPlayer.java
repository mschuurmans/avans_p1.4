package nl.avans.essperience.entities.flappy;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import nl.avans.essperience.utils.AssetManager;

public class FlappyPlayer
{
	private int _x = 300;
	private int _y = 300;
	private String _imageKey = "Flappy/bird.png";
	private String _imageKeyFlap1 = "Flappy/bird2.png";
	private String _imageKeyFlap2 = "Flappy/bird3.png";
	private Image[] _birds;
	private int _frameIndex = 0;

	public FlappyPlayer()
	{
		_birds = new Image[4];
		_birds[0] = AssetManager.Instance().getImage(_imageKey);
		_birds[1] = AssetManager.Instance().getImage(_imageKeyFlap1);
		_birds[2] = AssetManager.Instance().getImage(_imageKeyFlap2);
		_birds[3] = AssetManager.Instance().getImage(_imageKeyFlap1);
	}

	public void draw(Graphics g)
	{
		int index = _frameIndex / 10;
		g.drawImage(_birds[index], _x, _y, _birds[index].getWidth(null), _birds[index].getHeight(null), null);
		
		_frameIndex++;
		if(_frameIndex > 39)
			_frameIndex = 0;
	}
	
	public Shape getShape()
	{
		int height = _birds[0].getHeight(null);
		int width = _birds[0].getWidth(null);
		Rectangle2D birdc = new Rectangle2D.Double(_x, _y, width, height);
		return birdc;
	}
	
	public int getY()
	{
		return _y;
	}
	
	public int getX()
	{
		return _x;
	}
	public void moveY(int deltaY)
	{
		_y += deltaY;
	}
}

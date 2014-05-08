package nl.avans.essperience.entities;

import java.awt.Graphics;

import nl.avans.essperience.utils.AssetManager;

public class FlappyPipe 
{
	private String _imageKey = "pipeline.png";
	
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	
	public FlappyPipe()
	{
		
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
		g.drawImage(AssetManager.Instance().getImage(_imageKey), _x,_y,_width,_height, null);
	}
}

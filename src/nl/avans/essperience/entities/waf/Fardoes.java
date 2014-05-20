package nl.avans.essperience.entities.waf;

import java.awt.Graphics;
import java.awt.Image;

import nl.avans.essperience.utils.AssetManager;

public class Fardoes 
{
	private String _imageKey = "fardoes.png";
	private Image _fardoesImage;
	private int _x;
	private int _y;
	private int _width;
	private int _height;
	private int _location;
	
	public Fardoes(int location, int width, int height)
	{
		this._width = width;
		this._height = height;
		
		_fardoesImage = AssetManager.Instance().getImage(_imageKey);
	}
	public void setLocation(int value)
	{
		_location = value;
	}
	
	public int getLocation()
	{
		return _location;
	}
	
	public void draw(Graphics g, int x , int y)
	{
		g.drawImage(_fardoesImage, _x, _y, _width, _height, null);
	}
}

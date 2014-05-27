package nl.avans.essperience.entities.fops;

import java.awt.Graphics2D;
import java.awt.Image;

import nl.avans.essperience.utils.AssetManager;

public class Splash
{
	private int posX;
	private int posY;
	private int _rotation;
	private int _size;
	private String _splashType;
	private Image _splashBananaImage = AssetManager.Instance().getImage("Fops/splashbanana.png");
	private Image _splashAppleImage = AssetManager.Instance().getImage("Fops/splashapple.png");
	private Image _splashPearImage = AssetManager.Instance().getImage("Fops/splashpear.png");
	private Image _splashOrangeImage = AssetManager.Instance().getImage("Fops/splashorange.png");
	private Image _splashImage;
	
	public final String BANANA = "banana";
	public final String APPLE = "apple";
	public final String PEAR = "pear";
	public final String ORANGE = "orange";
	
	public Splash(int x, int y, String type)
	{
		this.posX = x;
		this.posY = y;
		this._splashType = type;
		_size = 150;
		int rand = (int)(Math.random() * 360);
		_rotation = rand;
		switch (_splashType)
		{
		case BANANA: _splashImage = _splashBananaImage;
		break;
		case APPLE: _splashImage = _splashAppleImage;
		break;
		case PEAR: _splashImage = _splashPearImage;
		break;
		case ORANGE: _splashImage = _splashOrangeImage;
		break; 
		default: _splashImage = _splashBananaImage;
		break;
		}
	}
	
	public Image getImage() 
	{
		return _splashImage;
	}
	
	public int getX()
	{
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}
	
	public int getRotation()
	{
		return _rotation;
	}
	
	public void setSize(int size)
	{
		_size = size;
	}
	
	public String getType()
	{
		return _splashType;
	}
	
	public void draw(Graphics2D g)
	{
		g.translate(posX, posY);
		g.rotate(Math.toRadians(_rotation));
		g.drawImage(_splashImage, 0-_size/2, 0-_size/2, _size, _size, null);
		g.rotate(-Math.toRadians(_rotation));
		g.translate(-posX, -posY);
	}
}

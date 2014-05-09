package nl.avans.essperience.entities.flappy;

import java.awt.Graphics;
import java.awt.Image;

public class FlappyPlayer
{
	private int _x;
	private int _y;
	private Image _bird;
	
	public FlappyPlayer()
	{
		
	}
	
	public void draw(Graphics g)
	{
		g.drawImage(_bird, _x, _y, _bird.getWidth(null), _bird.getHeight(null), null);
	}
}

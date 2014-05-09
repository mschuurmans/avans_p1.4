package nl.avans.essperience.entities.flappy;

import java.awt.Graphics;
import java.awt.Image;

import nl.avans.essperience.utils.AssetManager;

public class FlappyPlayer
{
	private int _x = 300;
	private int _y = 300;
	private String _imageKey = "Flappy/bird.png";
	private Image _bird;

	public FlappyPlayer()
	{
		_bird = AssetManager.Instance().getImage(_imageKey);
	}

	public void draw(Graphics g)
	{
		g.drawImage(_bird, _x, _y, _bird.getWidth(null), _bird.getHeight(null), null);
	}
}

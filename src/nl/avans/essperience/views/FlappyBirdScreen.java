package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.utils.AssetManager;

public class FlappyBirdScreen extends GameScreen
{
	private BufferedImage _background;
	private BufferedImage _spritesheet;
	private static final long serialVersionUID = -2013215913618586135L;

	public FlappyBirdScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		_spritesheet = (BufferedImage) AssetManager.Instance().getImage("flappy.png");
		//_background = spritesheet.getSubimage(288, 512, 288, 512);
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawString("FlappyBird Screen", 10, 15);
		g2.drawImage(_spritesheet, 0, 0, 288, 512, null);
		super.drawLives(g);
	}

}

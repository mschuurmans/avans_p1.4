package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.utils.AssetManager;

public class IndianaJantjeScreen extends GameScreen
{
	// Images
	private BufferedImage _spritesheet;
	private Image _background;
	
	private static final long serialVersionUID = -2013215913618586135L;

	public IndianaJantjeScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init()
	{		
		
	}
	
	@Override
	public void update() 
	{
		
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	}

}

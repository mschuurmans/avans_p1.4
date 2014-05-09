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
	private int _side;
	
	private static final long serialVersionUID = -2013215913618586135L;

	public IndianaJantjeScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		init();
	}

	public void init()
	{		
		_side = chooseSide();
	}
	
	@Override
	public void update() 
	{
		switch (_side)
		{
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			default: System.out.println("OMFG SIDE WENT WRONG");
				break;
		}
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
	}
	
	private int chooseSide() {
		int rand = (int)(Math.random() * 3);
		return rand;
	}

}

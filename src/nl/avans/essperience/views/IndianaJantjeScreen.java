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
	private Image _stone;
	private int _side;
	private double _offset;
	private int _drawX;
	private int _drawY;
	
	private static final long serialVersionUID = -2013215913618586135L;

	public IndianaJantjeScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		_background = AssetManager.Instance().getImage("IndianaJantje/background.jpg");
		_stone = AssetManager.Instance().getImage("Flappy/flappy.png");
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
				_offset = 0;
				break;
			case 1:
				_offset = 1;
				break;
			case 2:
				_offset = -1;
				break;
			default: System.out.println("OMFG SIDE WENT WRONG");
				break;
		}
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		_drawY = screenHeight/2;
		_drawX = (int) ((screenWidth/2) + ((screenWidth/4)*_offset));
		
		int backgroundWidth = _background.getWidth(null);
		g.drawImage(_background, 0, 0, screenWidth, screenHeight, null);
		g.drawImage(_stone, _drawX, _drawY, screenWidth/2, screenHeight/2, null);
		init();
	}
	
	private int chooseSide() {
		int rand = (int)(Math.random() * 3);
		return rand;
	}

}

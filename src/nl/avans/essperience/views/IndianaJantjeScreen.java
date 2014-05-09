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
	private int _drawX;
	private int _drawY;
	private int _index;
	
	private static final long serialVersionUID = -2013215913618586135L;

	public IndianaJantjeScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		_background = AssetManager.Instance().getImage("IndianaJantje/background.jpg");
		_spritesheet = (BufferedImage) AssetManager.Instance().getImage("IndianaJantje/stonesspritesheet.png");
		init();
	}

	public void init()
	{		
		if(_index==0)
			_side = chooseSide();
	}
	
	@Override
	public void update() 
	{
		_drawX = (_index % 6) * 800;
		_drawY = (_index / 6) * 800;
		_index++;
		_index %= 18;
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		
		int backgroundWidth = _background.getWidth(null);
		g.drawImage(_background, 0, 0, screenWidth, screenHeight, null);
		
		BufferedImage subImg = _spritesheet.getSubimage(_drawX, _drawY, 800, 800);
		g.drawImage(subImg, screenWidth/3*_side, screenHeight-(screenWidth/3), screenWidth/3, screenWidth/3, null);
		init();
	}
	
	private int chooseSide() {
		int rand = (int)(Math.random() * 3);
		return rand;
	}

}

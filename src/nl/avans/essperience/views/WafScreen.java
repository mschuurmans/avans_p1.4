package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Image;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.WafModel;
import nl.avans.essperience.utils.AssetManager;

public class WafScreen extends GameScreen
{
	private static final long serialVersionUID = -8219699149382321785L;
	private Image _openLillyImage;
	private Image _closedLillyImage;
	private int _screenHeight;
	private int _screenWidth;
	private int _imageHeight;
	private int _imageWidth;
	private int _xOffset;
	private int _yOffset;
	
	
	public WafScreen(WafModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		_openLillyImage = AssetManager.Instance().getImage("Waf/lilly open.png");
		_closedLillyImage = AssetManager.Instance().getImage("Waf/lilly closed.png");
		_screenWidth = Main.GAME.getWidth();
		_screenHeight = Main.GAME.getHeight();
		_imageWidth = _screenWidth/7;
		_imageHeight = _screenHeight/7;
		_xOffset = (int)(_imageWidth/4f);
		_yOffset = _imageHeight/3;
	}

	@Override
	public void update() 
	{
		_gameModel.update();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		WafModel model = (WafModel)_gameModel;
		
		g.drawImage(model.getBackground(), 0, 0, _screenWidth, _screenHeight, null);
		for (int y = 0; y < 3; y++) 
		{
			for (int x = 0; x < 3; x++) 
			{
				if(model.getFardoes().getLocation() != ((y*3)+x)+1)
					g.drawImage(_closedLillyImage, _imageWidth * ((x*2)+1), _imageHeight * ((y*2)+1), _imageWidth, _imageHeight, null);
				else
				{
					g.drawImage(_openLillyImage, _imageWidth * ((x*2)+1), _imageHeight * ((y*2)+1), _imageWidth, _imageHeight, null);
					model.getFardoes().draw(g, _imageWidth * ((x*2)+1) + _xOffset, _imageHeight * ((y*2)+1) - _yOffset);
				}
			}
		}
		g.drawString("Whack A Fardoes Screen", 10, 10);
		addTimeBar(g);
	}

}

package nl.avans.essperience.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.SimonGameModel;
import nl.avans.essperience.utils.AssetManager;

public class SimonGameScreen extends GameScreen
{

	private static final long serialVersionUID = -5638706431021884300L;
	private Image _background;
	private Image _backgroundTree;
	private int _screenWidth;
	private int _screenHeight;
	
	private int _flashIntensity;
	

	public SimonGameScreen(SimonGameModel model) 
	{
		super(model);
		
		super.setBackground(new Color(0xf_f6ffcc));
		_backgroundTree = AssetManager.Instance().getImage("Simon/simon_background_tree.png");
		_background = AssetManager.Instance().getImage("Simon/simon_background.png");
		_screenWidth = Main.GAME.getWidth();
		_screenHeight = Main.GAME.getHeight();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		_gameModel.update();
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		SimonGameModel model = (SimonGameModel) _gameModel;
		
		//model content
		model.draw(g);
		
		//foreground tree
		g.drawImage(_backgroundTree, 0, 0, _screenWidth, _screenHeight, null);
		
		//Grass forground
		for (int i = 0; i < (Main.GAME.getWidth() / 380) +1; i++)
			g.drawImage(_background, i * 380, Main.GAME.getHeight() - 130, 380, 130, null);
		
		//drawOverlay GUI. should be displayed in front of the game elemnts
		model.drawOverlay(g);
		
		// if model says that the user has guessed right. set the alpha level at 0xff/2
		if( ((SimonGameModel)_gameModel).getGuessedRight() )
			_flashIntensity = 0xff/2;
		// when the alphalevel is above 10 display a white screen with the given alpha level.
		if(_flashIntensity > 10)
		{
			((SimonGameModel)_gameModel).resetGuessedRight();
			
			g.setColor(new Color(0xff, 0xff, 0xff, _flashIntensity));
			g.fillRect(0, 0, _screenWidth, _screenHeight);
			_flashIntensity /= 2;
			g.setColor(Color.black);
		}
		//timebar
		addTimeBar(g);
	}

}

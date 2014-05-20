package nl.avans.essperience.views;

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
	

	public SimonGameScreen(SimonGameModel model) 
	{
		super(model);
		
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
		
		System.out.println("drawing simon");
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		
		
		SimonGameModel model = (SimonGameModel) _gameModel;
		
		g.drawImage(_background, 0, 0, _screenWidth, _screenHeight, null);

		model.draw(g);	
		
		g.drawImage(_backgroundTree, 0, 0, _screenWidth, _screenHeight, null);
	}

}

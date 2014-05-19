package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.SimonGameModel;

public class SimonGameScreen extends GameScreen
{

	private static final long serialVersionUID = -5638706431021884300L;

	public SimonGameScreen(SimonGameModel model) 
	{
		super(model);
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
		
		model.draw(g);	
	}

}

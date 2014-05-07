package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.models.GameModel;

public class MenuScreen extends GameScreen
{
	private boolean _debug = false;
	
	public MenuScreen() 
	{
		super(new GameModel());
	}

	@Override
	public void update() 
	{
		if(_debug)
			System.out.println("Update called.");
	}

	public void paintComponent(Graphics g)
	{
		//drawing code.
		if(_debug)
			System.out.println("Drawing menu screen");
		
		g.drawString("Menu Screen", 10, 15);
	}
	

}

package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.models.GameModel;

public class FlappyBirdScreen extends GameScreen
{
	private static final long serialVersionUID = -2013215913618586135L;

	public FlappyBirdScreen(GameModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		System.out.println("FLAPPY DRAW CALLED");
		g.drawString("FlappyBird Screen", 10, 15);
	}

}

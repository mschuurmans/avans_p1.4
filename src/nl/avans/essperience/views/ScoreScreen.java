package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.models.ScoreModel;

public class ScoreScreen extends GameScreen
{
	private static final long serialVersionUID = -2401781820632799509L;
	private int _livesLeft = 3;
	private int _level = 1;
	public ScoreScreen(ScoreModel model) 
	{
		super(model);
	}
	
	public void setLivesLeft(int value)
	{
		this._livesLeft = value;
	}
	public void setLevel(int value)
	{
		this._level = value;
	}
	
	@Override
	public void update() 
	{
				
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawString("The Essperience Score screen!", 200, 100);
		g.drawString("Lives left: " + _livesLeft, 200, 125);
		g.drawString("Current Level: " + _level, 200, 150);
	}

}

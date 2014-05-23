package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.models.FopsModel;

public class FopsScreen extends GameScreen 
{
	private static final long serialVersionUID = -6529289814578856921L;

	public FopsScreen(FopsModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		_gameModel.update();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	

}

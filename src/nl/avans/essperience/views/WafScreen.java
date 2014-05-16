package nl.avans.essperience.views;

import java.awt.Graphics;

import nl.avans.essperience.models.WafModel;

public class WafScreen extends GameScreen
{
	private static final long serialVersionUID = -8219699149382321785L;
	
	public WafScreen(WafModel model) 
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
		WafModel model = (WafModel)_gameModel;
		
		model.getFardoes().draw(g);
	}

}

package nl.avans.essperience.views;

import java.awt.Graphics;
import java.util.ArrayList;

import nl.avans.essperience.entities.simon.FruitPiece;
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
		FopsModel model = (FopsModel)_gameModel;
		ArrayList<FruitPiece> fruits = model.getFruits();
		for (FruitPiece f : fruits)
		{
			// TODO draw fruits
		}
		
		addTimeBar(g);
	}
	

}

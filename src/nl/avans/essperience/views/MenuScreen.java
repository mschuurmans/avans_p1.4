package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;

public class MenuScreen extends GameScreen
{
	private static final long serialVersionUID = 4178628810705405806L;
	
	private boolean _debug = false;
	private int _selected = 0;
	
	public void setSelected(int value)
	{
		_selected = value;
		System.out.println("Selected value has been set to: " + _selected);
	}
	
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
		super.paintComponent(g);
		//drawing code.
		if(_debug)
			System.out.println("Drawing menu screen");
		if(Main.GAME != null)
		{
			int xCenter = Main.GAME.getWidth() / 2;
			int yCenter = Main.GAME.getHeight() / 2;
			
			Font font = new Font("Arial", Font.PLAIN, 60) ;
			BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			FontMetrics fm = img.getGraphics().getFontMetrics(font);
			String startGame = "Start Game";
			String exitGame = "Exit Game";
			
			if(_selected == 0)
				startGame = ">> Start Game <<";
			
			if(_selected == 1)
				exitGame = ">> Exit Game <<";
			
			int stringWidth = fm.stringWidth(startGame);
			int optionStringWidth = fm.stringWidth(exitGame);
			
			g.setFont(font);
			g.drawString(startGame, xCenter - (stringWidth / 2), yCenter);
			g.drawString(exitGame, xCenter - (optionStringWidth / 2), yCenter + 55);
		}
	}
	

}

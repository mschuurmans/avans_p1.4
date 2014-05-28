package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

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
		if(Main.GAME.getScore() == 0) // TODO to change to != 0
		{
			//Drawing the keyboard keys
			for(int i = 0; i < 26; i++) // normal letter keys
			{	
				int width = 93;
				int height = 88;
				
				int xIndex, yIndex, xOffset;
				
				if( i < 10 )
				{
					xIndex = i % 10;
					yIndex = 1;
					xOffset = 0;
				}
				else if( i < 19)
				{
					xIndex = i % 10;
					yIndex = 2;
					xOffset = 20;
				}
				else
				{
					xIndex = i % 9;
					yIndex = 3;
					xOffset = 0;
				}
			
				int x = 100 + xOffset + xIndex * (width + 2);
				int y = 100 + yIndex * (height + 3);
				
				g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey.png"), x, y, width, height, null);
				Font font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				g.drawString("" + i, x + width/5, y + height /2);
			}
			//shift space and enter
			//TODO
		}
		
		if(Main.GAME != null)
		{
			int xCenter = Main.GAME.getWidth() / 2;
			int yCenter = Main.GAME.getHeight() / 2;
			
			Font font = new Font("Arial", Font.PLAIN, 60) ;
			BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			FontMetrics fm = img.getGraphics().getFontMetrics(font);
			String startGame = "Press A and D to start";
			
			
			int stringWidth = fm.stringWidth(startGame);
			
			g.setFont(font);
			g.drawString(startGame, xCenter - (stringWidth / 2), yCenter);
		}
	}
	

}

package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.phys2d.math.Vector2f;
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
			
			//keyboard
			char[] charArray = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
			char shiftArrow = 0x21E7;
			String shiftString = "Shift";
			char returnArrow = 0x23CE;
			String returnString = "Enter";
			int selectedKey = 5;

			int width = 93;
			int keyHeight = 88;
			
			Vector2f[] extraKeyLocations = new Vector2f[4];
			
			//Drawing the keyboard keys
			for(int i = 0; i < 26; i++) // normal letter keys
			{	
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
					xOffset = 40;
				}
				else
				{
					xIndex = i % 9;
					yIndex = 3;
					xOffset = 80;
				}
			
				int x = 100 + xOffset + (xIndex * (width + 2));
				int y = 100 + yIndex * (keyHeight + 3);

				extraKeyLocations[0] = new Vector2f((float) 100 - (4 + 9), 100 + yIndex * (keyHeight + 3));
				extraKeyLocations[1] = new Vector2f((float) 100 + 3 + (9* (width)), 100 + yIndex * (keyHeight + 3));
				extraKeyLocations[2] = new Vector2f((float) 100 + 60 + (9* (width)), 100 + 1 * (keyHeight + 3));
				extraKeyLocations[3] = new Vector2f((float) 100 + 40 + (2* (width)), 100 + 4 * (keyHeight + 3));
				
				//draw the Keys
				g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey.png"), x, y, width, keyHeight, null);
				
				//draw the letters on the Key
				Font font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				g.drawString("" + charArray[i], x + width/5, y + keyHeight /2);
				
				//if selected. display hand
				if(i == selectedKey)
					g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x + 60, y + 50, 22, 32, null);
			}
			//shift space and enter
			//shift keys
			for(int i = 0; i < 2; i++)
			{
				int x = (int)extraKeyLocations[i].getX();
				int y = (int)extraKeyLocations[i].getY();
				g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey_shift.png"), x, y, 186, 88, null);
				g.drawString(shiftArrow + shiftString, x + width/5, y + keyHeight /2);
			}
			//enter key
			int x = (int)extraKeyLocations[2].getX();
			int y = (int)extraKeyLocations[2].getY();
			g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey_return.png"), x, y, 179, 179, null);
			g.drawString(returnString + returnArrow, x + width/5, y + keyHeight + keyHeight /2);
			//space bar
			x = (int)extraKeyLocations[3].getX();
			y = (int)extraKeyLocations[3].getY();
			g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey_spacebar.png"), x, y, 558, 88, null);
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

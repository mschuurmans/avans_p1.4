package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import net.phys2d.math.Vector2f;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameOverModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class GameOverScreen extends GameScreen {

	private static final long serialVersionUID = 6570132551382597299L;

	public GameOverScreen(GameOverModel model) {
		super(model);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int xCenter = Main.GAME.getWidth() / 2;
		int yLocation = Main.GAME.getHeight() / 6;
		
		Font font = new Font("Arial", Font.PLAIN, 60) ;
		String gameOver = "Game Over";
		
		g.setFont(font);
		g.drawString(gameOver, xCenter - Utils.getWidth(gameOver, g.getFont()) /2, yLocation);
		
		String scoreString = "Your score: " + Main.GAME.getScore();
		g.drawString(scoreString, xCenter - Utils.getWidth(scoreString, g.getFont()) /2, yLocation + 60);
		
		String enterString = "Enter your name to submit your highscore";
		g.drawString(enterString, xCenter - Utils.getWidth(enterString, g.getFont()) /2, yLocation + 120);
		
		String orNot = "Or hit the spacebar on the keyboard to skip";
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString(orNot, 50, yLocation + 150);
		g.setFont(font);
		
		String nameString = ((GameOverModel)_gameModel).getName() + ((GameOverModel)_gameModel).getNextChartoDisplay();
		g.drawString(nameString, 200, yLocation + 180);
		
		//Drawing the keyboard
		if(true) // TODO to change to != 0
		{

			int width = 93;
			int keyHeight = 88;
			int keyboardWidth = 1090;
			
			int keybX = Main.GAME.getWidth() / 2 - keyboardWidth / 2;
			int keybY = 250;
			
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
			
				int x = keybX + xOffset + (xIndex * (width + 2));
				int y = keybY + yIndex * (keyHeight + 3);

				extraKeyLocations[0] = new Vector2f((float) keybX - (4 + 9), keybY + yIndex * (keyHeight + 3)); //left shift
				extraKeyLocations[1] = new Vector2f((float) keybX + 3 + (9* (width)), keybY+ yIndex * (keyHeight + 3)); //right shift
				extraKeyLocations[2] = new Vector2f((float) keybX + 60 + (9* (width)), keybY + 1 * (keyHeight + 3)); //Enter key
				extraKeyLocations[3] = new Vector2f((float) keybX + 40 + (2* (width)), keybY + 4 * (keyHeight + 3)); //spacebar
				
				//draw the Keys
				g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey.png"), x, y, width, keyHeight, null);
				
				//draw the letters on the Key
				font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				
				char keyChar = ((GameOverModel)_gameModel).getKeyboardCharacters()[i];					
				g.drawString("" + keyChar, x + width/5, y + keyHeight /2);
				
				//if selected. display hand
				if(i == ((GameOverModel)_gameModel).getSelectedKey())
					g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x + 60, y + 50, 22, 32, null);
			}
			//shift space and enter
			//shift keys
			for(int i = 0; i < 2; i++)
			{
				int x = (int)extraKeyLocations[i].getX();
				int y = (int)extraKeyLocations[i].getY();
				int keydownOffset = 0;
				Image image;
				
				if( ((GameOverModel)_gameModel).getShiftEnabled() ) //if shift = true
				{
					image = AssetManager.Instance().getImage("Essperience/keyboardkey_shift_down.png");
					keydownOffset = 4;
				}
				else
					image = AssetManager.Instance().getImage("Essperience/keyboardkey_shift.png");
				
				g.drawImage(image, x, y, 186, 88, null);
				g.drawString(((GameOverModel)_gameModel).getShiftString(), x + width/5, y + keydownOffset + keyHeight /2);
			}
			//enter key
			int x = (int)extraKeyLocations[2].getX();
			int y = (int)extraKeyLocations[2].getY();
			g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey_return.png"), x, y, 179, 179, null);
			g.drawString(((GameOverModel)_gameModel).getReturnString(), x + width/5, y + keyHeight + keyHeight /2);
			//space bar
			x = (int)extraKeyLocations[3].getX();
			y = (int)extraKeyLocations[3].getY();
			g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey_spacebar.png"), x, y, 558, 88, null);
			
			//place the selection hand on the special keys
			if(((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.ENTERID)
			{
				x = (int)extraKeyLocations[2].getX() + 130;
				y = (int)extraKeyLocations[2].getY() + 130;
				g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x, y, 22, 32, null);
			}
			else if (((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.LEFTSHIFTID)
			{
				x = (int)extraKeyLocations[0].getX() + 60;
				y = (int)extraKeyLocations[0].getY() + 50;
				g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x, y, 22, 32, null);
			}
			else if (((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.RIGHTSHIFTID)
			{
				x = (int)extraKeyLocations[1].getX() + 60;
				y = (int)extraKeyLocations[1].getY() + 50;
				g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x, y, 22, 32, null);
			}
			else if (((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.SPACEBARID)
			{
				x = (int)extraKeyLocations[3].getX() + 500;
				y = (int)extraKeyLocations[3].getY() + 60;
				g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x, y, 22, 32, null);
			}
		}
		
	}

}

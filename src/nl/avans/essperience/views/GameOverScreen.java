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
	
	private Vector2f _handPosition = new Vector2f();

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
		int yLocation = Main.GAME.getHeight() / 12;
		
		Font font = new Font("Arial", Font.PLAIN, 60) ;
		String gameOver = "Game Over";
		
		g.setFont(font);
		g.drawString(gameOver, xCenter - Utils.getWidth(gameOver, g.getFont()) /2, yLocation);
		
		String scoreString = "Your score: " + Main.GAME.getScore();
		g.drawString(scoreString, xCenter - Utils.getWidth(scoreString, g.getFont()) /2, yLocation + 60);
		
		String enterString = "Enter your name: ";
		g.drawString(enterString, xCenter /2 - Utils.getWidth(enterString, g.getFont()) /2, yLocation + 170);
		
		String nameString = ((GameOverModel)_gameModel).getName() + ((GameOverModel)_gameModel).getNextChartoDisplay();
		g.drawString(nameString, xCenter -50, yLocation + 170);
		
		String orNot = "Or hit the spacebar on the keyboard to skip";
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		g.drawString(orNot, 50, yLocation + 190);
		g.setFont(font);
		
		//Drawing the keyboard
		if(true) // TODO to change to != 0
		{

			int width = 93;
			int keyHeight = 88;
			int keyboardWidth = 1090;
			int keyboardHeight = 450;
			
			int keybX;
			
			if (Main.GAME.getWidth() == 1920)
				keybX = Main.GAME.getWidth() / 2 - keyboardWidth / 2 - 100;
			else
				keybX = Main.GAME.getWidth() / 2 - keyboardWidth / 2;
			
			int keybY = Main.GAME.getHeight() - keyboardHeight;
			
			Vector2f[] extraKeyLocations = new Vector2f[4];
			
			//draw the names from the quickSelection list
			g.setFont(new Font("Arial", Font.PLAIN, 24));
			for(String s : ((GameOverModel)_gameModel).getHighscoreQuickList())
			{
				int i = ((GameOverModel)_gameModel).getHighscoreQuickList().indexOf(s);
				int x = keybX + ( i * 145 );
				int y = keybY  - 30;
				
				int listSize = ((GameOverModel)_gameModel).getHighscoreQuickList().size();
				String string = ((GameOverModel)_gameModel).getHighscoreQuickList().get( listSize - (i + 1)); //dem dirty tricks to reverse the list
				
				g.drawString(string, x, y);
				
				g.draw3DRect(x, y -22, 140, 30, true);
				
				if( ((GameOverModel) _gameModel).getSelectedKey() == i -20)
				{
					_handPosition.set(x + 70, y - 5);
				}
			}
			g.setFont(font);
			
			//Drawing the keyboard keys
			for(int i = 0; i < 26; i++) // normal letter keys
			{	
				int xIndex, yIndex, xOffset;
				
				if( i < 10 )
				{
					xIndex = i % 10;
					yIndex = 0;
					xOffset = 0;
				}
				else if( i < 19)
				{
					xIndex = i % 10;
					yIndex = 1;
					xOffset = 40;
				}
				else
				{
					xIndex = i % 9;
					yIndex = 2;
					xOffset = 80;
				}
			
				int x = keybX + xOffset + (xIndex * (width + 2));
				int y = keybY + yIndex * (keyHeight + 3);

				extraKeyLocations[0] = new Vector2f((float) keybX - (4 + 9), keybY + yIndex * (keyHeight + 3)); //left shift
				extraKeyLocations[1] = new Vector2f((float) keybX + 3 + (9* (width)), keybY+ yIndex * (keyHeight + 3)); //right shift
				extraKeyLocations[2] = new Vector2f((float) keybX + 60 + (9* (width)), keybY + 0 * (keyHeight + 3)); //Enter key
				extraKeyLocations[3] = new Vector2f((float) keybX + 40 + (2* (width)), keybY + 3 * (keyHeight + 3)); //spacebar
				
				//draw the Keys
				g.drawImage(AssetManager.Instance().getImage("Essperience/keyboardkey.png"), x, y, width, keyHeight, null);
				
				//draw the letters on the Key
				font = new Font("Arial", Font.PLAIN, 30);
				g.setFont(font);
				
				char keyChar = ((GameOverModel)_gameModel).getKeyboardCharacters()[i];					
				g.drawString("" + keyChar, x + width/5, y + keyHeight /2);
				
				//if selected. display hand
				if(i == ((GameOverModel)_gameModel).getSelectedKey())
					_handPosition.set(x +60, y +50);
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
				_handPosition.set(x, y);
			}
			else if (((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.LEFTSHIFTID)
			{
				x = (int)extraKeyLocations[0].getX() + 60;
				y = (int)extraKeyLocations[0].getY() + 50;
				_handPosition.set(x, y);
			}
			else if (((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.RIGHTSHIFTID)
			{
				x = (int)extraKeyLocations[1].getX() + 60;
				y = (int)extraKeyLocations[1].getY() + 50;
				_handPosition.set(x, y);
			}
			else if (((GameOverModel)_gameModel).getSelectedKey() == GameOverModel.SPACEBARID)
			{
				x = (int)extraKeyLocations[3].getX() + 500;
				y = (int)extraKeyLocations[3].getY() + 60;
				_handPosition.set(x, y);
			}
			
			//drawing the hand on the position that has been set at the beginning
			x = (int) _handPosition.getX();
			y = (int) _handPosition.getY();
			g.drawImage(AssetManager.Instance().getImage("Essperience/cursorhand.png"), x, y, 22, 32, null);
			
			//end of keyboard
			
			
			//drawing the Wiimote keyMap
			if (Main.GAME.getWidth() == 1920)
			{
				x = keybX + keyboardWidth + 180;
				y = keybY -80;
				font = new Font("Arial", Font.PLAIN, 20);
				g.setFont(font);
				String string = "Key selection";
				g.drawString(string, x - Utils.getWidth(string, font), y + 83);

				string = "Toggle Shift";
				g.drawString(string, x - Utils.getWidth(string, font), y + 119);

				string = "Enter character";
				g.drawString(string, x - Utils.getWidth(string, font), y + 151);

				string = "Select Enter Key";
				g.drawString(string, x - Utils.getWidth(string, font), y + 189);

				string = "Backspace";
				g.drawString(string, x - Utils.getWidth(string, font), y + 228);

				string = "Spacebar";
				g.drawString(string, x - Utils.getWidth(string, font), y + 267);

				g.drawImage(AssetManager.Instance().getImage("Essperience/wiimote.png"), x, y, 300, 463, null);
			}
		}
		
	}

}

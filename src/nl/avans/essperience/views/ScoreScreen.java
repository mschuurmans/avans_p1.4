package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import nl.avans.essperience.controllers.GameHandler;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.ScoreModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

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
		((ScoreModel) _gameModel).scoreCount();		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		int size = 200;
		int space = 50;
		int center = Main.GAME.getWidth() / 2;
		//int y = (Main.GAME.getHeight() / 2) - size / 2 + (Main.GAME.getHeight() / 10);
		int y = 325;
		
		g.drawImage(AssetManager.Instance().getImage("Essperience/bliss_background.jpg"), 0, 0, 1920, 1200, null);
		
		Image img = AssetManager.Instance().getImage("heart.png");
		if(_livesLeft > 0)
			g.drawImage(img, center - (size + space + size/2), y ,size, size, null);
		
		if(_livesLeft > 1)
			g.drawImage(img, center - (size/2), y ,size, size, null);
		
		if(_livesLeft > 2)
			g.drawImage(img, center + (space + size/2), y ,size, size, null);
		
		Graphics2D g2 = (Graphics2D)g;
		
		
		g2.setFont(new Font("Arial", Font.PLAIN, 80));
		Utils.drawString(g, ""+_level, center - Utils.getWidth(""+_level, getFont()), 150);
		Utils.drawString(g, "" + ((ScoreModel) _gameModel).getRandomScore(), center + (Utils.getWidth("Your score: " + ((ScoreModel) _gameModel).getScore(), g2.getFont())/2), 175);
		Utils.drawString(g, "Your score: " + ((ScoreModel) _gameModel).getScore(), center - (Utils.getWidth("Your score: " + ((ScoreModel) _gameModel).getScore(), g2.getFont())/2), 250);
		Utils.drawString(g, "Next Game: " + ((GameHandler.getNextGame())), center - (Utils.getWidth("Next Game: " + (GameHandler.getNextGame()), g2.getFont())/2), 650);
	}

}

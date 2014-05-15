package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Image;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.ScoreModel;
import nl.avans.essperience.utils.AssetManager;

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
		
		int size = 200;
		int space = 50;
		int center = Main.GAME.getWidth() / 2;
		int y = (Main.GAME.getHeight() / 2) - size / 2;
		//200 50 100
		Image img = AssetManager.Instance().getImage("heart.png");
		if(_livesLeft > 0)
			g.drawImage(img, center - (size + space + size/2), y ,size, size, null);
		
		if(_livesLeft > 1)
			g.drawImage(img, center - (size/2), y ,size, size, null);
		
		if(_livesLeft > 2)
			g.drawImage(img, center + (space + size/2), y ,size, size, null);
	}

}

package nl.avans.essperience.views;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.models.RedButtonModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

@SuppressWarnings("serial")
public class RedButtonScreen extends GameScreen {
	
	private Image _arrow;
	
	public RedButtonScreen(GameModel model) {
		super(model);
		// TODO Auto-generated constructor stub
		_arrow = AssetManager.Instance().getImage("RedButton/arrow.png");
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		_gameModel.update();
		if (((RedButtonModel) (_gameModel)).getColorChange()){
			setBackground(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()).brighter().brighter());
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		int xCenter = Main.GAME.getWidth() / 2;
		int yCenter = Main.GAME.getHeight() / 2;
		int arrowWidth = screenWidth/10;
		int arrowHeight = screenWidth/5;
		int numberOfArrowsInCircle = 24;
		int numberOfArrows = 7;
		String dont;
		
		Font font = Main.GAME.getFont(10);
		g.setFont(font);
		if (((RedButtonModel)_gameModel).getDont())
		{
			dont = "DON'T";
		}
		else
		{
			dont = "DO";
		}
		int stringWidth = Utils.getWidth(dont, g.getFont());
		g.drawString(dont, xCenter - (stringWidth / 2), yCenter - (yCenter / 2) - 100);
		font = Main.GAME.getFont(90);
		g.setFont(font);
		String _press = "PRESS THE RED BUTTON";
		stringWidth = Utils.getWidth(_press, g.getFont());
		g.drawString(_press, xCenter - (stringWidth / 2), yCenter - (yCenter / 2));
		
		for(int i = 0; i < numberOfArrowsInCircle; i++)
		{			
			double rotation = Math.toRadians((360/numberOfArrowsInCircle) * i);
			int x = screenWidth/2;
			int y;
			if ((double) screenWidth/ (double) screenHeight == 1.6)
				y = (int) (screenHeight * 1.48125);
			else
				y = (int) (screenHeight * 1.45);
			
			g2.translate(x, y);
			g2.rotate(rotation);
			
			if (i < (numberOfArrows/2+1) || i > (numberOfArrowsInCircle - (numberOfArrows/2+1)))
				g2.drawImage(_arrow, 0 - arrowWidth/2, 0 - screenHeight, arrowWidth, arrowHeight, null);
			
			g2.rotate(-rotation);
			g2.translate(-x, -y);
		}
		
		addTimeBar(g);
	}

}

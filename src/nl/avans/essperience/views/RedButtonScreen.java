package nl.avans.essperience.views;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.models.RedButtonModel;
import nl.avans.essperience.utils.Utils;

public class RedButtonScreen extends GameScreen {
	
	public RedButtonScreen(GameModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		_gameModel.update();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		int screenWidth = Main.GAME.getWidth();
		int screenHeight = Main.GAME.getHeight();
		int xCenter = Main.GAME.getWidth() / 2;
		int yCenter = Main.GAME.getHeight() / 2;
		
		setBackground(Color.CYAN);
		
		g.setFont(new Font("Arial", Font.PLAIN, 35));
		String _dont = "DON'T";
		int stringWidth = Utils.getWidth(_dont, g.getFont());
		g.drawString(_dont, xCenter - (stringWidth / 2), yCenter - 70);
		g.setFont(new Font("Arial", Font.PLAIN, 90));
		String _press = "PRESS THE RED BUTTON";
		stringWidth = Utils.getWidth(_press, g.getFont());
		g.drawString(_press, xCenter - (stringWidth / 2), yCenter);
		double timeBarSize = Main.GAME.getWidth() * ((RedButtonModel) _gameModel).getTimeRemaining();
		g.fillRect(0, 0, (int) timeBarSize, 20);
	}

}

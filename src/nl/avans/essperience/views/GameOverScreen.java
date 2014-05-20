package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameOverModel;

public class GameOverScreen extends GameScreen {

	public GameOverScreen(GameOverModel model) {
		super(model);
		// TODO Auto-generated constructor stub
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
		int yCenter = Main.GAME.getHeight() / 2;
		
		Font font = new Font("Arial", Font.PLAIN, 60) ;
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		String gameOver = "Game Over";
		
		
		int stringWidth = fm.stringWidth(gameOver);
		
		g.setFont(font);
		g.drawString(gameOver, xCenter - (stringWidth / 2), yCenter);
	}

}

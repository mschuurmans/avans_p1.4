package nl.avans.essperience.views;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import net.phys2d.raw.Body;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.GameModel;
import nl.avans.essperience.models.RedButtonModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

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
		
		setBackground(Color.CYAN);
		
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		String _dont = "DON'T";
		int stringWidth = Utils.getWidth(_dont, g.getFont());
		g.drawString(_dont, xCenter - (stringWidth / 2), yCenter - (yCenter / 2) - 100);
		g.setFont(new Font("Arial", Font.PLAIN, 90));
		String _press = "PRESS THE RED BUTTON";
		stringWidth = Utils.getWidth(_press, g.getFont());
		g.drawString(_press, xCenter - (stringWidth / 2), yCenter - (yCenter / 2));
		
		for(int i = 0; i < 24; i++)
		{			
			double rotation = Math.toRadians(15 * i);
			int x = screenWidth/2;
			int y = (int) (screenHeight) + 385;
			
			g2.translate(x, y);
			g2.rotate(rotation);
			
			g2.drawImage(_arrow, 0 - screenWidth/20, 0 - 800, screenWidth/10, screenWidth/5, null);
			
			g2.rotate(-rotation);
			g2.translate(-x, -y);
		}
		
//		g.drawImage(_arrow, screenWidth/6-70, screenHeight-screenWidth/5, screenWidth/10, screenWidth/5, null);
//		g.drawImage(_arrow, screenWidth/6*2-70, screenHeight-screenWidth/5, screenWidth/10, screenWidth/5, null);
//		g.drawImage(_arrow, screenWidth/6*3-70, screenHeight-screenWidth/5, screenWidth/10, screenWidth/5, null);
//		g.drawImage(_arrow, screenWidth/6*4-70, screenHeight-screenWidth/5, screenWidth/10, screenWidth/5, null);
//		g.drawImage(_arrow, screenWidth/6*5-70, screenHeight-screenWidth/5, screenWidth/10, screenWidth/5, null);
		
		addTimeBar(g);
	}

}

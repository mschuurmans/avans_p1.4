package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nl.avans.essperience.events.StatusUpdateListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.LoadingModel;

public class LoadingScreen extends GameScreen
{
	private static final long serialVersionUID = -2460620638594076361L;
	private static String _status = "";
	public LoadingScreen(LoadingModel model) 
	{
		super(model);
		// TODO Auto-generated constructor stub
		((LoadingModel)_gameModel).addStatusUpdateListener(new StatusUpdateListener()
		{	
			@Override
			public void statusUpdated(String status) 
			{
				_status = status;
			}
		});
	}
	
	public void update()
	{
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		Font font = new Font("Arial", Font.PLAIN, 60) ;
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		FontMetrics fm = img.getGraphics().getFontMetrics(font);
		String status = _status;
		
		int stringWidth = fm.stringWidth(status);
		
		int xCenter = (int)(Main.DIMENSION.getWidth() / 2);
		int yCenter = (int)(Main.DIMENSION.getHeight() / 2);
		
		g.setFont(font);
		g.drawString(status, xCenter - (stringWidth / 2), yCenter);
	}
}

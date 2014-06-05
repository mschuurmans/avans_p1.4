package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import nl.avans.essperience.events.StatusUpdateListener;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.LoadingModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class LoadingScreen extends GameScreen
{
	private static final long serialVersionUID = -2460620638594076361L;
	private static String _status = "";
	private Image _image = null;
	public LoadingScreen(LoadingModel model) 
	{
		super(model);
		
		URL url = this.getClass().getClassLoader().getResource("Essperience/menuscreen.png");
		
		try {
			_image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		if(_image != null)
			g.drawImage(_image, 0, 0, Main.GAME.getWidth(), Main.GAME.getHeight(), null);
		
		//Font font = new Font("Arial", Font.PLAIN, 60) ;
		Font font = Main.GAME.getFont(60);
		String status = _status;
		
		int stringWidth = Utils.getWidth(status, font);
		
		int xCenter = (int)(Main.DIMENSION.getWidth() / 2);
		int yCenter = (int)(Main.DIMENSION.getHeight() / 2);
		
		g.setFont(font);
		Utils.drawString(g, status, xCenter - (stringWidth / 2), yCenter);
	}
}

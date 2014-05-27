package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import net.phys2d.raw.Body;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class FopsScreen extends GameScreen 
{
	private static final long serialVersionUID = -6529289814578856921L;
	private Image[] _fruitImages = new Image[4];
	private Image _crosshair = AssetManager.Instance().getImage("Fops/crosshair.png");
	public static final int BANANA = 0;
	public static final int ORANGE = 1;
	public static final int APPLE = 2;
	public static final int PEAR = 3;
	
	public FopsScreen(FopsModel model) 
	{
		super(model);

		_fruitImages[BANANA] = AssetManager.Instance().getImage("Simon/banana.png");
		_fruitImages[ORANGE] = AssetManager.Instance().getImage("Simon/orange.png");
		_fruitImages[APPLE] = AssetManager.Instance().getImage("Simon/apple.png");
		_fruitImages[PEAR] = AssetManager.Instance().getImage("Simon/pear.png");
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() 
	{
		_gameModel.update();
	}
	
	public void paintComponent(Graphics g1)
	{
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		
		//System.out.println("drawing!");
		FopsModel model = (FopsModel)_gameModel;
		ArrayList<Body> fruitBodies = model.getBodies();
		g.drawImage(_crosshair, (int)model.getCursorPosition().x, (int)model.getCursorPosition().y, 20,20, null);
		for (Body body : fruitBodies)
		{
			float rotation = body.getRotation();
			int x = (int)body.getPosition().getX();
			int y = (int)body.getPosition().getY();
		
			g.translate(x, y);
			g.rotate(rotation);	
			int size = 90;
			g.drawImage(getFruitImage((String)body.getUserData()), 0 -size/2, 0 -size/2, size, size, null);	

			g.rotate(-rotation);
			g.translate(-x, -y);
			// TODO draw fruits
		}
		
		addTimeBar(g);
		
		//Display debug data when model says so!
		if(model.isDebugTrue())
		{
			int startY = 40;
			List<String> debugData = model.getDebugData();
			for(String string : debugData)
			{
				g.drawString(string, Main.GAME.getWidth() - (Utils.getWidth(string, g.getFont()) + 20), startY + (20 * debugData.indexOf(string) ) );
			}
		}
			
	}

	private Image getFruitImage(String name)
	{
		switch (name) {
		case "banana": return _fruitImages[BANANA];
		case "orange": return _fruitImages[ORANGE];
		case "pear": return _fruitImages[PEAR];
		case "apple": return _fruitImages[APPLE];
		default: return null;
		}
	}
	
}

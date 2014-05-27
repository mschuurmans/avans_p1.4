package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class FopsScreen extends GameScreen 
{
	private static final long serialVersionUID = -6529289814578856921L;
	private Image[] _fruitImages = new Image[4];
	private Image _crosshair = AssetManager.Instance().getImage("Fops/crosshair.png");
	private Image _bullet = AssetManager.Instance().getImage("Fops/bullet.png");
	private Image _splashImage = AssetManager.Instance().getImage("Fops/splash.png");
	private static final int BANANA = 0;
	private static final int ORANGE = 1;
	private static final int APPLE = 2;
	private static final int PEAR = 3;
	private static int _screenWidth = Main.GAME.getWidth();
	private static int _screenHeight = Main.GAME.getHeight();
	private int _amountOfBullets;
	
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
		
		FopsModel model = (FopsModel)_gameModel;
		_amountOfBullets = model.getBullets();
		ArrayList<Body> fruitBodies = model.getBodies();
		ArrayList<Vector2f> shotPositions = model.getShotPositions();
		int splashSize = 150;
		
		//drawing splashes
		for (Vector2f s : shotPositions)
		{
			g.drawImage(_splashImage, (int)s.getX(), (int)s.getY(), splashSize, splashSize, null);
		}
		
		//drawing fruits
		for (Body body : fruitBodies)
		{
			float rotation = body.getRotation();
			int x = (int)body.getPosition().getX();
			int y = (int)body.getPosition().getY();
		
			g.translate(x, y);
			g.rotate(rotation);	
			int size = 150;
			g.drawImage(getFruitImage((String)body.getUserData()), 0 -size/2, 0 -size/2, size, size, null);	

			g.rotate(-rotation);
			g.translate(-x, -y);
		}
		
		//drawing crosshair
		int crosshairSize = 60;
		g.drawImage(_crosshair, (int)model.getCursorPosition().x, (int)model.getCursorPosition().y, crosshairSize, crosshairSize, null);
		
		//bullet information
		g.drawString("Bullets: ", _screenWidth - 130, _screenHeight-30);
		if (_amountOfBullets <= 6)
		{
			for (int i = _amountOfBullets; i > 0; i--)
			{
				g.drawImage(_bullet, _screenWidth-30-(10*i), _screenHeight-40, 10, 20, null);
			}
		}
		else
		{
			g.drawString("" + _amountOfBullets, _screenWidth-30, _screenHeight-30);
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

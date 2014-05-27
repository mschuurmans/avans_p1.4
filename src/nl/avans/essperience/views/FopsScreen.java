package nl.avans.essperience.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import net.phys2d.raw.Body;
import nl.avans.essperience.entities.simon.FruitPiece;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.AssetManager;

public class FopsScreen extends GameScreen 
{
	private static final long serialVersionUID = -6529289814578856921L;
	private Image[] _fruitImages = new Image[4];
	private Image _crosshair = AssetManager.Instance().getImage("Fops/crosshair.png");
	private Image _bullet = AssetManager.Instance().getImage("Fops/bullet.png");
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
		
		//System.out.println("drawing!");
		FopsModel model = (FopsModel)_gameModel;
		_amountOfBullets = model.getBullets();
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
		
		g.drawString("Bullets: ", _screenWidth - 60, _screenHeight-30);
		if (_amountOfBullets <= 6)
		{
			for (int i = _amountOfBullets; i > 0; i--)
			{
				g.drawImage(_bullet, _screenWidth-30-(10*i), _screenHeight-30, null);
			}
		}
		else
		{
			g.drawString("" + _amountOfBullets, _screenWidth-30, _screenHeight-30);
		}
		addTimeBar(g);
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

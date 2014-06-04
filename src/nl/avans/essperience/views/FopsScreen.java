package nl.avans.essperience.views;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import net.phys2d.raw.Body;
import nl.avans.essperience.entities.fops.BulletHole;
import nl.avans.essperience.entities.fops.Splash;
import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.FopsModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class FopsScreen extends GameScreen 
{
	private static final long serialVersionUID = -6529289814578856921L;
	private Image[] _fruitImages = new Image[4];
	private BufferedImage[] _fruitPieceImages = new BufferedImage[4];
	private Image _crosshair = AssetManager.Instance().getImage("Fops/crosshair.png");
	private Image _bullet = AssetManager.Instance().getImage("Fops/bullet.png");
	private static final int BANANA = 0;
	private static final int ORANGE = 1;
	private static final int APPLE = 2;
	private static final int PEAR = 3;
	
	private static int _screenWidth = Main.GAME.getWidth();
	private static int _screenHeight = Main.GAME.getHeight();
	private int _amountOfBullets;
	private double _splashAlpha = 1;
	
	public FopsScreen(FopsModel model) 
	{
		super(model);

		_fruitImages[BANANA] = AssetManager.Instance().getImage("Simon/banana.png");
		_fruitImages[ORANGE] = AssetManager.Instance().getImage("Simon/orange.png");
		_fruitImages[APPLE] = AssetManager.Instance().getImage("Simon/apple.png");
		_fruitImages[PEAR] = AssetManager.Instance().getImage("Simon/pear.png");
		_fruitPieceImages[BANANA] = (BufferedImage) AssetManager.Instance().getImage("Fops/banana_pieces.png");
		_fruitPieceImages[ORANGE] = (BufferedImage) AssetManager.Instance().getImage("Fops/orange_pieces.png");
		_fruitPieceImages[APPLE] = (BufferedImage) AssetManager.Instance().getImage("Fops/apple_pieces.png");
		_fruitPieceImages[PEAR] = (BufferedImage) AssetManager.Instance().getImage("Fops/pear_pieces.png");
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
		ArrayList<Splash> splashes = model.getSplashes();
		
		//draw background
		g.drawImage(AssetManager.Instance().getImage("Fops/fruitops_background.png") , 0, 0, Main.GAME.getWidth(), Main.GAME.getHeight(), null);
		
		//draw SplashScreen
		Composite composite = g.getComposite();
		_splashAlpha -= 0.03;
		if(_splashAlpha > 0)
		{
			g.setComposite(AlphaComposite.SrcOver.derive((float) _splashAlpha));
			g.drawImage(AssetManager.Instance().getImage("Fops/fruitops_splashscreen.png"), (Main.GAME.getWidth() / 2) -400/2, (int) (Main.GAME.getHeight() * 0.1), 800/2, 340/2, null);
			g.setComposite(composite);
		}
		
		//draw bulletholes
		for(BulletHole bh : AssetManager.Instance().getBulletHoles())
		{
			bh.draw(g);
		}
		
		//drawing splashes
		for (Splash s : splashes)
		{
			//System.out.println("drawing splash: " + s.getType() +  " rotated over: " + s.getRotation() + " at x: " + s.getX() + " and y: " + s.getY());
			s.draw(g);
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
		int bulletSize = 60;
		g.drawString("Bullets: ", _screenWidth - 230, _screenHeight-30);
		if (_amountOfBullets <= 30)
		{
			for (int i = _amountOfBullets; i > 0; i--)
			{
				g.drawImage(_bullet, _screenWidth-60-(bulletSize/2*i), _screenHeight-60, bulletSize/2, bulletSize, null);
			}
		}
		else
		{
			g.setFont(new Font("SANS-SERIF", Font.BOLD, 20));
			g.setColor(Color.WHITE);
			g.drawString("Bullets: " + _amountOfBullets, _screenWidth-180, _screenHeight-30);
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
		case "bananapiece1": return _fruitPieceImages[BANANA].getSubimage(0, 0,512, 512);
		case "bananapiece2": return _fruitPieceImages[BANANA].getSubimage(512, 0,512, 512);
		case "bananapiece3": return _fruitPieceImages[BANANA].getSubimage(1024, 0,512, 512);
		case "bananapiece4": return _fruitPieceImages[BANANA].getSubimage(1536, 0,512, 512);
		case "orangepiece1": return _fruitPieceImages[ORANGE].getSubimage(0, 0,512, 512);
		case "orangepiece2": return _fruitPieceImages[ORANGE].getSubimage(512, 0,512, 512);
		case "orangepiece3": return _fruitPieceImages[ORANGE].getSubimage(1024, 0,512, 512);
		case "orangepiece4": return _fruitPieceImages[ORANGE].getSubimage(1536, 0,512, 512);
		case "pearpiece1": return _fruitPieceImages[PEAR].getSubimage(0, 0,512, 512);
		case "pearpiece2": return _fruitPieceImages[PEAR].getSubimage(512, 0,512, 512);
		case "pearpiece3": return _fruitPieceImages[PEAR].getSubimage(1024, 0,512, 512);
		case "pearpiece4": return _fruitPieceImages[PEAR].getSubimage(1536, 0,512, 512);
		case "applepiece1": return _fruitPieceImages[APPLE].getSubimage(0, 0,512, 512);
		case "applepiece2": return _fruitPieceImages[APPLE].getSubimage(512, 0,512, 512);
		case "applepiece3": return _fruitPieceImages[APPLE].getSubimage(1024, 0,512, 512);
		case "applepiece4": return _fruitPieceImages[APPLE].getSubimage(1536, 0,512, 512);
		default: return null;
		}
	}
	
}

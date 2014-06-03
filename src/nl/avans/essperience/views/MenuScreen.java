package nl.avans.essperience.views;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import nl.avans.essperience.main.Main;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Utils;

public class MenuScreen extends GameScreen
{
	private static final long serialVersionUID = 4178628810705405806L;
	
	private boolean _debug = false;
	private int _selected = 0;
	
	private Image _checkedLeft;
	private Image _checkedRight;
	private Image _unCheckedLeft; 
	private Image _unCheckedRight;
	
	public void setSelected(int value)
	{
		_selected = value;
		System.out.println("Selected value has been set to: " + _selected);
	}
	
	public MenuScreen(MenuModel model) 
	{
		super(model);
		
		BufferedImage checkedFull = (BufferedImage)AssetManager.Instance().getImage("Essperience/footsteps_green.png");
		_checkedLeft = checkedFull.getSubimage(0, 0, 75, 175);
		_checkedRight = checkedFull.getSubimage(75, 0, 75, 175);
		
		BufferedImage uncheckedFull = (BufferedImage)AssetManager.Instance().getImage("Essperience/footsteps.png");
		_unCheckedLeft = uncheckedFull.getSubimage(0, 0, 75, 175);
		_unCheckedRight = uncheckedFull.getSubimage(75, 0, 75, 175);
	}

	@Override
	public void update() 
	{
		if(_debug)
			System.out.println("Update called.");
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//drawing code.
		if(_debug)
			System.out.println("Drawing menu screen");
		
		if(Main.GAME != null)
		{
			int xCenter = Main.GAME.getWidth() / 2;
			int yCenter = Main.GAME.getHeight() / 2;
			
			Font font = new Font("Arial", Font.PLAIN, 60) ;
			BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			FontMetrics fm = img.getGraphics().getFontMetrics(font);
			String startGame = "Stand on the pressure plates";
			String nLine = "to start the game!";
			
			
			int stringWidth = fm.stringWidth(startGame);
			int nLineWidth = fm.stringWidth(nLine);
			
			g.setFont(font);
			Utils.drawStringWithOutline(g, startGame, font, xCenter - (stringWidth / 2), yCenter - 115);
			Utils.drawStringWithOutline(g, nLine, font,  xCenter - (nLineWidth / 2),  yCenter - 50);
			
			
			int leftLocX = xCenter - (20 + 150);
			int rightLocX = xCenter + 20;
			
			if(((MenuModel)_gameModel).getLeftFoot() == true)
				g.drawImage(_checkedLeft, leftLocX, yCenter, 75, 175, null);
			else
				g.drawImage(_unCheckedLeft, leftLocX, yCenter, 75, 175, null);
			
			if(((MenuModel)_gameModel).getRightFoot() == true)
				g.drawImage(_checkedRight, rightLocX, yCenter, 75, 175, null);
			else
				g.drawImage(_unCheckedRight, rightLocX, yCenter, 75, 175, null);
				
		}
	}
	

}

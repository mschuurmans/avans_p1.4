package nl.avans.essperience.models;

import java.awt.Image;

import nl.avans.essperience.utils.AssetManager;

public class RedButtonModel extends GameModel
{
	
	private Image _background;
	
	public RedButtonModel()
	{
		_background = AssetManager.Instance().getImage("RedButton/background.png");
	}
	
	public Image getBackground()
	{
		return _background;
	}
	
	@Override
	public void update()
	{
		
	}
}

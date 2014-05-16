package nl.avans.essperience.models;

<<<<<<< HEAD
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
=======
public class RedButtonModel extends GameModel {

>>>>>>> 0d13b5b522b242f58b2db64cabbf123a3a54a40f
}

package nl.avans.essperience.models;

import java.awt.Image;
import java.util.Timer;

import nl.avans.essperience.utils.AssetManager;

public class RedButtonModel extends GameModel
{	
	public RedButtonModel()
	{
		_maxTime = 3000;
	}
	
	@Override
	public void update()
	{
		if (getTimeRemaining() == 0)
		{
			if(_modelToControllerListener != null)
				_modelToControllerListener.timesUpEvent();
		}
	}
}

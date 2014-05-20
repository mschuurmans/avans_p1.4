package nl.avans.essperience.models;

import nl.avans.essperience.entities.waf.Fardoes;
import nl.avans.essperience.main.*;
import nl.avans.essperience.utils.AssetManager;

import java.awt.*;

public class WafModel extends GameModel
{
	private Fardoes _fardoes;
	private Image _background;
	private int _timeBeforeMove = 2000; // time before the fardoes changes location in millisec
	private long _lastTimeChanged = 0;	

	public WafModel()
	{
		_background = AssetManager.Instance().getImage("Waf/fardoes background.jpg");
		_fardoes = new Fardoes(1, 100,100); //TODO change to image w/h
		_timeBeforeMove = 200 + (2500/(int)Math.sqrt(Main.GAME.getDifficulty()));
		_maxTime = 1000 + (2000/(int)Math.sqrt(Main.GAME.getDifficulty()));
	}
	
	/**
	 * whacks and returns a boolean if it was a hit or not.
	 * @param location location where the whack was.
	 * @return
	 */
	public boolean whack(int location)
	{
		if(location == _fardoes.getLocation())
			return true;
		else
			return false;
	}
	
	public Image getBackground()
	{
		return this._background;
	}

	public Fardoes getFardoes()
	{
		return _fardoes;
	}
	
	@Override
	public void update()
	{
		// update method
		if(System.currentTimeMillis() > (_lastTimeChanged + _timeBeforeMove))
		{
			int newLocation =(int) (Math.random() * 9) + 1;		
			_fardoes.setLocation(newLocation);
			_lastTimeChanged = System.currentTimeMillis();
		}
		
		if(getTimeRemaining() <= 0)
		{
			if(_modelToControllerListener != null)
			{
				_modelToControllerListener.gameFinished(false);
			}
		}
	}
}

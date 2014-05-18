package nl.avans.essperience.models;

import java.awt.Image;
import java.util.Timer;

import nl.avans.essperience.utils.AssetManager;

public class RedButtonModel extends GameModel
{
	private Timer _timer;
	private long _beginTime;
	private long _currentTime;
	private long _timePassed;
	private long _timeBar;
	private int _maxTime;
	
	public RedButtonModel()
	{
		_beginTime = System.currentTimeMillis();
		_maxTime = 3000;
	}
	
	public long getTimeRemaining() {
		_currentTime = System.currentTimeMillis();
		_timePassed = _currentTime - _beginTime;
		if (_timePassed < 3000)
		{
			_timeBar = (_maxTime - _timePassed) / _maxTime;
		}
		return _timeBar;
	}
	
	@Override
	public void update()
	{
		
	}
}

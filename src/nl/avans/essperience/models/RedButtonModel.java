package nl.avans.essperience.models;

import java.awt.Image;
import java.util.Timer;

import nl.avans.essperience.utils.AssetManager;

public class RedButtonModel extends GameModel
{
	private Timer _timer;
	private double _beginTime;
	private double _currentTime;
	private double _timePassed;
	private double _timeBar;
	private double _maxTime;
	
	public RedButtonModel()
	{
		_beginTime = System.currentTimeMillis();
		_maxTime = 3000;
	}
	
	public double getTimeRemaining() {
		_currentTime = System.currentTimeMillis();
		_timePassed = _currentTime - _beginTime;
		if (_timePassed < _maxTime)
		{
			_timeBar = (_maxTime - _timePassed) / _maxTime;
		}
		else
		{
			_timeBar = 0;
		}
		return _timeBar;
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

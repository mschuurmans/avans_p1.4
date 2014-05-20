package nl.avans.essperience.models;

import nl.avans.essperience.events.ModelToControllerEventListener;

public class GameModel 
{
	protected int _maxTime;
	protected int _speed;
	protected ModelToControllerEventListener _modelToControllerListener = null;
	protected double _timeBar;
	protected double _beginTime = System.currentTimeMillis();
	
	public void addModelToControllerEventListener( ModelToControllerEventListener  listener)
	{
		_modelToControllerListener = listener;
	}
	
	public double getTimeRemaining() {
		double _currentTime = System.currentTimeMillis();
		double _timePassed = _currentTime - _beginTime;
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
	
	public void update(){}
}

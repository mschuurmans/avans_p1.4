package nl.avans.essperience.models;

import nl.avans.essperience.events.ModelToControllerEventListener;

public class GameModel 
{
	protected int _maxTime;
	protected int _speed;
	protected ModelToControllerEventListener _modelToControllerListener = null;
	protected double _timeBar;
	protected double _beginTime = System.currentTimeMillis();
	protected boolean _debug = false;
	
	public void addModelToControllerEventListener( ModelToControllerEventListener  listener)
	{
		_modelToControllerListener = listener;
	}
	
	public double getTimeRemaining() {
		double currentTime = System.currentTimeMillis();
		double timePassed = currentTime - _beginTime;
		if (timePassed < _maxTime)
			_timeBar = (_maxTime - timePassed) / _maxTime;
		else
			_timeBar = 0;
		if (_debug)
			System.out.println("maxtime: "+ _maxTime + " timePassed: " + timePassed + "_timeBar: " + _timeBar);
		return _timeBar;
	}
	
	public int getTimeRemainingAsInt()
	{
		double rem = getTimeRemaining() * 100;
		return (int)rem;
	}
	
	public void update(){}
}

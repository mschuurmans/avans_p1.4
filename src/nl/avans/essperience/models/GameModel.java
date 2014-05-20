package nl.avans.essperience.models;

import nl.avans.essperience.events.ModelToControllerEventListener;

public class GameModel 
{
	protected int _maxTime;
	protected int _speed;
	protected ModelToControllerEventListener _modelToControllerListener = null;
	
	public void addModelToControllerEventListener( ModelToControllerEventListener  listener)
	{
		_modelToControllerListener = listener;
	}
	
	public void update(){}
}

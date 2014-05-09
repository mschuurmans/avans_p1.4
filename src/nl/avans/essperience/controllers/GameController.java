package nl.avans.essperience.controllers;

import nl.avans.essperience.events.MicroGameFinishedEventListener;

public abstract class GameController 
{
	protected MicroGameFinishedEventListener _finishedListener = null;
	
	public GameController()
	{
		
	}
	
	public void addMicroGameFinishedEventListener(MicroGameFinishedEventListener listener)
	{
		this._finishedListener = listener;
	}	
	
	public void callFinishedListener(boolean succeed)
	{
		if(_finishedListener != null)
			_finishedListener.microGameFinishedEvent(succeed);
	}
}

package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;

public class InputController 
{
	private InputTriggerdEventListener _listener = null;
	
	public void addInputTriggeredEventListener(InputTriggerdEventListener listener)
	{
		this._listener = listener;
	}
}

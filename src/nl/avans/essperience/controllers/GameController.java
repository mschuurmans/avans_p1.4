package nl.avans.essperience.controllers;

import nl.avans.essperience.events.MicroGameFinishedEventListener;

public abstract class GameController 
{
	private InputController _input;
	private MicroGameFinishedEventListener _finishedListener = null;
	
	public GameController()
	{
		
	}
	
	public void addMicroGameFinishedEventListener(MicroGameFinishedEventListener listener)
	{
		this._finishedListener = listener;
	}
	
	public void setInputController(InputController input)
	{
		this._input = input;
	}
}

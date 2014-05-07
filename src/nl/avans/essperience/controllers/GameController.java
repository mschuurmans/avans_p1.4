package nl.avans.essperience.controllers;

public abstract class GameController 
{
	private InputController _input;
	
	public GameController()
	{
		
	}
	
	public void setInputController(InputController input)
	{
		this._input = input;
	}
}

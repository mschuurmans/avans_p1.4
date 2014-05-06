package nl.avans.essperience.controllers;

public abstract class GameController 
{
	private InputController _input;
	
	public GameController(InputController input)
	{
		this._input = input;
	}
}

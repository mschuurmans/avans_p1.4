package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;

public class MenuController extends GameController
{

	public MenuController() 
	{
		InputController input = new InputController();
		input.addInputTriggeredEventListener(new InputTriggerdEventListener() 
		{
				@Override
				public void buttonPressed() 
				{
					//handle the button pressed event.
				}
		});
		
		this.setInputController(input);
	}

}

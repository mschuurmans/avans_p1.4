package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.MenuScreen;

public class MenuController extends GameController
{
	private MenuScreen _view;
	private MenuModel _model;
	public MenuController(MenuScreen menuScreen, MenuModel model) 
	{
		this._view = menuScreen;
		this._model = model;
		InputController input = new InputController();
		input.addInputTriggeredEventListener(new InputTriggerdEventListener() 
		{
				@Override
				public void keyPressed(GameKeys key) 
				{
					//handle the button pressed event.
				}
		});
		
		this.setInputController(input);
	}

}

package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.MenuScreen;

public class MenuController extends GameController
{
	private MenuScreen _view;
	private MenuModel _model;
	
	private boolean _debug = false;
	
	public MenuController(MenuScreen menuScreen, MenuModel model) 
	{
		this._view = menuScreen;
		this._model = model;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener() 
		{
				@Override
				public void keyPressed(GameKeys key) 
				{
					if(_debug)
						System.out.println("button was pressed and event was called.");
					
					switch(key)
					{
						case KeyEnter:
							selectCurrentOption();
							break;
						default:
							break;
					}
				}
		});
		
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	public void selectCurrentOption()
	{
		if(this._finishedListener != null)
			this._finishedListener.microGameFinishedEvent(true);
	}

}

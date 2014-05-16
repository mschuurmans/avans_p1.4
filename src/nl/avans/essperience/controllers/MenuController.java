package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.MenuScreen;

public class MenuController extends GameController
{
	private MenuScreen _view;
	private MenuModel _model;
	
	private boolean _debug = true;
	private int _currentlySelected = 0;
	
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
						System.out.println("button was pressed and event was called." + key.toString());
					
					switch(key)
					{
						case KeyEnter:
							selectCurrentOption();
							break;
						case KeyUp:
							if(_currentlySelected > 0)
								_currentlySelected = 0;
							_view.setSelected(0);
							break;
						case KeyDown:
							if(_currentlySelected < 1)
								_currentlySelected++;
							_view.setSelected(_currentlySelected);
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
		if(_debug)
			System.out.println("MenuController : current option");
		
		if(_currentlySelected == 0)
		{
			_view.stopTimer();
			this.callFinishedListener(true);
		}
		else if(_currentlySelected == 1)
			System.exit(0);
	}

}

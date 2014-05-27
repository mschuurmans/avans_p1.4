package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.MenuScreen;

public class MenuController extends GameController
{
	private MenuScreen _view;
	@SuppressWarnings("unused")
	private MenuModel _model;
	
	private boolean _debug = true;
	private int _currentlySelected = 0;
	
	public MenuController(MenuScreen menuScreen, MenuModel model) 
	{
		this._view = menuScreen;
		this._model = model;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener() 
		{
			private boolean aPressed = false;
			private boolean dPressed = false;
				@Override
				public void keyPressed(GameKeys key) 
				{
					if(_debug)
						System.out.println("button was pressed and event was called." + key.toString());
					
					switch(key)
					{
						case KeyA:
							aPressed = true;
							break;
						case KeyD:
							dPressed = true;
							break;
					}
					
					if(aPressed && dPressed)
						startGame();
				}
				
				@Override
				public void keyReleased(GameKeys key)
				{
					switch(key)
					{
						case KeyA:
							aPressed = false;
							break;
						case KeyD:
							dPressed = false;
							break;
					}
				}
		});
		
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	public void startGame()
	{
		if(_debug)
			System.out.println("MenuController : current option");
		
		
		AssetManager.Instance().flushPersistentData();
		_view.stopTimer();
		this.callFinishedListener(true);
	}

}

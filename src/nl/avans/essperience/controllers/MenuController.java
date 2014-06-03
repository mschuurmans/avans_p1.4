package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.models.MenuModel;
import nl.avans.essperience.utils.AssetManager;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.MenuScreen;

public class MenuController extends GameController
{
	private MenuScreen _view;
	private MenuModel _model;
	
	private boolean _debug = true;
	
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
						case KeyA:
							_model.setLeftFoot(true);
							break;
						case KeyD:
							_model.setRightFoot(true);
							break;
						default:
							break;
					}
					
					if(_model.getLeftFoot() && _model.getRightFoot())
						startGame();
				}
				
				@Override
				public void keyReleased(GameKeys key)
				{
					switch(key)
					{
						case KeyA:
							_model.setLeftFoot(false);
							break;
						case KeyD:
							_model.setRightFoot(false);
							break;
						default:
							break;
					}
				}
		});
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	public void startGame()
	{				
		AssetManager.Instance().flushPersistentData();
		_view.stopTimer();
		this.callFinishedListener(true);
	}

}

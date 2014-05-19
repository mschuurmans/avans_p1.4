package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.SimonGameModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.SimonGameScreen;

public class SimonGameController extends GameController
{
	private SimonGameModel _model;
	private SimonGameScreen _view;
	
	public SimonGameController(SimonGameModel model, SimonGameScreen view)
	{
		this._model = model;
		this._view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
				public void keyPressed(GameKeys key)
				{
					
				}
		});
		_view.addViewToControllerEventListener(new ViewToControllerEventListener()
		{
			@Override
			public void sendGamefinishedEvent(boolean succes)
			{
				callFinishedListener(succes);
			}
		});
		this._view.addKeyListener(InputController.Instance().getKeyboardListener());
	}
	
	
}

package nl.avans.essperience.controllers;

import nl.avans.essperience.events.InputTriggerdEventListener;
import nl.avans.essperience.events.ViewToControllerEventListener;
import nl.avans.essperience.models.RedButtonModel;
import nl.avans.essperience.utils.Enums.GameKeys;
import nl.avans.essperience.views.RedButtonScreen;

public class RedButtonController extends GameController 
{
	
	private RedButtonModel _model;
	private RedButtonScreen _view;
	private boolean _debug = true;
	
	public RedButtonController(RedButtonModel model, RedButtonScreen view) 
	{
		_model = model;
		_view = view;
		
		InputController.Instance().addInputTriggeredEventListener(new InputTriggerdEventListener()
		{
			public void keyPressed(GameKeys key)
			{
				switch(key)
				{
					case KeySpacebar:
						callFinishedListener(false);
						break;
					default:
						break;
				}
				if (_debug)
					System.out.println("Red Button Pressed");
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
